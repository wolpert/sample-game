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

package com.codeheadsystems.sample.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.codeheadsystems.gamelib.desktop.dagger.GameLibModule;
import com.codeheadsystems.gamelib.entity.dagger.GameLibEntityModule;
import com.codeheadsystems.sample.SampleEntityModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * The type Entity desktop launcher.
 */
public class EntityDesktopLauncher {


  /**
   * The entry point of application.
   *
   * @param arg the input arguments
   */
  public static void main(String[] arg) {
    DaggerEntityDesktopLauncher_EntityDesktopComponent.builder()
        .build()
        .application();
  }

  /**
   * The interface Entity desktop component.
   */
  @Singleton
  @Component(modules = {
      SampleEntityModule.class,
      GameLibModule.class,
      GameLibEntityModule.class
  })
  public interface EntityDesktopComponent {

    /**
     * Application lwjgl 3 application.
     *
     * @return the lwjgl 3 application
     */
    Lwjgl3Application application();

  }

}
