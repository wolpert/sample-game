package com.codeheadsystems.sample.screen;

import static com.codeheadsystems.gamelib.core.util.LoggerHelper.logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Logger;
import com.codeheadsystems.gamelib.entity.entity.EntityGenerator;
import com.codeheadsystems.gamelib.entity.manager.EngineManager;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FieldScreen extends ScreenAdapter {

  private static final Logger LOGGER = logger(FieldScreen.class);

  private final EngineManager engineManager;
  private final Set<EntityGenerator> entityGenerators;
  private final InputMultiplexer multiplexer;

  @Inject
  public FieldScreen(final EngineManager engineManager,
                     final Set<EntityGenerator> entityGenerators,
                     final Set<InputAdapter> inputAdapters) {
    this.engineManager = engineManager;
    this.entityGenerators = entityGenerators;
    multiplexer = new InputMultiplexer();
    inputAdapters.forEach(multiplexer::addProcessor);
    Gdx.input.setInputProcessor(multiplexer);
  }

  @Override
  public void show() {
    super.show();
    entityGenerators.forEach(EntityGenerator::generate);
  }


  @Override
  public void render(final float delta) {
    super.render(delta);
    engineManager.update(delta);
  }
}
