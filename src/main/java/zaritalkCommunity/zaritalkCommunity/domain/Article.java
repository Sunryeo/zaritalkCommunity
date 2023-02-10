package zaritalkCommunity.zaritalkCommunity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update article set deleted_at = current_timestamp where id = ?")
@Where(clause = "deleted_at is null")
//@SqlResultSetMapping(name = "articleList",
//    entities = {@EntityResult(entityClass = Article.class
//            , fields = {
//            @FieldResult(name = "ar_id", column = "ar_id"),
//            @FieldResult(name = "ac_id", column = "account_id"),
//            @FieldResult(name = "title", column = "ar_title"),
//            @FieldResult(name = "body", column = "ar_body"),
//            @FieldResult(name = "created_at", column = "created_at"),
//            @FieldResult(name = "updated_at", column = "updated_at"),
//            @FieldResult(name = "deleted_at", column = "deleted_at")
//            }
//            ),
//    @EntityResult(entityClass = Account.class, fields = {
//            @FieldResult(name = "ac_id", column = "ac_id"),
//            @FieldResult(name = "nickname", column = "ac_nickname"),
//    }),
//    @EntityResult(entityClass = Likes.class
//            , fields = {
//            @FieldResult(name = "l_id", column = "id")
//    })
//    },
//    columns = {@ColumnResult(name = "is_Liked", type = String.class),
//    @ColumnResult(name = "like_cnt", type = Integer.class)})
//@NamedNativeQuery(
//        name = "Article.articleList",
//        query = "select id as ar_id, title as ar_title, body as ar_body, nickname as ac_nickname, created_at, updated_at, deleted_at," +
//                " case when" +
//                " (select article_id from likes where account_id = ? and article_id = article.id) is not null" +
//                " then 'ok' else 'no' end as is_liked," +
//                " coalesce(like_cnt,0) as like_cnt," +
//                " from article" +
//                " left join (select id as ac_id, nickname from account) account on account.ac_id = article.account_id" +
//                " left join (select article_id, count(*) as like_cnt from likes" +
//                " group by article_id) l on l.article_id = article.id" +
//                " where article.deleted_at is null",
//        resultSetMapping = "articleList"
//)
public class Article extends BaseEntity{

    private String title;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Likes> likes = new ArrayList<>();

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    //==연관관계 메서드==//
    public void setAccount(Account account) {
        this.account = account;
        account.getArticles().add(this);
    }

    // 생성 메서드
//    public static Article createArticle(String title, String body, Account account) {
//        Article article = new Article(title, body);
//        article.setAccount(account);
//
//        return article;
//    }
}
