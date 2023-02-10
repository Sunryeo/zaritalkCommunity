package zaritalkCommunity.zaritalkCommunity.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
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

    public List findAllWithAccountId(Long accountId) {
//        List<Article> resultList = em.createQuery("select" +
//                        " case when l.account.account_id = :account_id" +
//                        " then 'ok'" +
//                        " else 'no'" +
//                        " end," +
////                        " ar" +
//                        " ar.id, ar.title, ar.body" +
////                        " new zaritalkCommunity" +
////                        ".zaritalkCommunity.dto" +
////                        ".ArticleQueryDto(ar.id, ar.likes.size, ar.title, ar.body," +
////                        " ac.nickname, ac.account_type)" +
//                        " from Article ar" +
//                        " left join ar.likes l" +
//                        " left join ar.account ac" +
//                        " order by ar.created_at desc")
//                .setParameter("account_id", account_id)
//                .getResultList();

//        System.out.println("resultList: " + resultList);
//        return resultList;
        String query = "select id, title, body, nickname, created_at, updated_at," +
                " case when" +
                " (select article_id from likes where account_id = ? and article_id = article.id) is not null" +
                " then 'ok' else 'no' end as is_liked," +
                " coalesce(like_cnt,0) as like_cnt," +
                " from article" +
                " left join (select id as accid, nickname from account) account on account.accid = article.account_id" +
                " left join (select article_id, count(*) as like_cnt from likes" +
                " group by article_id) l on l.article_id = article.id" +
                " where article.deleted_at is null";


//        return em.createNativeQuery(query, "articleList").setParameter(1, accountId)
//                .getResultList();
        return em.createNamedQuery("Article.articleList").setParameter(1, accountId).getResultList();
    }

    public List<ArticleQueryDto> findAll() {
        return em.createQuery("select" +
                        " new zaritalkCommunity" +
                        ".zaritalkCommunity.dto" +
                        ".ArticleQueryDto(ar.id, ar.likes.size, ar.title, ar.body," +
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
