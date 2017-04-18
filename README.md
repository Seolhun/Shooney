# Personal Homepage and Portfolio Platform
[프로젝트 홈페이지](https://www.hi-cord.com/shooney)

- Type : 개인 프로젝트
- IDE : Spring Tool Suite
- WAS : Tomcat 9.0
- OS : OSX  
- Framework : Spring Boot(Security, JPA, Schduler ,MongoDB), Bootstrap, AngularJS  
- Library : Summernote, Jsoup

## 프로젝트 개요 및 시작일.
*2016-11-26 ~ *  
- 2016년 11월 26일 첫 시작하였으며, 중간에 다른 프로젝트 연습 및 회사 프로젝트를 병행하여 늦어지고 있습니다.
- 현재 우선순위 1순위로 작업 진행중입니다.
- [HI-CORD](https://hi-cord.com/shooney)에 앞으로 작업 내용이 나와있습니다.


## 1. 프로젝트 목표.
- 스프링의 최신버전인 부트에 대해서 이해하는 것
- XML이 아닌 Java Config를 통한  
- SQL 맵핑 프레임워크인 `MyBatis를 배제`하고 자바 표준  퍼시스턴스 기술인 `JPA를 적용`하여 효율적으로 객체지향 프로그래밍을 접목한다. 
- Spring Security를 Java Config와 JPA(Hibernate) 에서 적용시키기.
- Jsoup을 통한 웹 크롤링 맛보기 


## 2. 문제점 및 해결방법 기록  
개인 홈페이지 및 포트폴리오 플랫폼을 구현하면서 발생한 문제점을 어떻게 해결하였는지를 서술하겠습니다. 

#### 2.1 Spring Security의 Customize
Spring Security를 커스터마이징하면서 Filter를 통해 인증/권한부여 과정의 이해가 어려웠습니다. 특히, Customizing 후 입력한 값의 데이터들이 필터를 거치는 과정에서 에러가 발생하였습니다. Parameter의 Override 문제였으며, 디버깅하여 겨우 해결 할 수 있었습니다.

#### 2.2 Paging(Pagination)
JPA의 Paging처리에서 기존 사용하던 Count값을 가져오는 것에서 원하는 방식이 되지 않아 `@Query`문을 추상클레스에 만들어 Parameter값으로 테이블의 갯수를 파악 할 수 있게 만들었습니다.
후에는 `Criteria`를 통해 JPQL을 컨트롤하여 가져오는 방법을 알아 해결하였습니다.

#### 2.3 MongoDB 활용
IT관련 정보를 제공하는 사이트들의 뉴스를 크롤링하여 몽고디비에 저장하였습니다. 현재 50만 데이터를 모았으며, MongoDB Repository를 사용하여, AngularJS로 Read, Pagination을 구현했습니다.

#### 2.4 Jsoup을 통한 데이터 크롤링
HTTPS로 연결 후, News에 존재하는 Header Image에 소스를 사용시 Https에서 http로 이미지 소스가 연결되는 에러가 발생되었습니다.
=> Jsoup 로직에서 Img 태그를 찾아, 다운로드 하는 로직을 추가 할 예정입니다.

#### 2.5 AngularJS를 통한 Pagination
현재 구글 방식의 Example를 참고하여 Pagination을 구현했습니다. Module에서 Controller와 Service를 나누는 부분과 Scope에 대한 학습이 더 필요하다고 생각합니다.


## 3. 추가 구현 계획 
- Spring WebSocket을 적용하여 알림 기능 구현 예정(알림 기능을 Spring Stomp + Sock.Js로 구현 할 예정입니다. - 브라우저 호환)  
- Spring Social을 통해 제가 작성한 블로그를 공유할 수 있게 만들 예정입니다.(Kakao - Facebook 예정)
- 웹 크롤링 데이터 확대 및 통계/분석 기능. - 50만 데이터를 통해 키워드 분석 및 Visualize를 구현 할 예정입니다.


## 4. 실행방법
- [Mongodb](https://docs.mongodb.com/getting-started/shell/)를 설치하고, mongod를 통한 몽고디비 서버 실행
- [Redis](https://redis.io/topics/quickstart)를 설치하고, redis-server를 통해 레디스 서버 실행
- Datasource Properties에 Entity자동생성 부분이 Create로 되어있는지 확인
- com.shun.blog 패키지에서 Hibernate Config에 DDL 자동 생성 주석 확인/첫 시작이면 주석해제.
- Project Run. 
- 권한이 적용되어있으므로, 회원가입한 후 사용할 것.
