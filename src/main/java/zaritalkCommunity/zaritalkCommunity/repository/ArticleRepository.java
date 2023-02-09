package zaritalkCommunity.zaritalkCommunity.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.dto.ArticleQueryDto;

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

    public List<ArticleQueryDto> findAll() {
        return em.createQuery("select" +
                        " new zaritalkCommunity" +
                        ".zaritalkCommunity.dto" +
                        ".ArticleQueryDto(ar.id, count (l.article.id), ar.title, ar.body," +
                        " ac.nickname, ac.account_type)" +
                        " from Article ar" +
                        " join ar.likes l" +
                        " join ar.account ac" +
                        " group by l.article.id", ArticleQueryDto.class)
                .getResultList();
    }

    public void delete(Long id) {
        Article article = findById(id);
        em.remove(article);
    }
}
