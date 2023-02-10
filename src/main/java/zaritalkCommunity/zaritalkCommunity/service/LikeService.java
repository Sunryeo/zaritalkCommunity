package zaritalkCommunity.zaritalkCommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.domain.Likes;
import zaritalkCommunity.zaritalkCommunity.repository.LikeRepository;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;


    /**
     * 좋아요 생성
     */
    @Transactional
    public Long createLike(Likes like, Account account, Article article) {
        // 좋아요 중복 검증
        validateDuplicateLike(account.getId(), article.getId());
        like.setAccount(account);
        like.setArticle(article);
        likeRepository.save(like);
        return like.getId();
    }

    /**
     * 좋아요 단일 조회(pk)
     */
    public Likes findOne(Long id) {
        return likeRepository.findById(id);
    }

    /**
     * accountId & articleId로 좋아요 조회
     */
    public Likes findByAccountIdAndArticleId(Long accountId, Long articleId) {
        return likeRepository.findByAccountIdAndArticleId(accountId, articleId);
    }

    /**
     * 좋아요 중복 검증
     */
    private void validateDuplicateLike(Long accountId, Long articleId) {
        Likes existLike = findByAccountIdAndArticleId(accountId, articleId);

        if(!Objects.isNull(existLike)) {
            throw new IllegalStateException("이미 좋아요한 게시물입니다.");
        }
    }
}
