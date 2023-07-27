package com.codeheadsystems.sample.dagger;

import com.badlogic.gdx.InputProcessor;
import com.codeheadsystems.sample.manager.KeyboardManager;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/**
 * The interface Input module.
 */
@Module
public interface InputModule {

  /**
   * Keyboard manager input processor.
   *
   * @param inputAdapter the input adapter
   * @return the input processor
   */
  @Binds
  @IntoSet
  InputProcessor keyboardManager(KeyboardManager inputAdapter);

}
