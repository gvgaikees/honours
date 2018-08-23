package tracks.levelGeneration.submission.tagextractor.extractors;

import core.game.GameDescription;
import tracks.levelGeneration.submission.tagextractor.Tag;

public class CanDestroySpriteExtractor extends Extractor{
    public CanDestroySpriteExtractor() {
        super(Extractor.DESTROY_SPRITES);
    }

    @Override
    public Tag extract(GameDescription description) {
        // Get all avatar sprites
        return new Tag(this.getType(), description.getAllSpriteData().stream()
                // that are not of type avatar...
                .anyMatch(sprite -> description.getAvatar().stream().noneMatch(avatar -> avatar.name.equals(sprite))
                        // AND check for all avatar sprites...
                        && description.getAvatar().stream()
                        // that interact with the sprite
                        .anyMatch(avatar -> description.getInteraction(sprite.name, avatar.name).stream()
                                // that leads to the death of the sprite.
                                .anyMatch(interaction -> interaction.type.equals("KillSprite"))
                        )
                )
        );
    }
}
