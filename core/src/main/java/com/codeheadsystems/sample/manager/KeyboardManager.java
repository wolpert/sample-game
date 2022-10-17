package com.codeheadsystems.sample.manager;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.codeheadsystems.sample.entitysystem.TankEntitySystem;
import com.codeheadsystems.sample.entitysystem.ZoomEntitySystem;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class handles the keys and what it means for the various systems. (Direction, firing, zooming, etc)
 */
@Singleton
public class KeyboardManager extends InputAdapter {

  private final ZoomEntitySystem zoomEntitySystem;
  private final TankEntitySystem tankEntitySystem;

  @Inject
  public KeyboardManager(final ZoomEntitySystem zoomEntitySystem,
                         final TankEntitySystem tankEntitySystem) {
    this.zoomEntitySystem = zoomEntitySystem;
    this.tankEntitySystem = tankEntitySystem;
  }

  @Override
  public boolean keyDown(final int keycode) {
    boolean result = true;
    switch (keycode) {
      case Input.Keys.UP -> zoomEntitySystem.zoomUp();
      case Input.Keys.DOWN -> zoomEntitySystem.zoomDown();
      case Input.Keys.W -> tankEntitySystem.gas();
      case Input.Keys.A -> tankEntitySystem.turnLeft();
      case Input.Keys.D -> tankEntitySystem.turnRight();
      default -> result = false;
    }
    return result;
  }

  @Override
  public boolean keyUp(final int keycode) {
    boolean result = true;
    switch (keycode) {
      case Input.Keys.UP -> zoomEntitySystem.disableZoomUp();
      case Input.Keys.DOWN -> zoomEntitySystem.disableZoomDown();
      case Input.Keys.W -> tankEntitySystem.stopGas();
      case Input.Keys.A -> tankEntitySystem.stopTurningLeft();
      case Input.Keys.D -> tankEntitySystem.stopTurningRight();
      default -> result = false;
    }
    return result;
  }

}
