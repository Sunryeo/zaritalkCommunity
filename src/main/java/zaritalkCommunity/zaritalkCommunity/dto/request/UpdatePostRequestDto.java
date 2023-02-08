package zaritalkCommunity.zaritalkCommunity.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdatePostRequestDto {

    @ApiParam(name = "body", example = "update body example", required = true)
    private String body;
}
