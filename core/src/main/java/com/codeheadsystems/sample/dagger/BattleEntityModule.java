package com.codeheadsystems.sample.dagger;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.codeheadsystems.gamelib.entity.component.SortComponent;
import com.codeheadsystems.gamelib.entity.component.SpriteComponent;
import com.codeheadsystems.gamelib.entity.entity.EntityGenerator;
import com.codeheadsystems.gamelib.entity.manager.EngineManager;
import com.codeheadsystems.sample.component.TiledBackgroundComponent;
import com.codeheadsystems.sample.entitysystem.TankEntitySystem;
import com.codeheadsystems.sample.entitysystem.TiledBackgroundEntitySystems;
import com.codeheadsystems.sample.entitysystem.ZoomEntitySystem;
import com.codeheadsystems.sample.model.GameConstants;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(includes = {BattleEntityModule.Binder.class})
public class BattleEntityModule {

  public static final String TANK_SPRITE = "tankSprite";
  private static final float UNIT_SCALE = 1 / 4f;

  @Provides
  @Singleton
  public GameConstants gameConstants(final AssetManager assetManager){
    return assetManager.get("game_constants.json", GameConstants.class);
  }

  @Provides
  @Singleton
  @IntoSet
  public EntityGenerator background(final EngineManager engineManager,
                                    final SpriteBatch spriteBatch,
                                    final AssetManager assetManager) {
    return () -> {
      final TiledMap tiledMap = assetManager.get("field.tmx", TiledMap.class);
      final OrthogonalTiledMapRenderer renderer =
          new OrthogonalTiledMapRenderer(tiledMap, UNIT_SCALE, spriteBatch);
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
                              @Named(TANK_SPRITE) final Sprite tankSprite) {
    return () -> {
      final Entity entity = engineManager.createEntity()
          .add(engineManager.createComponent(SpriteComponent.class).sprite(tankSprite))
          .add(engineManager.createComponent(SortComponent.class));
      engineManager.addEntity(entity);
      return entity;
    };
  }

  @Provides
  @Singleton
  @Named("TILE_WIDTH")
  public int tileWidth(final AssetManager assetManager) {
    final TiledMap tiledMap = assetManager.get("field.tmx", TiledMap.class);
    final MapProperties prop = tiledMap.getProperties();
    return prop.get("tilewidth", Integer.class);
  }

  @Provides
  @Singleton
  @Named("TILE_HEIGHT")
  public int tileHeight(final AssetManager assetManager) {
    final TiledMap tiledMap = assetManager.get("field.tmx", TiledMap.class);
    final MapProperties prop = tiledMap.getProperties();
    return prop.get("tileheight", Integer.class);
  }

  @Provides
  @Singleton
  @Named(TANK_SPRITE)
  public Sprite tankSprite(final AssetManager assetManager,
                           @Named("TILE_WIDTH") final int tileWidth,
                           @Named("TILE_HEIGHT") final int tileHeight,
                           final OrthographicCamera camera) {
    final Texture img = assetManager.get("tank.png", Texture.class);
    final Sprite sprite = new Sprite(img);
    sprite.setSize(tileWidth * UNIT_SCALE, tileHeight * UNIT_SCALE);
    sprite.setOrigin(tileWidth * UNIT_SCALE / 2f, tileHeight * UNIT_SCALE / 2f);
    sprite.setCenter(camera.viewportWidth / 2f, camera.viewportHeight / 2f); // where we start, need to fix this.
    camera.position.set(sprite.getX(), sprite.getY(), 0); // reset to get rid of weirdness.
    return sprite;
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

    @Binds
    @IntoSet
    EntitySystem zoomEntitySystem(ZoomEntitySystem entitySystem);

    @Binds
    @IntoSet
    EntitySystem tankEntitySystem(TankEntitySystem tankEntitySystem);
  }
}
