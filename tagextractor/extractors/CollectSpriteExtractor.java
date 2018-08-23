package tracks.levelGeneration.submission.tagextractor.extractors;

import core.game.GameDescription;
import tracks.levelGeneration.submission.tagextractor.Tag;

public class CollectSpriteExtractor extends Extractor{
    public CollectSpriteExtractor() {
        super(Extractor.COLLECT_SPRITES);
    }

    @Override
    public Tag extract(GameDescription description) {
        return new Tag(this.getType(), description.getTerminationConditions().stream()
                // any termination condition that leads to a win..
                .anyMatch(terminationData -> terminationData.win.equals("True")
                        // which is of type (Multi)SpriteCounter
                        && (terminationData.type.equals("SpriteCounter") || terminationData.type.equals("MultiSpriteCounter"))
                        // AND for which any sprites..
                        && terminationData.sprites.stream()
                        // that are not of type avatar...
                        .anyMatch(sprite -> description.getAvatar().stream().noneMatch(avatar -> avatar.name.equals(sprite))
                                // AND check for all avatar sprites...
                                && description.getAvatar().stream()
                                // that interact with the sprite
                                .anyMatch(avatar -> description.getInteraction(sprite, avatar.name).stream()
                                        // that leads to the death of the sprite.
                                        .anyMatch(interaction -> interaction.type.equals("KillSprite"))
                                )
                        )
                )
        );
    }
}
