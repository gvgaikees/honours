package tracks.levelGeneration.submission.tagextractor;

public class Tag {

    // The type of Tag
    public int type;

    // Whether the tag is found
    public boolean present;

    public Tag(int type, boolean present) {
        this.type = type;
        this.present = present;
    }
}
