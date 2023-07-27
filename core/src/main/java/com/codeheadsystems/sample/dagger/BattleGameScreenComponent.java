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

/**
 * The interface Battle game screen component.
 */
@Singleton
@Component(modules = {
    GdxRuntimeCacheModule.class,
    InputModule.class,
    GameLibEntityModule.class,
    BattleEntityModule.class,
    BattleGameScreenComponent.Binder.class})
public interface BattleGameScreenComponent {

  /**
   * Build battle game screen component.
   *
   * @param cache the cache
   * @return the battle game screen component
   */
  static BattleGameScreenComponent build(final GdxRuntimeCache cache) {
    Gdx.app.setLogLevel(Application.LOG_DEBUG); // TODO: make this setable
    return DaggerBattleGameScreenComponent.builder()
        .gdxRuntimeCacheModule(new GdxRuntimeCacheModule(cache))
        .build();
  }

  /**
   * Screen screen.
   *
   * @return the screen
   */
  Screen screen();

  /**
   * The interface Binder.
   */
  @Module
  interface Binder {

    /**
     * Entity screen screen.
     *
     * @param screen the screen
     * @return the screen
     */
    @Binds
    Screen entityScreen(FieldScreen screen);

  }


}
