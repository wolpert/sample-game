package com.codeheadsystems.sample.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Pool;

public class TiledBackgroundComponent implements Pool.Poolable, Component {

  private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

  @Override
  public void reset() {
    orthogonalTiledMapRenderer = null;
  }

  public OrthogonalTiledMapRenderer orthogonalTiledMapRenderer() {
    return orthogonalTiledMapRenderer;
  }

  public TiledBackgroundComponent set(OrthogonalTiledMapRenderer orthogonalTiledMapRenderer) {
    this.orthogonalTiledMapRenderer = orthogonalTiledMapRenderer;
    return this;
  }

}
