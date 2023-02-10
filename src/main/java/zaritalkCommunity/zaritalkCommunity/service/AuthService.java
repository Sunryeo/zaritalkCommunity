package zaritalkCommunity.zaritalkCommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.repository.AccountRepository;
import zaritalkCommunity.zaritalkCommunity.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;
    private final ArticleRepository articleRepository;

    public Account authAccount(String authentication) {
        Account account;
        try{
            account = accountRepository.findByAccountId(authentication);
        } catch(Exception e) {
            System.out.println("Exception: " + e);
            throw new IllegalStateException("자리톡 회원만 이용할 수 있습니다.");
        }

        return account;
    }

    public void authWriter(Long accountId, Long articleId) {
        Article article = articleRepository.findById(articleId);
        System.out.println("article.getAccount: " + article.getAccount());
        if(article.getAccount().getId() != accountId) {
            throw new IllegalStateException("글 작성자만 접근할 수 있습니다.");
        }
    }
}
