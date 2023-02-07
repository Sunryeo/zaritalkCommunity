package zaritalkCommunity.zaritalkCommunity.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import zaritalkCommunity.zaritalkCommunity.domain.Likes;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    public void save(Likes like) {
        em.persist(like);
    }

    public List<Likes> findByArticleId(Long articleId) {
        return em.createQuery("select l from Likes l where l.article.id = :articleId", Likes.class)
                .setParameter("articleId", articleId)
                .getResultList();
    }

    public List<Likes> findByAccountId(Long accountId) {
        return em.createQuery("select l from Likes l where l.account.id = :accountId", Likes.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }

}
