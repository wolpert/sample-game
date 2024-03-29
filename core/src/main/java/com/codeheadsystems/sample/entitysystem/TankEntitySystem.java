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

/**
 * The type Tank entity system.
 */
@Singleton
public class TankEntitySystem extends EntitySystem {

  private final int rotation;
  private final float maxSpeed;

  private final Sprite tankSprite;
  private final OrthographicCamera camera;

  private float speed = 0f;

  private Gas gas = Gas.OFF;
  private Turning turning = Turning.NONE;

  /**
   * Instantiates a new Tank entity system.
   *
   * @param tankSprite    the tank sprite
   * @param camera        the camera
   * @param gameConstants the game constants
   */
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
    rotateTank(deltaTime);
    moveTank(deltaTime);
  }

  private void moveTank(final float deltaTime) {
    if (speed > 0 || gas.equals(Gas.ON)) {
      // handle speed
      speed += (gas.equals(Gas.ON) ? deltaTime : -deltaTime);
      speed = MathUtils.clamp(speed, 0f, maxSpeed);
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

  /**
   * Gas.
   */
  public void gas() {
    gas = Gas.ON;
  }

  /**
   * Stop gas.
   */
  public void stopGas() {
    gas = Gas.OFF;
  }

  /**
   * Turn left.
   */
  public void turnLeft() {
    turning = Turning.LEFT;
  }

  /**
   * Turn right.
   */
  public void turnRight() {
    turning = Turning.RIGHT;
  }

  /**
   * Stop turning left.
   */
  public void stopTurningLeft() {
    if (turning.equals(Turning.LEFT)) {
      turning = Turning.NONE;
    }
  }

  /**
   * Stop turning right.
   */
  public void stopTurningRight() {
    if (turning.equals(Turning.RIGHT)) {
      turning = Turning.NONE;
    }
  }

  /**
   * The enum Gas.
   */
  enum Gas {
    /**
     * On gas.
     */
    ON,
    /**
     * Off gas.
     */
    OFF}

  /**
   * The enum Turning.
   */
  enum Turning {
    /**
     * Left turning.
     */
    LEFT,
    /**
     * Right turning.
     */
    RIGHT,
    /**
     * None turning.
     */
    NONE}

}
