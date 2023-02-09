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
    public Long createArticle(Account account, Article article) {
//            validateStringLength(article.getTitle(), article.getBody());
            article.setAccount(account);
            articleRepository.save(article);

        return article.getId();
    }

    /**
     * 글 단일 조회
     */
    public Article findOne(Long id) {
        return articleRepository.findById(id);
    }

    /**
     * 글 수정
     */
    @Transactional
    public void update(Long id, String body) {
        Article article = articleRepository.findById(id);
        article.setBody(body);
    }

    /**
     * 글 삭제
     */
    @Transactional
    public void delete(Long id) {
        articleRepository.delete(id);
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
