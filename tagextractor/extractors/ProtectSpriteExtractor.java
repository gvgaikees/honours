package tracks.levelGeneration.submission.tagextractor.extractors;

import core.game.GameDescription;
import tracks.levelGeneration.submission.tagextractor.Tag;

/**
 * This extractor checks whether it is imperative for a sprite to be protected.
 */
public class ProtectSpriteExtractor extends Extractor {

    public ProtectSpriteExtractor() {
        super(Extractor.PROTECT_SPRITES);
    }

    @Override
    public Tag extract(GameDescription description) {
        return new Tag(this.getType(), description.getTerminationConditions().stream()
                // any termination condition that leads to a loss..
                .anyMatch(terminationData -> terminationData.win.equals("False")
                        // which is of type (Multi)SpriteCounter
                        && (terminationData.type.equals("SpriteCounter") || terminationData.type.equals("MultiSpriteCounter"))
                        // AND for which any sprite..
                        && terminationData.sprites.stream()
                        // that is not of type avatar...
                        .anyMatch(sprite -> description.getAvatar().stream().noneMatch(avatar -> avatar.name.equals(sprite))
                        // AND check for all sprites...
                        && description.getAllSpriteData().stream()
                                // that do not interact with avatar
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