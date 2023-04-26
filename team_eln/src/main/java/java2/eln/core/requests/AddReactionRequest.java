package java2.eln.core.requests;

public class AddReactionRequest {
    String code;
    String name;
    String filename = "team_eln/data/demoReaction1.txt";

    public AddReactionRequest(String code, String name, String filename) {
        this.code = code;
        this.name = name;
        this.filename = filename;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }
}
