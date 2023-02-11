package zaritalkCommunity.zaritalkCommunity.Exception;

public enum CustomErrorCode {
    DUPLICATE_ACCOUNT_ERROR("이미 존재하는 회원입니다.", 409),
    INVALID_ACCOUNT_ERROR("자리톡 회원만 이용할 수 있습니다.", 403),
    TITLE_LENGTH_OVER_ERROR("제목은 50자 미만이어야 합니다.", 400),
    BODY_LENGTH_OVER_ERROR("내용은 100자 미만이어야 합니다.", 400),
    UNAUTHORIZED_ACCOUNT_ERROR("글 작성자만 접근할 수 있습니다.", 401),
    ALREADY_LIKED_ERROR("이미 좋아요한 게시물입니다.", 400);

    private final String message;
    private final int status;

    private CustomErrorCode(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
