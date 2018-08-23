package tracks.levelGeneration.submission;

import core.game.GameDescription;
import core.game.GameDescription.SpriteData;
import core.generator.AbstractLevelGenerator;
import tools.ElapsedCpuTimer;
import tools.GameAnalyzer;
import tracks.levelGeneration.submission.tagextractor.Decider;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;

public class LevelGenerator extends AbstractLevelGenerator {
	Decider decider;
	AbstractLevelGenerator levelGenerator;

	public LevelGenerator(GameDescription game, ElapsedCpuTimer elapsedTimer) {
		decider = new Decider();
		levelGenerator = decider.decide(game, elapsedTimer);
	}

	@Override
	public String generateLevel(GameDescription game, ElapsedCpuTimer elapsedTimer) {
		return levelGenerator.generateLevel(game, elapsedTimer);
	}
}
