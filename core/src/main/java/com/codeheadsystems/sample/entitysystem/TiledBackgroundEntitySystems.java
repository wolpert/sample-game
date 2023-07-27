package com.codeheadsystems.sample.entitysystem;

import static com.codeheadsystems.gamelib.core.util.LoggerHelper.logger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Logger;
import com.codeheadsystems.gamelib.entity.entitysystem.Priorities;
import com.codeheadsystems.gamelib.entity.entitysystem.WrappedIteratingEntitySystem;
import com.codeheadsystems.sample.component.TiledBackgroundComponent;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The type Tiled background entity systems.
 */
@Singleton
public class TiledBackgroundEntitySystems extends WrappedIteratingEntitySystem {

  private static final Logger LOGGER = logger(TiledBackgroundEntitySystems.class);
  private static final ComponentMapper<TiledBackgroundComponent> MAPPER = ComponentMapper.getFor(TiledBackgroundComponent.class);
  private final OrthographicCamera orthographicCamera;

  /**
   * Instantiates a new Tiled background entity systems.
   *
   * @param orthographicCamera the orthographic camera
   */
  @Inject
  public TiledBackgroundEntitySystems(final OrthographicCamera orthographicCamera) {
    super(Family.all(TiledBackgroundComponent.class).get(), Priorities.BACKGROUND.priority());
    this.orthographicCamera = orthographicCamera;
  }

  @Override
  public void processEntity(final Entity entity, final float deltaTime) {
    final TiledBackgroundComponent component = MAPPER.get(entity);
    final OrthogonalTiledMapRenderer orthogonalTiledMapRenderer = component.orthogonalTiledMapRenderer();
    orthogonalTiledMapRenderer.setView(orthographicCamera);
    orthogonalTiledMapRenderer.render();
  }
}
