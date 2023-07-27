package com.codeheadsystems.sample.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Pool;

/**
 * The type Tiled background component.
 */
public class TiledBackgroundComponent implements Pool.Poolable, Component {

  private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

  @Override
  public void reset() {
    orthogonalTiledMapRenderer = null;
  }

  /**
   * Orthogonal tiled map renderer orthogonal tiled map renderer.
   *
   * @return the orthogonal tiled map renderer
   */
  public OrthogonalTiledMapRenderer orthogonalTiledMapRenderer() {
    return orthogonalTiledMapRenderer;
  }

  /**
   * Set tiled background component.
   *
   * @param orthogonalTiledMapRenderer the orthogonal tiled map renderer
   * @return the tiled background component
   */
  public TiledBackgroundComponent set(OrthogonalTiledMapRenderer orthogonalTiledMapRenderer) {
    this.orthogonalTiledMapRenderer = orthogonalTiledMapRenderer;
    return this;
  }

}
