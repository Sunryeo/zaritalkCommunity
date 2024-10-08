package zaritalkCommunity.zaritalkCommunity.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
    private String nickname;

    @Column(unique = true)
    private String account_id;

    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    private Boolean quit;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Article> articles = new ArrayList<>();

    public Account(String nickname, AccountType account_type) {
        this.nickname = nickname;
        this.account_type = account_type;
    }

    public void setAccountType(AccountType accountType) {
        this.account_type = accountType;
    }

    public void setAccountId(Long id, AccountType accountType) {
        String accountId = accountType + " " + id.toString();
        this.account_id = accountId;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

}
