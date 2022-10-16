package com.codeheadsystems.sample.manager;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.codeheadsystems.sample.entitysystem.ZoomEntitySystem;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class handles the keys and what it means for the various systems. (Direction, firing, zooming, etc)
 */
@Singleton
public class KeyboardManager extends InputAdapter {

  private final ZoomEntitySystem zoomEntitySystem;

  @Inject
  public KeyboardManager(final ZoomEntitySystem zoomEntitySystem) {
    this.zoomEntitySystem = zoomEntitySystem;
  }

  @Override
  public boolean keyDown(final int keycode) {
    switch (keycode) {
      case Input.Keys.UP:
        zoomEntitySystem.zoomUp();
        return true;
      case Input.Keys.DOWN:
        zoomEntitySystem.zoomDown();
        return true;
      default:
        return false;
    }
  }

  @Override
  public boolean keyUp(final int keycode) {
    switch (keycode) {
      case Input.Keys.UP:
        zoomEntitySystem.disableZoomUp();
        return true;
      case Input.Keys.DOWN:
        zoomEntitySystem.disableZoomDown();
        return true;
      default:
        return false;
    }
  }

}
