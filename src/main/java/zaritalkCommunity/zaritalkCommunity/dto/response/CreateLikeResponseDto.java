package zaritalkCommunity.zaritalkCommunity.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import zaritalkCommunity.zaritalkCommunity.domain.AccountType;

@Data
@AllArgsConstructor
public class CreateLikeResponseDto {

    @ApiModelProperty(name = "id", required = true, example = "1")
    private Long id;

    @ApiModelProperty(name = "nickname", required = true, example = "sunryeo")
    private String nickname;

    @ApiModelProperty(name = "account_type", required = true, allowableValues = "LESSOR, REALTOR, LESSEE")
    private AccountType account_type;

    @ApiModelProperty(name = "articleId", required = true, example = "1")
    private Long articleId;

}
