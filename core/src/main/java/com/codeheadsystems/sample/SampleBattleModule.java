/*
 *   Copyright (c) 2022. Ned Wolpert <ned.wolpert@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.codeheadsystems.sample;

import static com.codeheadsystems.gamelib.core.dagger.GameResources.RESOURCE_PATH;
import static com.codeheadsystems.gamelib.core.dagger.LoadingModule.MAIN_SCREEN;
import static com.codeheadsystems.gamelib.entity.dagger.GameLibEntityModule.ENTITY_SCREEN_SHOW_CONSUMER;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Screen;
import com.codeheadsystems.gamelib.entity.entity.EntityScreen;
import com.codeheadsystems.sample.dagger.BattleEntityModule;
import com.codeheadsystems.sample.entitysystem.TiledBackgroundEntitySystems;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import java.util.function.Consumer;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(includes = {SampleBattleModule.Binder.class, BattleEntityModule.class})
public class SampleBattleModule {

  @Provides
  @Singleton
  @Named(RESOURCE_PATH)
  String resourcePath() {
    return "battle/";
  }

  /**
   * You cannot use Textures or other things that are loaded by the asset manager during
   * dagger injection. The asset manager has to have loaded everything up... which only
   * happens during the loading screen. You can have your own entity screen that creates
   * the sprites if you want, or use the method shown below.
   */
  @Provides
  @Singleton
  @Named(ENTITY_SCREEN_SHOW_CONSUMER)
  Consumer<EntityScreen> showConsumer() {
    return screen -> {
    };
  }

  /**
   * Binder methods only. Must be interfaces.
   */
                               @Module
  interface Binder {

    /**
     * Here, we want the first screen to be used once loaded as the entity screen.
     * You can actually setup your own screen first that once the game starts, goes
     * to the entity screen.
     */
    @Binds
    @Named(MAIN_SCREEN)
    Screen mainScreen(EntityScreen impl);

    /**
     * Add our tiled entity set.
     *
     * @param entitySystems we are binding.
     * @return an entity system.
     */
    @Binds
    @IntoSet
    EntitySystem tiledBackground(TiledBackgroundEntitySystems entitySystems);
  }
}
