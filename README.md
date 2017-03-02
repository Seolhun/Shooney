# Personal Homepage and Portfolio Platform

#### *2016-11-26 ~ *  

- Type : 개인 프로젝트
- IDE : Spring Tool Suite
- WAS : Tomcat 9.0
- OS : OSX  
- Framework : Spring Boot(Security, JPA, MongoDB), Bootstrap, AngularJS  
- Library : Summernote, Jsoup


## 1. 프로젝트 목표.
- 스프링의 최신버전인 부트에 대해서 이해하는 것
- XML이 아닌 Java Config를 통한  
- SQL 맵핑 프레임워크인 `MyBatis를 배제`하고 자바 표준  퍼시스턴스 기술인 `JPA를 적용`하여 효율적으로 객체지향 프로그래밍을 접목한다. 
- Spring Security를 Java Config와 JPA(Hibernate) 에서 적용시키기.
- Jsoup을 통한 웹 크롤링 맛보기 


## 2. 문제점 및 해결방법 기록  
개인 홈페이지 및 포트폴리오 플랫폼을 구현하면서 발생한 문제점을 어떻게 해결하였는지를 서술하겠습니다. 

#### 2.1 Social UserConnection Table Entity  
스프링 시큐리티 예제를 보면 UserConnection 의 테이블을 직접 DDL로 데이터베이스에 만들어두어야 하는 불편함을 보여 스프링 데이터 JPA를 지원을 받아 도메인 엔터티로 구현하였다.  

#### 2.2 Paging(Pagination)
JPA의 Paging처리에서 기존 사용하던 Count값을 가져오는 것에서 원하는 방식이 되지 않아 `@Query`문을 추상클레스에 만들어 Parameter값으로 테이블의 갯수를 파악 할 수 있게 만들었습니다. 후에는 `Criteria`를 통해 JPQL을 컨트롤하여 가져오는 방법을 알았습니다. 

#### 2.3 MongoDB 활용
ITWorld나 

## 3. 추가 구현 계획  

- 스프링 웹 소켓을 적용하여 알림 기능 구현 예정(알림 기능을 Spring Stomp + Sock.Js로 구현 할 가능성 있음)  
- Spring Social을 통해 자신이 올린 포트폴리오 등에 대한 정보를 공유할 수 있는 기능.
- 
