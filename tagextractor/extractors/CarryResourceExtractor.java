package tracks.levelGeneration.submission.tagextractor.extractors;

import core.game.GameDescription;
import tracks.levelGeneration.submission.tagextractor.Tag;

public class CarryResourceExtractor extends Extractor{
    public CarryResourceExtractor() {
        super(Extractor.CARRY_RESOURCES);
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
                        .anyMatch(avatar -> description.getInteraction(avatar.name, sprite.name).stream()
                                // that leads to the change of a resource
                                .anyMatch(interaction -> interaction.type.equals("ChangeResource"))
                        )
                )
        );
    }
}
