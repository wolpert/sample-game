package com.codeheadsystems.sample.entitysystem;

import static com.codeheadsystems.gamelib.core.util.LoggerHelper.logger;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class manages what the zoom is for the camera. Something else has to tell it to enable zooming (up or down)
 * and when to disable it.
 */
@Singleton
public class ZoomEntitySystem extends EntitySystem {
  private static final Logger LOGGER = logger(ZoomEntitySystem.class);
  private static final float ZOOM = 1f;
  private static final float MAX_ZOOM_OUT = 1f;
  private static final float MIN_ZOOM_IN = 0.1f;

  private final OrthographicCamera camera;
  private Zoom zoom = Zoom.NONE;

  /**
   * Instantiates a new Zoom entity system.
   *
   * @param camera the camera
   */
  @Inject
  public ZoomEntitySystem(final OrthographicCamera camera) {
    this.camera = camera;
  }

  /**
   * Zoom up.
   */
  public void zoomUp() {
    zoom = Zoom.UP;
  }

  /**
   * Zoom down.
   */
  public void zoomDown() {
    zoom = Zoom.DOWN;
  }

  /**
   * Disable zoom up.
   */
  public void disableZoomUp() {
    if (zoom.equals(Zoom.UP)) {
      zoom = Zoom.NONE;
    }
  }

  /**
   * Disable zoom down.
   */
  public void disableZoomDown() {

    if (zoom.equals(Zoom.DOWN)) {
      zoom = Zoom.NONE;
    }
  }

  @Override
  public void update(final float deltaTime) {
    switch (zoom) {
      case DOWN -> zoomOut(deltaTime);
      case UP -> zoomIn(deltaTime);
    }
  }

  private void zoomOut(final float deltaTime) {
    float zoom = camera.zoom + ZOOM * deltaTime;
    camera.zoom = Math.min(zoom, MAX_ZOOM_OUT);
  }

  private void zoomIn(final float deltaTime) {
    float zoom = camera.zoom - ZOOM * deltaTime;
    camera.zoom = Math.max(zoom, MIN_ZOOM_IN);
  }


  /**
   * The enum Zoom.
   */
  enum Zoom {
    /**
     * Down zoom.
     */
    DOWN,
    /**
     * Up zoom.
     */
    UP,
    /**
     * None zoom.
     */
    NONE}

}
