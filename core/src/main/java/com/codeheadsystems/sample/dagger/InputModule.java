package com.codeheadsystems.sample.dagger;

import com.badlogic.gdx.InputProcessor;
import com.codeheadsystems.sample.manager.KeyboardManager;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface InputModule {

  @Binds
  @IntoSet
  InputProcessor keyboardManager(KeyboardManager inputAdapter);

}
