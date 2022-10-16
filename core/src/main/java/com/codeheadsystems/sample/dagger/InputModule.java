package com.codeheadsystems.sample.dagger;

import com.badlogic.gdx.InputAdapter;
import com.codeheadsystems.sample.manager.DirectionManager;
import com.codeheadsystems.sample.manager.FireManager;
import com.codeheadsystems.sample.manager.ZoomManager;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface InputModule {

  @Binds
  @IntoSet
  InputAdapter directionManager(DirectionManager inputAdapter);

  @Binds
  @IntoSet
  InputAdapter fireManager(FireManager inputAdapter);

  @Binds
  @IntoSet
  InputAdapter zoomManager(ZoomManager inputAdapter);

}
