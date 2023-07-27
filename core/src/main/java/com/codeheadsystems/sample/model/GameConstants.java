package com.codeheadsystems.sample.model;

/**
 * The type Game constants.
 */
public class GameConstants {

  private Tank tank;

  /**
   * Gets tank.
   *
   * @return the tank
   */
  public Tank getTank() {
    return tank;
  }

  /**
   * The type Tank.
   */
  public static class Tank {

    private int rotation;
    private float maxSpeed;

    /**
     * Gets rotation.
     *
     * @return the rotation
     */
    public int getRotation() {
      return rotation;
    }

    /**
     * Gets max speed.
     *
     * @return the max speed
     */
    public float getMaxSpeed() {
      return maxSpeed;
    }
  }
}
