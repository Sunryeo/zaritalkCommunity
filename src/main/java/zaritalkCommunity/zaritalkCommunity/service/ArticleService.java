package zaritalkCommunity.zaritalkCommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.repository.AccountRepository;
import zaritalkCommunity.zaritalkCommunity.repository.ArticleRepository;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;

    /**
     * 글 작성
     */
    @Transactional
    public Long createArticle(String account_id, Article article) {
//        try{
            Account account = accountRepository.findByAccountId(account_id);
//            if(Objects.isNull(account)) {
//                throw new IllegalStateException("자리톡 회원만 글을 작성할 수 있습니다.");
//            }
//            validateStringLength(article.getTitle(), article.getBody());
            article.setAccount(account);
            articleRepository.save(article);
//        } catch(Exception e) {
//            System.out.println("Exception: " + e);
//        }

        return article.getId();
    }

    /**
     * 글 단일 조회
     */
    public Article findOne(Long id) {
        return articleRepository.findById(id);
    }

    /**
     * title, body 글자수 검증
     */
    private void validateStringLength(String title, String body) {
        if(title.length() > 50) {
            throw new IllegalStateException("제목은 50자 미만이어야 합니다.");
        }
        if(body.length() > 100) {
            throw new IllegalStateException("내용은 100자 미만이어야 합니다.");
        }
    }
}
