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
    @Transactional
    public Long join(Account account) {
        validateDuplicateAccount(account.getNickname());
        account.setAccountId(account.getNickname(), account.getAccount_type());
        account.setQuit(false);
        accountRepository.save(account);
        return account.getId();
    }

    /**
     * 중복회원 검증
     */
    private void validateDuplicateAccount(String nickname) {
        List<Account> accounts = accountRepository.findByNickname(nickname);
        if(!accounts.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
