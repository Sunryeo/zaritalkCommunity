package zaritalkCommunity.zaritalkCommunity.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.domain.Likes;
import zaritalkCommunity.zaritalkCommunity.dto.response.CreateLikeResponseDto;
import zaritalkCommunity.zaritalkCommunity.service.ArticleService;
import zaritalkCommunity.zaritalkCommunity.service.AuthService;
import zaritalkCommunity.zaritalkCommunity.service.LikeService;

@Api(tags = "like")
@RestController
@RequiredArgsConstructor
public class LikeApiController {

    private final LikeService likeService;
    private final ArticleService articleService;
    private final AuthService authService;

    @PostMapping("/like/{articleId}")
    public CreateLikeResponseDto createLike(@RequestHeader(value = "Authentication")String authentication,
                                            @PathVariable("articleId") Long articleId) {
        // user validation
        Account account = authService.authAccount(authentication);
        Article article = articleService.findOne(articleId);
        Likes like = new Likes();
        Long resultId = likeService.createLike(like, account, article);
        Likes result = likeService.findOne(resultId);

        return new CreateLikeResponseDto(resultId, result.getAccount().getNickname(),
                result.getAccount().getAccount_type(), result.getArticle().getId());
    }
}
