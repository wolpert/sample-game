package com.codeheadsystems.sample.dagger;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.codeheadsystems.gamelib.core.dagger.GdxRuntimeCache;
import com.codeheadsystems.gamelib.core.dagger.GdxRuntimeCacheModule;
import com.codeheadsystems.gamelib.entity.dagger.GameLibEntityModule;
import com.codeheadsystems.sample.screen.FieldScreen;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    GdxRuntimeCacheModule.class,
    InputModule.class,
    GameLibEntityModule.class,
    BattleEntityModule.class,
    BattleGameScreenComponent.Binder.class})
public interface BattleGameScreenComponent {

  static BattleGameScreenComponent build(final GdxRuntimeCache cache) {
    Gdx.app.setLogLevel(Application.LOG_DEBUG); // TODO: make this setable
    return DaggerBattleGameScreenComponent.builder()
        .gdxRuntimeCacheModule(new GdxRuntimeCacheModule(cache))
        .build();
  }

  Screen screen();

  @Module
  interface Binder {

    @Binds
    Screen entityScreen(FieldScreen screen);

  }


}
