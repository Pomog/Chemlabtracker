package java2.eln.core.responses;

public class DelReactionResponse extends CoreResponse{
    private boolean delResult;

    public boolean getDelResult() {
        return delResult;
    }

    public DelReactionResponse(boolean delResult) {
        this.delResult = delResult;
    }
}
