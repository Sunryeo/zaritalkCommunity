package zaritalkCommunity.zaritalkCommunity.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.dto.response.ArticleListWithAuthResponseDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.ListResponseDto;
import zaritalkCommunity.zaritalkCommunity.dto.request.CreatePostRequestDto;
import zaritalkCommunity.zaritalkCommunity.dto.request.UpdatePostRequestDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.CreatePostResponseDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.UpdatePostResponseDto;
import zaritalkCommunity.zaritalkCommunity.service.ArticleService;
import zaritalkCommunity.zaritalkCommunity.service.AuthService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "article")
@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;
    private final AuthService authService;


    @Operation(summary="게시글 작성", description="게시글 생성 api입니다.")
    @PostMapping("/post")
    public CreatePostResponseDto createArticle(@RequestBody @Valid CreatePostRequestDto dto,
                                            @RequestHeader(value = "Authentication")String authentication) {

        Article article = new Article(dto.getTitle(), dto.getBody());

        // user validation
        Account account = authService.authAccount(authentication);
        Long articleId = articleService.createArticle(account, article);
        Article result = articleService.findOne(articleId);

        return new CreatePostResponseDto(result.getId(), result.getTitle(),result.getBody(),
                result.getAccount().getNickname(), result.getCreated_at(), result.getUpdated_at());
    }

    @Operation(summary="게시글 수정", description="게시글 수정 api입니다.")
    @PutMapping("/posts/{id}")
    public UpdatePostResponseDto updateArticle(@PathVariable("id") Long id,
                                            @RequestBody @Valid UpdatePostRequestDto dto,
                                            @RequestHeader(value = "Authentication")String authentication) {
        // user validation
        Account account = authService.authAccount(authentication);
        authService.authWriter(account.getId(), id);
        articleService.update(id, dto.getBody());
        Article article = articleService.findOne(id);

        return new UpdatePostResponseDto(article.getId(), article.getTitle(), article.getBody());
    }

    @Operation(summary="게시글 삭제", description="게시글 삭제 api입니다.")
    @DeleteMapping("/posts/{id}")
    public void deleteArticle(@PathVariable("id") Long id,
                           @RequestHeader(value = "Authentication")String authentication) {
        // user validation
        Account account = authService.authAccount(authentication);
        authService.authWriter(account.getId(), id);
        articleService.delete(id);
    }

    @Operation(summary="게시글 목록 조회", description="게시글 목록 조회 api입니다.")
    @GetMapping("/post")
    public ListResponseDto<Object> findAllArticle(
            @RequestHeader(value = "Authentication", required = false) Optional<String> authentication
    ) {
        List<ArticleListWithAuthResponseDto> resultList = articleService.findAll(authentication);
        int count = resultList.size();

        return new ListResponseDto(resultList, count);
    }
}
