package com.codeheadsystems.sample.manager;

import static com.codeheadsystems.gamelib.core.util.LoggerHelper.logger;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Logger;
import javax.inject.Inject;

public class FireManager extends InputAdapter {
  private static final Logger LOGGER = logger(FireManager.class);

  @Inject
  public FireManager() {

  }

  @Override
  public boolean keyDown(final int keycode) {
    switch (keycode) {
      case Input.Keys.SPACE:
        LOGGER.debug("keyDown: " + keycode);
        return true;
      default:
        return false;
    }
  }

  @Override
  public boolean keyUp(final int keycode) {
    switch (keycode) {
      case Input.Keys.SPACE:
        LOGGER.debug("keyUp: " + keycode);
        return true;
      default:
        return false;
    }
  }
}
