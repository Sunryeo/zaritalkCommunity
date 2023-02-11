package zaritalkCommunity.zaritalkCommunity.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class UpdatePostResponseDto {

    @ApiModelProperty(name = "id", example = "1")
    private Long id;

    @ApiModelProperty(name = "title", example = "제목입니다.")
    private String title;

    @ApiModelProperty(name = "body", example = "내용입니다.")
    private String body;
}
