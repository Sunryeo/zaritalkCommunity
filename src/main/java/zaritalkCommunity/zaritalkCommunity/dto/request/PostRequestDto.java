package zaritalkCommunity.zaritalkCommunity.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostRequestDto {

    @ApiParam(name = "title", required = true)
    private String title;
    @ApiParam(name = "body", required = true)
    private String body;
}
