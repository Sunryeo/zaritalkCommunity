package zaritalkCommunity.zaritalkCommunity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@Getter @Setter
@RequiredArgsConstructor
public class ArticleListWithAuthResponseDto {

    @ApiModelProperty(name = "id", example = "1")
    private final BigInteger id;

    @ApiModelProperty(name = "title", example = "제목입니다.")
    private final String title;

    @ApiModelProperty(name = "body", example = "내용입니다.")
    private final String body;

    @ApiModelProperty(name = "nickname", example = "sunryeo")
    private final String nickname;

    @ApiModelProperty(name = "account_type", required = true, allowableValues = "LESSOR, REALTOR, LESSEE")
    private final String account_type;

    @ApiModelProperty(name = "created_at", example = "2023-02-09T15:37:09.756+00:00")
    private final Timestamp created_at;

    @ApiModelProperty(name = "updated_at", example = "2023-02-09T15:37:09.756+00:00")
    private final Timestamp updated_at;

    @ApiModelProperty(name = "like_cnt", example = "10")
    private final BigInteger like_cnt;

    @ApiModelProperty(name = "is_liked", example = "true", required = false)
    private final Boolean is_liked;
}
