package java2.eln.core.requests;

public class DeleteReactionRequest {
    private final String code;

    public DeleteReactionRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
