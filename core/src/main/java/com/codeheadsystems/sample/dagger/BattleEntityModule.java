package com.codeheadsystems.sample.dagger;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.codeheadsystems.gamelib.entity.component.SortComponent;
import com.codeheadsystems.gamelib.entity.component.SpriteComponent;
import com.codeheadsystems.gamelib.entity.entity.EntityGenerator;
import com.codeheadsystems.gamelib.entity.manager.EngineManager;
import com.codeheadsystems.sample.component.TiledBackgroundComponent;
import com.codeheadsystems.sample.entitysystem.TiledBackgroundEntitySystems;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import javax.inject.Singleton;

@Module(includes = {BattleEntityModule.Binder.class})
public class BattleEntityModule {

  @Provides
  @Singleton
  @IntoSet
  public EntityGenerator background(final EngineManager engineManager,
                                    final SpriteBatch spriteBatch,
                                    final AssetManager assetManager) {
    return () -> {
      TiledMap tiledMap = assetManager.get("field.tmx", TiledMap.class);
      OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(tiledMap,
          1 / 4f, spriteBatch);
      final Entity entity = engineManager.createEntity()
          .add(engineManager.createComponent(TiledBackgroundComponent.class).set(renderer));
      engineManager.addEntity(entity);
      return entity;
    };
  }

  @Provides
  @Singleton
  @IntoSet
  public EntityGenerator tank(final EngineManager engineManager,
                              final AssetManager assetManager,
                              final OrthographicCamera camera) {
    return () -> {
      final Texture img = assetManager.get("tank.png", Texture.class);
      final Sprite sprite = new Sprite(img);
      sprite.setX(0);
      sprite.setY(0);
      // sets the size based on the viewport....
      // note, probably should have a resize handler....
      float width = camera.viewportWidth / 3f;
      float height = width / img.getWidth() * img.getHeight();
      sprite.setSize(width, height);
      sprite.setCenter(camera.viewportWidth / 2f, camera.viewportHeight / 2f);
      final Entity entity = engineManager.createEntity()
          .add(engineManager.createComponent(SpriteComponent.class).sprite(sprite))
          .add(engineManager.createComponent(SortComponent.class));
      engineManager.addEntity(entity);
      return entity;
    };
  }

  /**
   * Binder methods only. Must be interfaces.
   */
  @Module
  interface Binder {

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
