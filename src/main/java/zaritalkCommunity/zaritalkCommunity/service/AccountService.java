package zaritalkCommunity.zaritalkCommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.repository.AccountRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * 회원가입
     */
    public Long join(Account account) {
        validateDuplicateAccount(account.getAccount_id());
        accountRepository.save(account);
        return account.getId();
    }

    /**
     * 중복회원 검증
     */
    private void validateDuplicateAccount(String account_id) {
        List<Account> accounts = accountRepository.findByAccountId(account_id);
        if(!accounts.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 로그인
     */

    /**
     * 로그아웃
     */
}
