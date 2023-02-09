package zaritalkCommunity.zaritalkCommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.domain.Likes;
import zaritalkCommunity.zaritalkCommunity.repository.AccountRepository;
import zaritalkCommunity.zaritalkCommunity.repository.ArticleRepository;
import zaritalkCommunity.zaritalkCommunity.repository.LikeRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final AccountRepository accountRepository;
    private final ArticleRepository articleRepository;


    /**
     * 좋아요 생성
     */
    @Transactional
    public Long createLike(Likes like, Account account, Article article) {
        like.setAccount(account);
        like.setArticle(article);
        likeRepository.save(like);
        return like.getId();
    }

    /**
     * 좋아요 단일 조회
     */
    public Likes findOne(Long id) {
        return likeRepository.findById(id);
    }
}
