### Api Resource

- 회원가입(`POST /join`)
- 글 작성(`POST /post`)
- 글 수정(`PUT /post/:id`)
- 글 삭제(`DELETE /post/:id`)
- 글 좋아요(`POST /like/:articleId`)

### DB ERD
<img width="824" src="https://velog.velcdn.com/images/elma98/post/cc687289-6656-46f2-b043-1185b9296341/image.png">


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

<img width="824" src="https://velog.velcdn.com/images/elma98/post/2d680d8e-9a5c-4d66-99aa-68a537b859f0/image.png">


[게시글(Article)]

<img width="824" src="https://velog.velcdn.com/images/elma98/post/181b7c05-5d53-43b6-b0a6-1a0a470570f1/image.png">


[좋아요(Likes)]

⚠️`Likes` 테이블은 구현 코드 내부에서는 `Like`로 통용되고 있지만 sql 예약어 충돌로 테이블 생성이 되지 않는 관계로 부득이하게 테이블명을 `Likes`로 명명하게 되었습니다.

<img width="824" src="https://velog.velcdn.com/images/elma98/post/4fb195e5-fefa-4f69-8a8e-e9ff1fa30fda/image.png">


<Api 테스트 방법>

http://localhost:8080/swagger-ui/#/ 스웨거 ui 접속

<img width="824" src="https://velog.velcdn.com/images/elma98/post/4663ce24-82ac-4ac7-9328-da0c39117a3b/image.png">

위 스크린샷의 `/join` api의 `Execute` 버튼을 누르면

<img width="824" src="https://velog.velcdn.com/images/elma98/post/7bbad86f-2988-46da-afb9-d7e245006e51/image.png">

`Response Header`의 `Authentication`의 값으로 회원의 `account_id`를 돌려줍니다. 앞으로 `Request Header`의
`Authentication`값이 필요한 모든 api를 호출할 때마다 해당 `account_id`를 사용하게 됩니다.

<img width="824" src="https://velog.velcdn.com/images/elma98/post/7b013c6a-c997-466e-9ac4-cfe7b5dac3db/image.png">

`Authentication` 값이 request시 필요한 api는 위와 같이 `api parameter`에 표시됩니다.


### 기타 사항

- 글 목록 조회시 `@SqlResultSetMapping`으로 객체 매핑 구현을 시도하였으나 실패하여 부득이하게 response dto 매핑 함수를 매뉴얼로 만들어 구현하였습니다. 안티패턴인 것을 인지하고 있습니다.
- 위와 같은 이유로 글 목록 조회시 작성자의 `account_type`을 한글로 표시하는 기능 요구사항을 충족시키지 못했습니다. enum value를 return하는 메소드는 구현해놓았습니다. 
