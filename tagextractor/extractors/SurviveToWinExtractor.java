package tracks.levelGeneration.submission.tagextractor.extractors;

import core.game.GameDescription;
import tracks.levelGeneration.submission.tagextractor.Tag;

public class SurviveToWinExtractor extends Extractor{
    public SurviveToWinExtractor() {
        super(Extractor.SURVIVE_FOR_TIME);
    }

    @Override
    public Tag extract(GameDescription description) {
        return new Tag(this.getType(), description.getTerminationConditions().stream()
                // any termination condition that leads to a win..
                .anyMatch(terminationData -> terminationData.win.equals("True")
                        // Check if it's a timeout
                        && terminationData.type.equals("Timeout")
                )
        );
    }
}
