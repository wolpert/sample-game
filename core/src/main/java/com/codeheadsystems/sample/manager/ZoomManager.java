package com.codeheadsystems.sample.manager;

import static com.codeheadsystems.gamelib.core.util.LoggerHelper.logger;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Logger;
import javax.inject.Inject;

public class ZoomManager extends InputAdapter {
  private static final Logger LOGGER = logger(ZoomManager.class);

  @Inject
  public ZoomManager() {
  }

  @Override
  public boolean keyDown(final int keycode) {
    switch (keycode) {
      case Input.Keys.UP:
      case Input.Keys.DOWN:
        LOGGER.debug("keyDown: " + keycode);
        return true;
      default:
        return false;
    }
  }

  @Override
  public boolean keyUp(final int keycode) {
    switch (keycode) {
      case Input.Keys.UP:
      case Input.Keys.DOWN:
        LOGGER.debug("keyUp: " + keycode);
        return true;
      default:
        return false;
    }
  }
}
