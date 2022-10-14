package com.codeheadsystems.sample.dagger;

import com.badlogic.gdx.Screen;
import com.codeheadsystems.gamelib.core.dagger.GdxRuntimeCache;
import com.codeheadsystems.gamelib.core.dagger.GdxRuntimeCacheModule;
import com.codeheadsystems.gamelib.entity.dagger.GameLibEntityModule;
import com.codeheadsystems.gamelib.entity.entity.EntityScreen;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    GdxRuntimeCacheModule.class,
    GameLibEntityModule.class,
    BattleEntityModule.class,
    BattleGameScreenComponent.Binder.class})
public interface BattleGameScreenComponent {

  Screen screen();

  default BattleGameScreenComponent build(final GdxRuntimeCache cache){
    return DaggerBattleGameScreenComponent.builder()
        .gdxRuntimeCacheModule(new GdxRuntimeCacheModule(cache))
        .build();
  }

  @Module
  interface Binder {

    @Binds
    Screen entityScreen(EntityScreen screen);

  }


}
