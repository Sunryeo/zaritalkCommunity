# 콜버스랩 백엔드 개발자 사전과제

## 지원자 문순려

### Api Resource

- 회원가입(`POST /join`)
- 글 작성(`POST /post`)
- 글 수정(`PUT /post/:id`)
- 글 삭제(`DELETE /post/:id`)
- 글 좋아요(`POST /like/:articleId`)

### DB ERD
![스크린샷 2023-02-11 오후 2.04.07.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F56%2Fhwc7znhn0hz8psyhm3wdb6d00000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_yZVKzo%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-02-11%20%EC%98%A4%ED%9B%84%202.04.07.png)

### 실행 방법
*데이터베이스는 H2 db를 사용하여 간단하게 구현하였습니다. `zaritalkCommunity`이름의 db를 최초에 생성해주시면 
이후 서버를 기동할 때마다 자동으로 기본 데이터(아래 자세히 설명)가 생성됩니다.* 

<데이터베이스 실행 방법>

1. h2 데이터베이스 설치
2. h2 데이터베이스 실행 
   - 데이터베이스가 설치된 파일 경로 내 bin 디렉토리에서 `/h2.sh`명령어 실행
3. h2 콘솔에서 `jdbc:h2:~/zaritalkCommunity`로 접속 (최초 한번)
4. `~/zaritalkCommunity.mv.db` 파일 생성 확인
5. 최초 접속 이후부터는 `jdbc:h2:tcp://localhost/~/zaritalkCommunity`로 접속

<기본 데이터>

**서버가 켜질 때마다 모든 table이 drop & create 되도록 설정해두었습니다.** 

- 서버를 기동시키면 기본 데이터가 아래와 같이 생성됩니다. 기본 생성된 데이터로 기능 구현 검증을 할 수 있습니다.
- 기본 생성 데이터는 `글 목록 조회` api 호출 시 구현 검증을 용이하게 하기 위해 생성되는 데이터입니다. 새로운 데이터를 생성, 수정, 삭제하는 데에 영향을 주지 않습니다.

[계정(Account)]

![스크린샷 2023-02-11 오후 1.26.17.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F56%2Fhwc7znhn0hz8psyhm3wdb6d00000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_HecwnP%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-02-11%20%EC%98%A4%ED%9B%84%201.26.17.png)

[게시글(Article)]

![스크린샷 2023-02-11 오후 1.27.41.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F56%2Fhwc7znhn0hz8psyhm3wdb6d00000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_3A6SFt%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-02-11%20%EC%98%A4%ED%9B%84%201.27.41.png)

[좋아요(Likes)]

⚠️`Likes` 테이블은 구현 코드 내부에서는 `Like`로 통용되고 있지만 sql 예약어 충돌로 테이블 생성이 되지 않는 관계로 부득이하게 테이블명을 `Likes`로 명명하게 되었습니다.

![스크린샷 2023-02-11 오후 1.31.51.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F56%2Fhwc7znhn0hz8psyhm3wdb6d00000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_mXchU0%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-02-11%20%EC%98%A4%ED%9B%84%201.31.51.png)

<Api 테스트 방법>

http://localhost:8080/swagger-ui/#/ 스웨거 ui 접속


![스크린샷 2023-02-11 오후 1.34.21.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F56%2Fhwc7znhn0hz8psyhm3wdb6d00000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_pAKlD9%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-02-11%20%EC%98%A4%ED%9B%84%201.34.21.png)
위 스크린샷의 `/join` api의 `Execute` 버튼을 누르면

![스크린샷 2023-02-11 오후 1.37.18.png](..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-02-11%20%EC%98%A4%ED%9B%84%201.37.18.png)

`Response Header`의 `Authentication`의 값으로 회원의 `account_id`를 돌려줍니다. 앞으로 `Request Header`의
`Authentication`값이 필요한 모든 api를 호출할 때마다 해당 `account_id`를 사용하게 됩니다.

![스크린샷 2023-02-11 오후 1.43.46.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F56%2Fhwc7znhn0hz8psyhm3wdb6d00000gp%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_Bgj32s%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-02-11%20%EC%98%A4%ED%9B%84%201.43.46.png)
`Authentication` 값이 request시 필요한 api는 위와 같이 `api parameter`에 표시됩니다.


### 기타 사항

- 글 목록 조회시 `@SqlResultSetMapping`으로 객체 매핑 구현을 시도하였으나 실패하여 부득이하게 response dto 매핑 함수를 매뉴얼로 만들어 구현하였습니다. 안티패턴인 것을 인지하고 있습니다.
- 위와 같은 이유로 글 목록 조회시 작성자의 `account_type`을 한글로 표시하는 기능 요구사항을 충족시키지 못했습니다. enum value를 return하는 메소드는 구현해놓았습니다. 