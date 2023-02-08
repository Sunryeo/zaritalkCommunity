package zaritalkCommunity.zaritalkCommunity.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import zaritalkCommunity.zaritalkCommunity.domain.Article;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public void save(Article article) {
        em.persist(article);
    }

    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    public List<Article> findAll() {
        return em.createQuery("select ar from Article ar", Article.class)
                .getResultList();
    }
}
