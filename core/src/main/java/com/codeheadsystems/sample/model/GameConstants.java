package com.codeheadsystems.sample.model;

public class GameConstants {

  private Tank tank;

  public Tank getTank() {
    return tank;
  }

  public static class Tank {

    private int rotation;
    private float maxSpeed;

    public int getRotation() {
      return rotation;
    }

    public float getMaxSpeed() {
      return maxSpeed;
    }
  }
}
