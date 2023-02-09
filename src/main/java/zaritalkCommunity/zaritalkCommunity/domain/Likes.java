package zaritalkCommunity.zaritalkCommunity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Getter
//@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update likes set deleted_at = current_timestamp where id = ?")
@Where(clause = "deleted_at is null")
public class Likes extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    //==연관관계 메서드==//
    public void setAccount(Account account) {
        this.account = account;
        account.getLikes().add(this);
    }

    public void setArticle(Article article) {
        this.article = article;
        article.getLikes().add(this);
    }

    // 생성 메서드
    public static Likes createLike(Article article, Account account) {
        Likes like = new Likes();
        like.setArticle(article);
        like.setAccount(account);

        return like;
    }
}
