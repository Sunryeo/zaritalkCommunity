package zaritalkCommunity.zaritalkCommunity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.AccountType;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.domain.Likes;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            // 사용자 생성
            Account account1 = createDbAccount("user1", AccountType.REALTOR);
            em.persist(account1);
            account1.setAccountId(account1.getId(), account1.getAccount_type());

            Account account2 = createDbAccount("user2", AccountType.LESSOR);
            em.persist(account2);
            account2.setAccountId(account2.getId(), account2.getAccount_type());

            Account account3 = createDbAccount("user3", AccountType.LESSEE);
            em.persist(account3);
            account3.setAccountId(account3.getId(), account3.getAccount_type());

            // 게시글 생성
            Article article1 = createDbArticle(account1,"제목1", "1번 게시글입니다.");
            em.persist(article1);

            Article article2 = createDbArticle(account2,"제목2", "2번 게시글입니다.");
            em.persist(article2);

            Article article3 = createDbArticle(account3,"제목3", "3번 게시글입니다.");
            em.persist(article3);

            // 좋아요 생성
            Likes like1 = createDbLike(account1, article2);
            em.persist(like1);

            Likes like2 = createDbLike(account2, article3);
            em.persist(like2);

            Likes like3 = createDbLike(account3, article1);
            em.persist(like3);

            Likes like4 = createDbLike(account1, article3);
            em.persist(like4);
        }

        private static Account createDbAccount(String nickname, AccountType accountType) {
            Account account = new Account(nickname, accountType);
            account.setQuit(false);
            return account;
        }

        private static Article createDbArticle(Account account, String title, String body) {
            Article article = new Article(title, body);
            article.setAccount(account);
            return article;
        }

        private static Likes createDbLike(Account account, Article article) {
            Likes like = new Likes();
            like.setAccount(account);
            like.setArticle(article);
            return like;
        }
    }
}
