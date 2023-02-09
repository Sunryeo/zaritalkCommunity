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

    // todo: join 확인 필요
    public List<Likes> findByArticleId(Long articleId) {
        return em.createQuery("select l from Likes l where l.article.id = :articleId", Likes.class)
                .setParameter("articleId", articleId)
                .getResultList();
    }

    // todo: join 확인 필요
    public List<Likes> findByAccountId(Long accountId) {
        return em.createQuery("select l from Likes l where l.account.id = :accountId", Likes.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }

    public Likes findById(Long id) {
        List<Likes> likeList = em.createQuery("select l from Likes l" +
                        " join fetch l.account ac" +
                        " join fetch l.article ar" +
                        " where l.id = :id", Likes.class)
                .setParameter("id", id)
                .getResultList();

        if(likeList.isEmpty()) {
            return null;
        } else {
            return likeList.get(0);
        }
    }

    public Likes findByAccountIdAndArticleId(Long accountId, Long articleId) {
        List<Likes> likeList = em.createQuery("select l from Likes l where l.account.id = :accountId" +
                        " and l.article.id = :articleId", Likes.class)
                .setParameter("accountId", accountId)
                .setParameter("articleId", articleId)
                .getResultList();
        if(likeList.isEmpty()) {
            return null;
        } else {
            return likeList.get(0);
        }
    }
}
