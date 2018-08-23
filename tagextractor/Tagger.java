package tracks.levelGeneration.submission.tagextractor;

import core.game.Game;
import core.game.GameDescription;
import core.vgdl.VGDLFactory;
import core.vgdl.VGDLParser;
import core.vgdl.VGDLRegistry;
import tracks.levelGeneration.submission.tagextractor.extractors.*;

import java.util.*;

public class Tagger {

    private List<Extractor> extractors = new LinkedList<>();

    public Tagger() {
        extractors.add(new ProtectSpriteExtractor());
        extractors.add(new CollectSpriteExtractor());
        extractors.add(new PlayerDieExtractor());
        extractors.add(new SurviveToWinExtractor());
        extractors.add(new CanDestroySpriteExtractor());
        extractors.add(new TimeoutExtractor());
        extractors.add(new MoveSpriteExtractor());
        extractors.add(new CarryResourceExtractor());
    }

    public Map<Integer, Tag> extractSingleGame(String gamePath){
        VGDLFactory.GetInstance().init();
        VGDLRegistry.GetInstance().init();

        Game game = new VGDLParser().parseGame(gamePath);
        GameDescription description = new GameDescription(game);

        return extractSingleGame(description);
    }

    public Map<Integer, Tag> extractSingleGame(GameDescription description){
        Map<Integer, Tag> tags = new LinkedHashMap<>();
        extractors.forEach(
                extractor -> tags.put(extractor.getType(), extractor.extract(description)));

        return tags;
    }

    public List<Map<Integer, Tag>> extractGames(String[][] games) {
        // Create tags
        List<Map<Integer, Tag>> tags = new ArrayList<>();

        // Iterate and assign extractions
        for (int progress = 0; progress < games.length; progress++) {
            System.out.print("\rExtraction progress: " + progress + "/" + (games.length - 1));
            tags.add(extractSingleGame(games[progress][0]));
        }
        System.out.println("\nFinished Tag Extraction");
        return tags;
    }

}
