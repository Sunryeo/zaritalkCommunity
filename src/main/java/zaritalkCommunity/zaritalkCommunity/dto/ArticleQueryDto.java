package zaritalkCommunity.zaritalkCommunity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import zaritalkCommunity.zaritalkCommunity.domain.AccountType;

@Data
@Getter @Setter
@RequiredArgsConstructor
public class ArticleQueryDto {

    @ApiModelProperty(name = "id", example = "1")
    private final Long id;
    @ApiModelProperty(name = "like_cnt", example = "10")
    private final int like_cnt;
    @ApiModelProperty(name = "title", example = "제목입니다.")
    private final String title;
    @ApiModelProperty(name = "body", example = "내용입니다.")
    private final String body;
    @ApiModelProperty(name = "nickname", example = "sunryeo")
    private final String nickname;
    @ApiModelProperty(name = "account_type", required = true, allowableValues = "LESSOR, REALTOR, LESSEE")
    private final AccountType account_type;

    private String is_liked;
}
