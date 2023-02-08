package zaritalkCommunity.zaritalkCommunity.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "컨트롤러이름")
@RestController
public class SwaggerExampleController {

    @Operation(summary="요약", description="설명")
    @ApiResponse(code = 200, message="ok")
    @PostMapping("/ex/")
    public ResponseDto exampleMethod() {
        return new ResponseDto();
    }
}

//DTO
@Data
class ResponseDto {
    @ApiModelProperty(value="필드 값", example="예시", required=true)
    private String a1;

    @ApiModelProperty(value="필드 값", example="예시")
    private String a2;
}
