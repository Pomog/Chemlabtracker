package java2.eln.core.requests;

public class DelReactionRequest {
    private final String code;

    public DelReactionRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
