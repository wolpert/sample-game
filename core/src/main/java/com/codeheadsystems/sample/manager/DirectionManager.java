package com.codeheadsystems.sample.manager;

import static com.codeheadsystems.gamelib.core.util.LoggerHelper.logger;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Logger;
import javax.inject.Inject;

public class DirectionManager extends InputAdapter {
  private static final Logger LOGGER = logger(DirectionManager.class);

  @Inject
  public DirectionManager() {

  }

  @Override
  public boolean keyDown(final int keycode) {
    switch (keycode) {
      case Input.Keys.W:
      case Input.Keys.A:
      case Input.Keys.S:
      case Input.Keys.D:
        LOGGER.debug("keyDown: " + keycode);
        return true;
      default:
        return false;
    }
  }

  @Override
  public boolean keyUp(final int keycode) {
    switch (keycode) {
      case Input.Keys.W:
      case Input.Keys.A:
      case Input.Keys.S:
      case Input.Keys.D:
        LOGGER.debug("keyUp: " + keycode);
        return true;
      default:
        return false;
    }
  }

  enum Direction {N, S, E, W, NE, NW, SE, SW}

}
