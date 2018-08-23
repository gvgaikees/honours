package tracks.levelGeneration.submission.tagextractor.extractors;

import core.game.GameDescription;
import tracks.levelGeneration.submission.tagextractor.Tag;

public class TimeoutExtractor extends Extractor{
    public TimeoutExtractor() {
        super(Extractor.TIMEOUT_SET);
    }

    @Override
    public Tag extract(GameDescription description) {
        return new Tag(this.getType(), description.getTerminationConditions().stream()
                // any termination condition that leads to a win..
                .anyMatch(terminationData -> terminationData.win.equals("False")
                        // Check if it's a timeout
                        && terminationData.type.equals("Timeout")
                )
        );
    }
}
