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
