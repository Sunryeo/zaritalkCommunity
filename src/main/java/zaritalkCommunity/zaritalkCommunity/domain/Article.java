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

    @OneToMany(mappedBy = "article")
    private List<Likes> likes = new ArrayList<>();

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
