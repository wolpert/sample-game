package com.codeheadsystems.sample.entitysystem;

import static com.codeheadsystems.sample.dagger.BattleEntityModule.TANK_SPRITE;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.codeheadsystems.gamelib.entity.entitysystem.Priorities;
import com.codeheadsystems.sample.model.GameConstants;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class TankEntitySystem extends EntitySystem {

  private final int rotation;
  private final float maxSpeed;

  private final Sprite tankSprite;
  private final OrthographicCamera camera;

  private float speed = 0f;

  private Gas gas = Gas.OFF;
  private Turning turning = Turning.NONE;

  @Inject
  public TankEntitySystem(@Named(TANK_SPRITE) final Sprite tankSprite,
                          final OrthographicCamera camera,
                          final GameConstants gameConstants) {
    super(Priorities.MOVEMENT.priority());
    this.tankSprite = tankSprite;
    this.camera = camera;
    final GameConstants.Tank tank = gameConstants.getTank();
    rotation = tank.getRotation();
    maxSpeed = tank.getMaxSpeed();
  }

  @Override
  public void update(final float deltaTime) {
    // handel turning
    rotateTank(deltaTime);
    moveTank(deltaTime);
  }

  private void moveTank(final float deltaTime) {
    if (speed > 0 || gas.equals(Gas.ON)) {
      // handle speed
      if (gas.equals(Gas.ON)) {
        speed += deltaTime;
        speed = Math.min(speed, maxSpeed);
      } else {
        speed -= deltaTime;
        speed = Math.max(speed, 0f);
      }
      if (speed > 0f) {
        final float rotation = tankSprite.getRotation();
        tankSprite.translateX(speed * MathUtils.cosDeg(rotation));
        tankSprite.translateY(speed * MathUtils.sinDeg(rotation));
        camera.position.set(tankSprite.getX(), tankSprite.getY(), 0);
      }
    }
  }

  private void rotateTank(final float deltaTime) {
    float rotate = 0;
    switch (turning) {
      case LEFT -> rotate = deltaTime * rotation;
      case RIGHT -> rotate = -deltaTime * rotation;
    }
    if (rotate != 0) {
      tankSprite.setRotation(tankSprite.getRotation() + rotate);
    }
  }

  public void gas() {
    gas = Gas.ON;
  }

  public void stopGas() {
    gas = Gas.OFF;
  }

  public void turnLeft() {
    turning = Turning.LEFT;
  }

  public void turnRight() {
    turning = Turning.RIGHT;
  }

  public void stopTurningLeft() {
    if (turning.equals(Turning.LEFT)) {
      turning = Turning.NONE;
    }
  }

  public void stopTurningRight() {
    if (turning.equals(Turning.RIGHT)) {
      turning = Turning.NONE;
    }
  }

  enum Gas {ON, OFF}

  enum Turning {LEFT, RIGHT, NONE}

}
