package zaritalkCommunity.zaritalkCommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;

    public Account authAccount(String authentication) {
        Account account;
        try{
            account = accountRepository.findByAccountId(authentication);
        } catch(Exception e) {
            System.out.println("Exception: " + e);
            throw new IllegalStateException("자리톡 회원만 이용할 수 있습니다.");
        }

        return account;
    }
}
