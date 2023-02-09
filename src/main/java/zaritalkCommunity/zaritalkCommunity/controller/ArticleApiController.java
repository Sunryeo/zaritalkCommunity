package zaritalkCommunity.zaritalkCommunity.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.dto.request.CreatePostRequestDto;
import zaritalkCommunity.zaritalkCommunity.dto.request.UpdatePostRequestDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.CreatePostResponseDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.UpdatePostResponseDto;
import zaritalkCommunity.zaritalkCommunity.service.ArticleService;
import zaritalkCommunity.zaritalkCommunity.service.AuthService;

import javax.validation.Valid;

@Api(tags = "article")
@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;
    private final AuthService authService;

    @PostMapping("/post")
    public CreatePostResponseDto createPost(@RequestBody @Valid CreatePostRequestDto dto,
                                            @RequestHeader(value = "Authentication")String authentication) {

        Article article = new Article(dto.getTitle(), dto.getBody());

        // user validation
        Account account = authService.authAccount(authentication);
        Long articleId = articleService.createArticle(account, article);
        Article result = articleService.findOne(articleId);

        return new CreatePostResponseDto(result.getId(), result.getTitle(),result.getBody(),
                result.getAccount().getNickname(), result.getCreated_at(), result.getUpdated_at());
    }

    @PutMapping("/posts/{id}")
    public UpdatePostResponseDto updatePost(@PathVariable("id") Long id,
                                            @RequestBody @Valid UpdatePostRequestDto dto,
                                            @RequestHeader(value = "Authentication")String authentication) {
        // user validation
        authService.authAccount(authentication);
        articleService.update(id, dto.getBody());
        Article article = articleService.findOne(id);

        return new UpdatePostResponseDto(article.getId(), article.getTitle(), article.getBody());
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") Long id,
                           @RequestHeader(value = "Authentication")String authentication) {
        // user validation
        authService.authAccount(authentication);
        articleService.delete(id);
    }
}
