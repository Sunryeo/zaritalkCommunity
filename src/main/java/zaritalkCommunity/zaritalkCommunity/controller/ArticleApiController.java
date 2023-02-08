package zaritalkCommunity.zaritalkCommunity.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.dto.request.PostRequestDto;
import zaritalkCommunity.zaritalkCommunity.dto.request.UpdatePostRequestDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.PostResponseDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.UpdatePostResponseDto;
import zaritalkCommunity.zaritalkCommunity.service.ArticleService;

import javax.validation.Valid;

@Api(tags = "article")
@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody @Valid PostRequestDto dto,
                                @RequestHeader(value = "Authentication")String authentication) {
        System.out.println(authentication);
        Article article = new Article(dto.getTitle(), dto.getBody());
        Long articleId = articleService.createArticle(authentication, article);
        Article result = articleService.findOne(articleId);

        return new PostResponseDto(result.getId(), result.getTitle(),result.getBody(),
                result.getAccount().getNickname(), result.getCreated_at(), result.getUpdated_at());
    }

    // todo: user validation 필요
    @PutMapping("/posts/{id}")
    public UpdatePostResponseDto updatePost(@PathVariable("id") Long id,
                                            @RequestBody @Valid UpdatePostRequestDto dto,
                                            @RequestHeader(value = "Authentication")String authentication) {

        articleService.update(id, dto.getBody());
        Article article = articleService.findOne(id);

        return new UpdatePostResponseDto(article.getId(), article.getTitle(), article.getBody());
    }

    // todo: user validation 필요
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") Long id,
                           @RequestHeader(value = "Authentication")String authentication) {
        articleService.delete(id);
    }
}
