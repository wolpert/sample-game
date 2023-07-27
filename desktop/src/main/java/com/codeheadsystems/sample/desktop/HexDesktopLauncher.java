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
import com.codeheadsystems.sample.SampleHexScreen;
import dagger.Component;
import javax.inject.Singleton;

/**
 * This is the basic desktop launcher. It is used as a default example in how to get the default
 * GDX application up and running without any extras.
 */
public class HexDesktopLauncher {
  /**
   * The entry point of application.
   *
   * @param arg the input arguments
   */
  public static void main(String[] arg) {
    DaggerHexDesktopLauncher_HexDesktopComponent.builder()
        .build()
        .application();
  }

  /**
   * The interface Hex desktop component.
   */
  @Singleton
  @Component(modules = {GameLibModule.class, SampleHexScreen.HexModule.class})
  public interface HexDesktopComponent {

    /**
     * Application lwjgl 3 application.
     *
     * @return the lwjgl 3 application
     */
    Lwjgl3Application application();

  }
}
