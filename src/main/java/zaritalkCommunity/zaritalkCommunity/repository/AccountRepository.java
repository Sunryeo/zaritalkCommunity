package zaritalkCommunity.zaritalkCommunity.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import zaritalkCommunity.zaritalkCommunity.domain.Account;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager em;

    public void save(Account account) {
        em.persist(account);
    }

    public Account findById(Long id) {
        return em.find(Account.class, id);
    }


    public Account findByAccountId(String account_id) {
        return em.createQuery("select ac from Account ac where ac.account_id = :accountId", Account.class)
                .setParameter("accountId", account_id)
                .getSingleResult();
    }

    public List<Account> findByNickname(String nickname) {
        return em.createQuery("select ac from Account ac where ac.nickname = :nickname", Account.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }
}
