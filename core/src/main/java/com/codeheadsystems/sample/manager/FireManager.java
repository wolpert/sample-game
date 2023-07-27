package com.codeheadsystems.sample.manager;

import static com.codeheadsystems.gamelib.core.util.LoggerHelper.logger;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Logger;
import javax.inject.Inject;

/**
 * The type Fire manager.
 */
public class FireManager extends InputAdapter {
  private static final Logger LOGGER = logger(FireManager.class);

  /**
   * Instantiates a new Fire manager.
   */
  @Inject
  public FireManager() {

  }

  @Override
  public boolean keyDown(final int keycode) {
    if (keycode == Input.Keys.SPACE) {
      LOGGER.debug("keyDown: " + keycode);
      return true;
    }
    return false;
  }

  @Override
  public boolean keyUp(final int keycode) {
    if (keycode == Input.Keys.SPACE) {
      LOGGER.debug("keyUp: " + keycode);
      return true;
    }
    return false;
  }
}
