package tracks.levelGeneration.submission.tagextractor.extractors;

import core.game.GameDescription;
import tracks.levelGeneration.submission.tagextractor.Tag;

public abstract class Extractor {
    public static final int PROTECT_SPRITES     = 0;
    public static final int COLLECT_SPRITES     = 1;
    public static final int PLAYER_CAN_DIE      = 2;
    public static final int SURVIVE_FOR_TIME    = 3;
    public static final int DESTROY_SPRITES     = 4;
    public static final int TIMEOUT_SET         = 5;
    public static final int MOVE_OBSTACLES      = 6;
    public static final int CARRY_RESOURCES     = 7;

    private int type;

    public Extractor(int type) {
        this.type = type;
    }

    public abstract Tag extract(GameDescription description);

    public int getType() {
        return type;
    }
}
