package zaritalkCommunity.zaritalkCommunity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import zaritalkCommunity.zaritalkCommunity.domain.AccountType;

import java.time.LocalDateTime;

@Data
@Getter @Setter
@RequiredArgsConstructor
public class ArticleQueryDto {

    @ApiModelProperty(name = "id", example = "1")
    private final Long id;

    @ApiModelProperty(name = "title", example = "제목입니다.")
    private final String title;

    @ApiModelProperty(name = "body", example = "내용입니다.")
    private final String body;

    @ApiModelProperty(name = "nickname", example = "sunryeo")
    private final String nickname;

    @ApiModelProperty(name = "account_type", required = true, allowableValues = "LESSOR, REALTOR, LESSEE")
    private final AccountType account_type;

    @ApiModelProperty(name = "created_at", example = "2023-02-09T15:37:09.756+00:00")
    private final LocalDateTime created_at;

    @ApiModelProperty(name = "created_at", example = "2023-02-09T15:37:09.756+00:00")
    private final LocalDateTime updated_at;

    @ApiModelProperty(name = "like_cnt", example = "10")
    private final int like_cnt;
}
