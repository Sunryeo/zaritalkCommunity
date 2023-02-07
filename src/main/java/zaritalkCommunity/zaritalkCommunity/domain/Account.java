package zaritalkCommunity.zaritalkCommunity.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String password;

    private String nickname;

    private String account_id;

    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    private boolean quit;

    private LocalDateTime created_at;

    private LocalDateTime deleted_at;

    @OneToMany(mappedBy = "account")
    private List<Likes> likes = new ArrayList<>();
}
