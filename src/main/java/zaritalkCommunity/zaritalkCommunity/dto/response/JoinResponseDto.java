package zaritalkCommunity.zaritalkCommunity.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class JoinResponseDto {

    @ApiModelProperty(name = "id", example = "1")
    private Long id;
}
