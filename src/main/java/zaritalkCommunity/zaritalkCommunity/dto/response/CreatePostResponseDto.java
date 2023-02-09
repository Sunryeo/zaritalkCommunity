package zaritalkCommunity.zaritalkCommunity.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreatePostResponseDto {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "title")
    private String title;
    @ApiModelProperty(example = "body example")
    private String body;
    @ApiModelProperty(example = "sunryeo")
    private String nickname;
    @ApiModelProperty(example = "2023-02-08")
    private LocalDateTime created_at;
    @ApiModelProperty(example = "2023-02-08")
    private LocalDateTime updated_at;
}
