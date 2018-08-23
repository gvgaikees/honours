package tracks.levelGeneration.submission.tagextractor;

import core.game.GameDescription;
import core.generator.AbstractLevelGenerator;
import tools.ElapsedCpuTimer;
import tracks.levelGeneration.submission.levelGenerators.LevelGenerator0;
import tracks.levelGeneration.submission.levelGenerators.LevelGenerator1;
import tracks.levelGeneration.submission.levelGenerators.LevelGenerator2;
import tracks.levelGeneration.submission.levelGenerators.LevelGenerator3;
import tracks.levelGeneration.submission.tagextractor.extractors.Extractor;

import java.util.Map;

public class Decider {
    private Tagger extractor;

    public Decider() {
        extractor = new Tagger();
    }

    public AbstractLevelGenerator decide(GameDescription description, ElapsedCpuTimer elapsedCpuTimer) {
        Map<Integer, Tag> tags = extractor.extractSingleGame(description);

        if (tags.get(Extractor.CARRY_RESOURCES).present) {
            return new LevelGenerator0(description, elapsedCpuTimer);
        } else if (tags.get(Extractor.MOVE_OBSTACLES).present) {
            return new LevelGenerator1(description, elapsedCpuTimer);
        } else if (tags.get(Extractor.COLLECT_SPRITES).present
                && !tags.get(Extractor.SURVIVE_FOR_TIME).present
                && tags.get(Extractor.DESTROY_SPRITES).present
                ) {
            return new LevelGenerator2(description, elapsedCpuTimer);
        } else {
            return new LevelGenerator3(description, elapsedCpuTimer);
        }
    }
}
