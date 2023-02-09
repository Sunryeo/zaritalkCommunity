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
        account.setQuit(false);
        accountRepository.save(account);
        account.setAccountId(account.getId(), account.getAccount_type());
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

    /**
     * 회원 단일 조회
     */
    public Account finOne(Long id) {
        return accountRepository.findById(id);
    }

}
