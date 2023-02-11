package zaritalkCommunity.zaritalkCommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zaritalkCommunity.zaritalkCommunity.Exception.CustomException;
import zaritalkCommunity.zaritalkCommunity.domain.Account;
import zaritalkCommunity.zaritalkCommunity.domain.Article;
import zaritalkCommunity.zaritalkCommunity.dto.response.ArticleListWithAuthResponseDto;
import zaritalkCommunity.zaritalkCommunity.repository.AccountRepository;
import zaritalkCommunity.zaritalkCommunity.repository.ArticleRepository;
import static zaritalkCommunity.zaritalkCommunity.Exception.CustomErrorCode.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;

    /**
     * 글 작성
     */
    @Transactional
    public Long createArticle(Account account, Article article) {
            validateStringLength(article.getTitle(), article.getBody());
            article.setAccount(account);
            articleRepository.save(article);

        return article.getId();
    }

    /**
     * 글 단일 조회
     */
    public Article findOne(Long id) {
        return articleRepository.findById(id);
    }

    /**
     * 글 목록 조회
     */
    public List findAll(Optional<String> account_id) {
        List resultList;
        if(account_id.isPresent()) {
            Account account = accountRepository.findByAccountId(account_id.get());
            List<Object[]> rawList = articleRepository.findAllWithAccountId(account.getId());
            resultList = mappingDtos(rawList);
        } else {
            resultList = articleRepository.findAllWithoutAccountId();
        }

        return resultList;
    }

    /**
     * 글 수정
     */
    @Transactional
    public void update(Long id, String body) {
        Article article = articleRepository.findById(id);
        article.setBody(body);
    }

    /**
     * 글 삭제
     */
    @Transactional
    public void delete(Long id) {
        articleRepository.delete(id);
    }


    /**
     * title, body 글자수 검증
     */
    private void validateStringLength(String title, String body) {
        if(title.length() > 50) {
            throw new CustomException(TITLE_LENGTH_OVER_ERROR);
        }
        if(body.length() > 100) {
            throw new CustomException(BODY_LENGTH_OVER_ERROR);
        }
    }

    /**
     * 객체 매핑 함수
     */
    private List<ArticleListWithAuthResponseDto> mappingDtos(List<Object[]> rawList) {
        List<ArticleListWithAuthResponseDto> resultList = new ArrayList<>();
        for(int i = 0; i < rawList.size(); i++) {
            BigInteger id = (BigInteger) rawList.get(i)[0];
            String title = (String) rawList.get(i)[1];
            String body = (String) rawList.get(i)[2];
            String nickname = (String) rawList.get(i)[3];
            String account_type = (String) rawList.get(i)[4];
            Timestamp created_at = (Timestamp) rawList.get(i)[5];
            Timestamp updated_at = (Timestamp) rawList.get(i)[6];
            Boolean is_liked = (Boolean) rawList.get(i)[7];
            BigInteger like_cnt = (BigInteger) rawList.get(i)[8];

            ArticleListWithAuthResponseDto element = new ArticleListWithAuthResponseDto(id, title, body, nickname, account_type, created_at, updated_at, like_cnt, is_liked);
            resultList.add(element);
        }

        return resultList;
    }
}
