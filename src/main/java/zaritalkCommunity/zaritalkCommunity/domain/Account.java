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
@SQLDelete(sql = "update account set deleted_at = current_timestamp where id = ?")
@Where(clause = "deleted_at is null")
public class Account extends BaseEntity {

    private String password;

    private String nickname;

    private String account_id;

    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    private boolean quit;

    @OneToMany(mappedBy = "account")
    private List<Likes> likes = new ArrayList<>();

    public Account(String account_id, String password, String nickname) {
        this.account_id = account_id;
        this.password = password;
        this.nickname = nickname;
    }

    public void setAccountType(AccountType accountType) {
        this.account_type = accountType;
    }
}
