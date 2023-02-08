package zaritalkCommunity.zaritalkCommunity.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.dto.request.PostRequestDto;
import zaritalkCommunity.zaritalkCommunity.dto.response.PostResponseDto;
import zaritalkCommunity.zaritalkCommunity.service.ArticleService;

import javax.validation.Valid;

@Api(tags = "article")
@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/post")
    public PostResponseDto post(@RequestBody @Valid PostRequestDto dto,
                                @RequestHeader(value = "Authentication")String authentication) {
        System.out.println(authentication);
        Article article = new Article(dto.getTitle(), dto.getBody());
        Long articleId = articleService.createArticle(authentication, article);
        Article result = articleService.findOne(articleId);

        return new PostResponseDto(result.getId(), result.getTitle(),result.getBody(),
                result.getAccount().getNickname(), result.getCreated_at(), result.getUpdated_at());
    }
}
