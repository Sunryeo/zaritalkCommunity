package zaritalkCommunity.zaritalkCommunity.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomException extends RuntimeException {

    private CustomErrorCode customErrorCode;
    private int status;
    private String detailMessage;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getMessage());
        this.customErrorCode = customErrorCode;
        this.status = customErrorCode.getStatus();
        this.detailMessage = customErrorCode.getMessage();
    }
}
