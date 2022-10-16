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
import static com.codeheadsystems.gamelib.core.dagger.LoadingModule.MAIN_SCREEN_PROVIDER;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Screen;
import com.codeheadsystems.gamelib.core.dagger.GdxRuntimeCache;
import com.codeheadsystems.gamelib.core.dagger.GdxRuntimeCacheModule;
import com.codeheadsystems.gamelib.entity.entity.EntityScreen;
import com.codeheadsystems.sample.dagger.BattleEntityModule;
import com.codeheadsystems.sample.dagger.BattleGameScreenComponent;
import com.codeheadsystems.sample.dagger.DaggerBattleGameScreenComponent;
import com.codeheadsystems.sample.entitysystem.TiledBackgroundEntitySystems;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import java.util.function.Function;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class SampleBattleModule {

  @Provides
  @Singleton
  @Named(RESOURCE_PATH)
  String resourcePath() {
    return "battle/";
  }

  @Provides
  @Singleton
  @Named(MAIN_SCREEN_PROVIDER)
  Function<GdxRuntimeCache, Screen> mainScreenProvider() {
    return (gdx) -> BattleGameScreenComponent.build(gdx).screen();
  }

}
