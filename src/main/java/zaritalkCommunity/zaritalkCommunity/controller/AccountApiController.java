package zaritalkCommunity.zaritalkCommunity.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.dto.request.JoinRequestDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.JoinResponseDto;
import zaritalkCommunity.zaritalkCommunity.service.AccountService;

import javax.validation.Valid;

@Api(tags = "account")
@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;

    @Operation(summary="회원가입", description="회원가입 api 입니다.")
    @ApiResponse(code = 200, message="ok")
    @PostMapping("/join")
    public JoinResponseDto join(@RequestBody @Valid JoinRequestDto dto) {
        Account account = new Account(dto.getNickname(), dto.getAccount_type());
        Long resultId = accountService.join(account);
        return new JoinResponseDto(resultId);
    }
}
