package zaritalkCommunity.zaritalkCommunity.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@AllArgsConstructor
public class JoinResponseDto {

    @ApiModelProperty(example = "1")
    private Long id;
}
