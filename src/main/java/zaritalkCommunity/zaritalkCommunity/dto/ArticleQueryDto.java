package zaritalkCommunity.zaritalkCommunity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import zaritalkCommunity.zaritalkCommunity.domain.AccountType;

@Data
@Getter
@AllArgsConstructor
public class ArticleQueryDto {

    @ApiModelProperty(name = "id", example = "1")
    private Long id;
    @ApiModelProperty(name = "like_count", example = "10")
    private Long like_count;
    @ApiModelProperty(name = "title", example = "제목입니다.")
    private String title;
    @ApiModelProperty(name = "body", example = "내용입니다.")
    private String body;
    @ApiModelProperty(name = "nickname", example = "sunryeo")
    private String nickname;
    @ApiModelProperty(name = "account_type", required = true, allowableValues = "LESSOR, REALTOR, LESSEE")
    private AccountType account_type;
}
