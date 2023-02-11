package zaritalkCommunity.zaritalkCommunity.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class CustomErrorResponse {
    private CustomErrorCode errorCode;
    private int status;
    private String errorMessage;
}
