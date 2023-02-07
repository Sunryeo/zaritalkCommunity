package zaritalkCommunity.zaritalkCommunity.domain;

import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update article set deleted_at = current_timestamp where id = ?")
@Where(clause = "deleted_at is null")
public class Article extends BaseEntity{
    @NotEmpty
    private String body;

    @OneToMany(mappedBy = "article")
    private List<Likes> likes = new ArrayList<>();
}
