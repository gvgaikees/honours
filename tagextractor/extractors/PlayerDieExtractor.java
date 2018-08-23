package tracks.levelGeneration.submission.tagextractor.extractors;

import core.game.GameDescription;
import tracks.levelGeneration.submission.tagextractor.Tag;

public class PlayerDieExtractor extends Extractor {
    public PlayerDieExtractor() {
        super(Extractor.PLAYER_CAN_DIE);
    }

    @Override
    public Tag extract(GameDescription description) {
        return new Tag(this.getType(), description.getTerminationConditions().stream()
                // any termination condition that leads to a loss..
                .anyMatch(terminationData -> terminationData.win.equals("False")
                        // which is of type (Multi)SpriteCounter
                        && (terminationData.type.equals("SpriteCounter") || terminationData.type.equals("MultiSpriteCounter"))
                        // AND for which all sprites..
                        && terminationData.sprites.stream()
                        // that are of type avatar...
                        .anyMatch(sprite -> description.getAvatar().stream().anyMatch(avatar -> avatar.name.equals(sprite))
                                // AND check for all sprites...
                                && description.getAllSpriteData().stream()
                                // that is not avatar
                                .anyMatch(checkSprite -> description.getAvatar().stream().noneMatch(avatar -> avatar.name.equals(checkSprite.name))
                                        // but does interact with another sprite
                                        && description.getInteraction(sprite, checkSprite.name).stream()
                                        // that leads to the death of the sprite.
                                        .anyMatch(interaction -> interaction.type.equals("KillSprite"))
                                )
                        )
                )
        );
    }
}
