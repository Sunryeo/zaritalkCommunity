package zaritalkCommunity.zaritalkCommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import zaritalkCommunity.zaritalkCommunity.Exception.CustomException;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.repository.AccountRepository;
import zaritalkCommunity.zaritalkCommunity.repository.ArticleRepository;
import static zaritalkCommunity.zaritalkCommunity.Exception.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;
    private final ArticleRepository articleRepository;

    public Account authAccount(String authentication) {
        Account account;
        try{
            account = accountRepository.findByAccountId(authentication);
        } catch(RuntimeException e) {
            throw new CustomException(INVALID_ACCOUNT_ERROR);
        }

        return account;
    }

    public void authWriter(Long accountId, Long articleId) {
        Article article = articleRepository.findById(articleId);

        if(article.getAccount().getId() != accountId) {
            throw new CustomException(UNAUTHORIZED_ACCOUNT_ERROR);
        }
    }
}
