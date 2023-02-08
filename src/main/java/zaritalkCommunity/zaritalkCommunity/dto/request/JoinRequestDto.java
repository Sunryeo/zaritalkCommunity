package zaritalkCommunity.zaritalkCommunity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Getter;
import zaritalkCommunity.zaritalkCommunity.domain.AccountType;

@Data
@Getter
public class JoinRequestDto {

    @ApiParam(name = "nickname", required = true)
    private String nickname;

    @ApiModelProperty(name = "account_type", required = true, allowableValues = "LESSOR, REALTOR, LESSEE")
    private AccountType account_type;
}
