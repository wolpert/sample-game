package com.codeheadsystems.sample.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.codeheadsystems.sample.model.GameConstants;

public class GameConstantsLoader extends AsynchronousAssetLoader<GameConstants, GameConstantsLoader.Parameter> {
  private final Json json;

  public GameConstantsLoader(FileHandleResolver resolver) {
    super(resolver);
    json = new Json();
  }

  @Override
  public void loadAsync(AssetManager manager, String fileName, FileHandle file, Parameter parameter) {
  }

  @Override
  public GameConstants loadSync(AssetManager manager, String fileName, FileHandle file, Parameter parameter) {
    return json.fromJson(GameConstants.class, file);
  }

  @Override
  public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, Parameter parameter) {
    return null;
  }

  public static class Parameter extends AssetLoaderParameters<GameConstants> {

  }

}

