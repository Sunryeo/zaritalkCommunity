package zaritalkCommunity.zaritalkCommunity.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePostRequestDto {

    @ApiParam(name = "title", required = true)
    private String title;
    @ApiParam(name = "body", required = true)
    private String body;
}
