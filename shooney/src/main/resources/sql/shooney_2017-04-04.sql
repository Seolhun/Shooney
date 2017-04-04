# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.0.2 (MySQL 5.5.5-10.1.22-MariaDB)
# Database: shooney
# Generation Time: 2017-04-04 13:53:50 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table TB_BLOG
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_BLOG`;

CREATE TABLE `TB_BLOG` (
  `BLOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BLOG_CONTENT` longtext NOT NULL,
  `BLOG_CREATED_BY` varchar(60) NOT NULL DEFAULT '',
  `BLOG_CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `BLOG_DELCHECK` int(11) NOT NULL,
  `BLOG_DEPTH` int(11) NOT NULL,
  `BLOG_ENTITY_NAME` varchar(30) DEFAULT NULL,
  `BLOG_HITS` int(11) NOT NULL,
  `BLOG_IDX` bigint(20) DEFAULT NULL,
  `BLOG_LIKES` int(11) NOT NULL,
  `BLOG_MODIFIED_BY` varchar(60) DEFAULT NULL,
  `BLOG_MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `BLOG_PORTFOLIO_TYPE` varchar(30) DEFAULT NULL,
  `BLOG_TITLE` varchar(150) NOT NULL DEFAULT '',
  PRIMARY KEY (`BLOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `TB_BLOG` WRITE;
/*!40000 ALTER TABLE `TB_BLOG` DISABLE KEYS */;

INSERT INTO `TB_BLOG` (`BLOG_ID`, `BLOG_CONTENT`, `BLOG_CREATED_BY`, `BLOG_CREATED_DATE`, `BLOG_DELCHECK`, `BLOG_DEPTH`, `BLOG_ENTITY_NAME`, `BLOG_HITS`, `BLOG_IDX`, `BLOG_LIKES`, `BLOG_MODIFIED_BY`, `BLOG_MODIFIED_DATE`, `BLOG_PORTFOLIO_TYPE`, `BLOG_TITLE`)
VALUES
	(1084,'<p style=\"padding: 0px; outline: none; color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\"><span style=\"color: rgb(51, 51, 51); font-family: Arial; font-size: 24pt;\"><b><span style=\"color: rgb(255, 94, 0);\"><br class=\"Apple-interchange-newline\"># Equals 와 Hashcode 사용하기 (너무 좋은 글, 꼭 읽어보기)</span></b></span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\"><br></span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">안녕하세요? 이번 시간엔&nbsp;</span><strong><span style=\"font-family: Arial;\">equals&nbsp;&amp; hashcode</span></strong><span style=\"font-family: Arial;\">를 어떤 곳에서 사용할 수 있는지를 확인해보려고 합니다. 모든 코드는</span><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"https://github.com/jojoldu/blog-code/tree/master/business-logic\" rel=\"nofollow\" style=\"color: rgb(42, 100, 150);\"><span style=\"font-family: Arial;\">Github</span></a><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"https://github.com/jojoldu/blog-code/tree/master/business-logic\" target=\"_blank\" title=\"새창으로 열기\" style=\"color: rgb(42, 100, 150);\"><span class=\"fa fa-external-link\" style=\"transform: translate(0px, 0px);\"></span></a><span style=\"font-family: Arial;\">에 있기 때문에 함께 보시면 더 이해하기 쉬우실 것 같습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">(공부한 내용을 정리하는</span><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"https://github.com/jojoldu/blog-code\" rel=\"nofollow\" style=\"color: rgb(42, 100, 150);\"><span style=\"font-family: Arial;\">Github</span></a><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"https://github.com/jojoldu/blog-code\" target=\"_blank\" title=\"새창으로 열기\" style=\"color: rgb(42, 100, 150);\"><span class=\"fa fa-external-link\" style=\"transform: translate(0px, 0px);\"></span></a><span style=\"font-family: Arial;\">와 세미나+책 후기를 정리하는</span><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"https://github.com/jojoldu/review\" rel=\"nofollow\" style=\"color: rgb(42, 100, 150);\"><span style=\"font-family: Arial;\">Github</span></a><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"https://github.com/jojoldu/review\" target=\"_blank\" title=\"새창으로 열기\" style=\"color: rgb(42, 100, 150);\"><span class=\"fa fa-external-link\" style=\"transform: translate(0px, 0px);\"></span></a><span style=\"font-family: Arial;\">, 이 모든 내용을 담고 있는</span><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"http://jojoldu.tistory.com/\" rel=\"nofollow\" style=\"color: rgb(42, 100, 150);\"><span style=\"font-family: Arial;\">블로그</span></a><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"http://jojoldu.tistory.com/\" target=\"_blank\" title=\"새창으로 열기\" style=\"color: rgb(42, 100, 150);\"><span class=\"fa fa-external-link\" style=\"transform: translate(0px, 0px);\"></span></a><span style=\"font-family: Arial;\">가 있습니다. )</span><br></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">java를 사용하면서 equals와 hashcode를 언제 override하면 좋을지 혹은 그런것이 있는지 모르시는 분들을 몇번 뵙게 되었습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">피부에 와닿을 수 있는 적절한 예제를 떠올리지 못하다가 마침 업무에서 사용할 기회가 되어서 적용후 포스팅을 시작하게 되었습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">틀린 내용이나 더 좋은 해결책이 있으신 경우엔 언제든지 가감없이 댓글 혹은 pull request 부탁드리겠습니다!</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">그럼 이제 시작하겠습니다.</span></p><h3 style=\"margin-top: 20px; margin-right: 0px; margin-left: 0px; padding: 0px; outline: none; font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum; line-height: 1.1; color: rgb(51, 51, 51); font-size: 24px;\"><span style=\"font-family: Arial;\">문제 상황</span></h3><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">프랜차이즈 매장들의 매출 정산을 해야한다고 가정하겠습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">해당 매장들은 결제가 발생할때마다 아래와 같은 형태로 DB에 데이터를 전송합니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"http://cfile9.uf.tistory.com/image/24686A3358D634232E70A5\" alt=\"payment db\"></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">각 컬럼은</span></p><ul style=\"margin-right: 0px; margin-left: 0px; padding: 0px; outline: none; list-style: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">calculate_code : 수수료 계산 방식 fk</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">method : 결제수단 (모바일, 카드, 현금)</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">owner_id : 매장 fk</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">pay_date : 결제일</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">price : 결제금액</span></li></ul><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">그리고 위 테이블의 데이터를 가공하여 아래와 같이 매출 테이블에 등록해야합니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"https://file.okky.kr/images/1490620958759.png\" style=\"width: 662px;\"><br></p><ul style=\"margin-right: 0px; margin-left: 0px; padding: 0px; outline: none; list-style: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">calculate_code : 수수료 계산 방식 fk</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">cash_amount : 현금 총 결제금액</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">creditcardamount : 카드 총 결제금액</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">mobile_amount : 모바일 총 결제금액</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">total_amount : 총 결제 금액</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">owner_id : 매장 fk</span></li><li style=\"margin: 0px; padding: 0px; outline: none; list-style: none;\"><span style=\"font-family: Arial;\">pay_date : 결제일</span></li></ul><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">두 데이터의 형태가 비슷해보이지만 미묘하게 다른 부분이 보이실것입니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">즉, payment의</span><span style=\"font-family: Arial;\">&nbsp;</span><strong><span style=\"font-family: Arial;\">calculatecode/ownerid/pay_date</span></strong><span style=\"font-family: Arial;\">를 기준으로 sales 데이터를 만든 것입니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">이번 시간에 만들 것은</span><span style=\"font-family: Arial;\">&nbsp;</span><strong><span style=\"font-family: Arial;\">payment 테이블의 데이터를 sales 테이블 데이터로 전환</span></strong><span style=\"font-family: Arial;\">하는 모듈입니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">어떻게 보면 크게 어려울것 같지 않은데, 직접 만들어보면서 어떤 문제가 있을지 확인하겠습니다.</span></p><h3 style=\"margin-top: 20px; margin-right: 0px; margin-left: 0px; padding: 0px; outline: none; font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum; line-height: 1.1; color: rgb(51, 51, 51); font-size: 24px;\"><span style=\"font-family: Arial;\">문제 해결</span></h3><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">먼저 build.gradle을 만들겠습니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><strong><span style=\"font-family: Arial;\">build.gradle</span></strong></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"http://cfile6.uf.tistory.com/image/26545F3C58D6342409A307\" alt=\"build.gradle\"></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">lombok, jpa, h2등 의존성을 추가하였습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">특이하게 보실수 있을 것은,</span><span style=\"font-family: Arial;\">&nbsp;</span><code style=\"margin: 0px; outline: none; font-size: 12.6px;\"><span style=\"font-family: Arial;\">ext[\'hibernate.version\'] = \'5.2.8.Final\'</span></code><span style=\"font-family: Arial;\">입니다.</span></p><blockquote style=\"padding: 0px 20px; outline: none; quotes: none; font-size: 15px; font-style: italic; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><p style=\"padding: 0px; outline: none;\"><span style=\"font-family: Arial;\">Java8이 들어오며 대부분의 date 타입을 LocalDate와 LocalDateTime을 사용하실텐데요, 해당 타입으로 DB를 생성하면, binary 타입이 생성됩니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">이를 방지하기 위해</span><span style=\"font-family: Arial;\">&nbsp;</span><code style=\"margin: 0px; outline: none; font-style: normal; font-size: 13.5px;\"><span style=\"font-family: Arial;\">AttributeConverter</span></code><span style=\"font-family: Arial;\">을 사용해서 추가 설정을 할수도 있지만, 하이버네이트 최근 버전에서는 이미 해결했기 때문에 spring-data-jpa에서 사용하는 하이버네이트를 강제로 오버라이딩 시킨것입니다. 이렇게 할 경우 추가 설정이 필요 없어집니다.</span></p></blockquote><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">다음은 Entity 클래스들을 만들겠습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">Entity 클래스 작성시에는 불필요한 코드 작성을 피하기 위해 롬복(lombok)을 적극 사용하겠습니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><strong><span style=\"font-family: Arial;\">Payment.java</span></strong></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"http://cfile30.uf.tistory.com/image/263EBE3D58D63424363F62\" alt=\"Payment\"></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">lombok의</span><span style=\"font-family: Arial;\">&nbsp;</span><code style=\"margin: 0px; outline: none; font-size: 12.6px;\"><span style=\"font-family: Arial;\">@builder</span></code><span style=\"font-family: Arial;\">를 사용하면 생성자에 인자로 지정된 값들에 한해 Builer를 지원합니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">혹시 builder 혹은 빌더패턴을 처음 보신다면</span><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"http://seotory.tistory.com/29\" rel=\"nofollow\" style=\"color: rgb(42, 100, 150);\"><span style=\"font-family: Arial;\">링크</span></a><span style=\"font-family: Arial;\">&nbsp;</span><a href=\"http://seotory.tistory.com/29\" target=\"_blank\" title=\"새창으로 열기\" style=\"color: rgb(42, 100, 150);\"><span class=\"fa fa-external-link\" style=\"transform: translate(0px, 0px);\"></span></a><span style=\"font-family: Arial;\">를 참고하세요!</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">getter 메소드들과 기본생성자까지 모두 lombok으로 지정하였습니다.</span></p><blockquote style=\"padding: 0px 20px; outline: none; quotes: none; font-size: 15px; font-style: italic; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><p style=\"padding: 0px; outline: none;\"><span style=\"font-family: Arial;\">Method enum을 Payment 클래스 내부에 작성하였습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">이렇게 하면 enum이 Payment 클래스와 직접적으로 관계가 있음을 코드상으로 표현할 수 있을 뿐더러, 다른 클래스에서 사용할때도 Payment.Method 가 되어 이름의 중복없이 깔끔하게 사용할 수 있습니다.</span></p></blockquote><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><strong><span style=\"font-family: Arial;\">Sales.java</span></strong></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"https://file.okky.kr/images/1490620782573.png\" style=\"width: 662px;\"><br></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">Sales도 Payment와 마찬가지로 lombok을 사용하여 구성을 마쳤는데, 한가지 메소드가 추가되었습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">Sales에 amount와 Payment.Method 값을 입력하면 Method에 맞춰</span><span style=\"font-family: Arial;\">&nbsp;</span><code style=\"margin: 0px; outline: none; font-size: 12.6px;\"><span style=\"font-family: Arial;\">+=</span></code><span style=\"font-family: Arial;\">연산이 수행됩니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">해당 연산은</span><span style=\"font-family: Arial;\">&nbsp;</span><strong><span style=\"font-family: Arial;\">Sales의 핵심 로직</span></strong><span style=\"font-family: Arial;\">입니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">이런 연산은 서비스 계층에서 해결하기보다는 Entity 계층에서 해결하고 이를 서비스 계층에서 호출하는 것이 더 좋습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><strong><span style=\"font-family: Arial;\">핵심 로직이 흩어지지 않고 Entity에 집중</span></strong><span style=\"font-family: Arial;\">되기 때문입니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">기본 배경이 되는 Entity 설계는 끝이났습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">이젠 테스트용 데이터를 생성하여 해당 데이터로 로직을 구현해보겠습니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><strong><span style=\"font-family: Arial;\">ApplicationTest.java</span></strong></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"http://cfile8.uf.tistory.com/image/214B724358D6342419C9BE\" alt=\"테스트용 데이터\"></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">(보시는것처럼</span><span style=\"font-family: Arial;\">&nbsp;</span><code style=\"margin: 0px; outline: none; font-size: 12.6px;\"><span style=\"font-family: Arial;\">@builder</span></code><span style=\"font-family: Arial;\">를 통해 별도의 코드 생성없이 Payment의 builder를 사용할 수 있습니다.)</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">이제 핵심 로직을 작성할 시간입니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">한번 고민해보겠습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">Payment의 ownerId, payDate, calculateCode를 기준으로 Payment를 분류한뒤, 이들을 합하여 각각의 Sales를 만들어야 합니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"https://file.okky.kr/images/1490620810575.png\" style=\"width: 662px;\"><br></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">(그림으로 표현한 분류도)</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">Payment 리스트를 반복문으로 돌리면서 분류한다 하였을때,</span><span style=\"font-family: Arial;\">&nbsp;</span><strong><span style=\"font-family: Arial;\">기존에 분류된 Payment 리스트에 새로 분류된 Payment는 어떻게 추가</span></strong><span style=\"font-family: Arial;\">할 수 있을까요?</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">분류된 Payment 리스트를 다시 반복문으로 돌리면서 비교한 뒤, 만족하면 리스트에 추가하고, 같은게 없을 경우 새로운 List를 만들어야 할까요?</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">분류된 Payment리스트를 다시 반복 비교하지 않고, ownerId, payDate, calculateCode 조합이 이미 분류되었는지 아닌지 어떻게 확인할 수 있을까요?</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">좀더 간단하게 생각해볼까요?</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">List의 경우 해당 값이 존재하는지 안하는지 파악하기 위해선 반복문을 사용할 수 밖에 없습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">그렇다면 List 외에 자료구조 중,</span><span style=\"font-family: Arial;\">&nbsp;</span><strong><span style=\"font-family: Arial;\">Key가 되는 특정 값이 있는지 없는지 한번에 확인</span></strong><span style=\"font-family: Arial;\">할 수 있는 것은 어떤게 있을까요?</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">Map입니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">Map은</span><span style=\"font-family: Arial;\">&nbsp;</span><code style=\"margin: 0px; outline: none; font-size: 12.6px;\"><span style=\"font-family: Arial;\">get(key)</span></code><span style=\"font-family: Arial;\">를 통해 해당 key값이 등록되어있는지 아닌지를 바로 확인할 수 있으니 추가로 반복문 수행이 필요없습니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">여기서 의문이 드시는게 어떻게 ownerId, payDate, calculateCode 이 3개 값을 한번에 Map의 key로 사용할것인가 이실텐데요.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">이때 사용되는 것이 바로</span><span style=\"font-family: Arial;\">&nbsp;</span><code style=\"margin: 0px; outline: none; font-size: 12.6px;\"><span style=\"font-family: Arial;\">hashCode</span></code><span style=\"font-family: Arial;\">입니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">Map의 구현체인 HashMap이나 LinkedHashMap을 보시면 바로 이 인스턴스의 hashCode 메소드 결과를 통해 key를 비교한다는 것을 확인할 수 있습니다.</span></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"http://cfile7.uf.tistory.com/image/25471D4F58D634233660AC\" alt=\"get\"></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><img src=\"http://cfile9.uf.tistory.com/image/2527844D58D634232DFD0E\" alt=\"hash\"></p><p style=\"padding: 0px; outline: none; font-size: 14px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><span style=\"font-family: Arial;\">즉,</span><span style=\"font-family: Arial;\">&nbsp;</span><strong><span style=\"font-family: Arial;\">인스턴스의 hashCode 메소드 결과가 같다면 동일한 key로 간주</span></strong><span style=\"font-family: Arial;\">하겠다는 것입니다.</span><span style=\"font-family: Arial;\">&nbsp;</span><br><span style=\"font-family: Arial;\">우린 이 점을 이용해서 Payment를 분류하겠습니다.</span></p><blockquote style=\"padding: 0px 20px; outline: none; quotes: none; font-size: 15px; font-style: italic; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Apple SD Gothic Neo&quot;, &quot;Malgun Gothic&quot;, Dotdum;\"><p style=\"padding: 0px; outline: none;\"><span style=\"font-family: Arial;\">equals도 hashCode와 사용되는 곳은 비슷합니다.</span><br><span style=\"font-family: Arial;\">해당 인스턴스들이 갖고 있는 값들이 같을 경우 같은 인스턴스로 봐야하는 경우엔 equals를 오버라이딩하여 사용합니다.</span><span style=\"font-family: Arial;\">&nbsp;</span></p></blockquote><p><br style=\"color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\"><br style=\"color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\"><span style=\"color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\">출처:&nbsp;</span><a href=\"http://postitforhooney.tistory.com/entry/SpringLombok-Equals-%EC%99%80-Hashcode-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-%ED%8D%BC%EC%98%B4\" style=\"color: rgb(51, 51, 51); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px; background-color: rgb(255, 255, 255);\">http://postitforhooney.tistory.com/entry/SpringLombok-Equals-와-Hashcode-사용하기-퍼옴</a><span style=\"color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\">&nbsp;[PostIT]</span><br style=\"color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\"><br style=\"color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\"><span style=\"color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\">출처:&nbsp;</span><a href=\"http://postitforhooney.tistory.com/entry/SpringLombok-Equals-%EC%99%80-Hashcode-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-%ED%8D%BC%EC%98%B4\" style=\"color: rgb(51, 51, 51); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px; background-color: rgb(255, 255, 255);\">http://postitforhooney.tistory.com/entry/SpringLombok-Equals-와-Hashcode-사용하기-퍼옴</a><span style=\"color: rgb(102, 102, 102); font-family: &quot;Noto Sans&quot;, sans-serif; font-size: 14px;\">&nbsp;[PostIT]</span><br></p>','2222','2017-04-04 22:44:16',0,0,'NOTICE',1,NULL,0,NULL,'2017-04-04 22:44:20','JAVA','롬복과 해시코드 그리고 equals');

/*!40000 ALTER TABLE `TB_BLOG` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table TB_COMMENT
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_COMMENT`;

CREATE TABLE `TB_COMMENT` (
  `COMMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMMENT_BLOG_ID` bigint(20) NOT NULL,
  `COMMENT_CREATED_BY` varchar(60) NOT NULL,
  `COMMENT_MODIFIED_BY` varchar(60) DEFAULT NULL,
  `COMMENT_CONTENT` longtext NOT NULL,
  `COMMENT_CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `COMMENT_DELCHECK` int(11) NOT NULL,
  `COMMENT_LIKES` int(11) DEFAULT NULL,
  `COMMENT_MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`COMMENT_ID`),
  KEY `COMMENT_BOARD_FK` (`COMMENT_BLOG_ID`),
  CONSTRAINT `COMMENT_BOARD_FK` FOREIGN KEY (`COMMENT_BLOG_ID`) REFERENCES `tb_blog` (`BLOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table TB_FILE_DATA
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_FILE_DATA`;

CREATE TABLE `TB_FILE_DATA` (
  `FILE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FILE_BLOG_ID` bigint(20) NOT NULL,
  `FILE_SAVED_NAME` varchar(200) NOT NULL,
  `FILE_SAVED_PATH` varchar(200) NOT NULL,
  `FILE_ORIGIN_NAME` varchar(100) NOT NULL,
  `FILE_SIZE` bigint(20) NOT NULL,
  `FILE_TYPE` varchar(20) NOT NULL,
  `FILE_CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FILE_CREATED_BY` varchar(60) NOT NULL,
  `FILE_MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `FILE_MODIFIED_BY` varchar(60) DEFAULT NULL,
  `FILE_DELCHECK` int(11) NOT NULL,
  PRIMARY KEY (`FILE_ID`),
  KEY `FILE_BLOG_FK` (`FILE_BLOG_ID`),
  CONSTRAINT `FILE_BLOG_FK` FOREIGN KEY (`FILE_BLOG_ID`) REFERENCES `TB_BLOG` (`BLOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table TB_MUSIC
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_MUSIC`;

CREATE TABLE `TB_MUSIC` (
  `MUSIC_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MUSIC_CREATED_BY` varchar(60) NOT NULL,
  `MUSIC_MODIFIED_BY` varchar(60) DEFAULT NULL,
  `MUSIC_CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MUSIC_DELCHECK` int(11) NOT NULL,
  `MUSIC_IMAGE` varchar(255) NOT NULL,
  `MUSIC_LYRICS` varchar(255) DEFAULT NULL,
  `MUSIC_MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `MUSIC_SINGER` varchar(255) NOT NULL,
  `MUSIC_TITLE` varchar(255) NOT NULL,
  `MUSIC_URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MUSIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table TB_PERSISTENT_LOGINS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_PERSISTENT_LOGINS`;

CREATE TABLE `TB_PERSISTENT_LOGINS` (
  `PERSISTENT_SERIES` varchar(100) NOT NULL,
  `PERSISTENT_CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PERSISTENT_EMAIL` varchar(60) NOT NULL,
  `PERSISTENT_TOKEN` varchar(100) NOT NULL,
  PRIMARY KEY (`PERSISTENT_SERIES`),
  UNIQUE KEY `UK_4elfobbskdy6kt1upjn0r5lyc` (`PERSISTENT_EMAIL`),
  UNIQUE KEY `UK_4ci2ejthiyko7i0u2r2w4o9rg` (`PERSISTENT_TOKEN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `TB_PERSISTENT_LOGINS` WRITE;
/*!40000 ALTER TABLE `TB_PERSISTENT_LOGINS` DISABLE KEYS */;

INSERT INTO `TB_PERSISTENT_LOGINS` (`PERSISTENT_SERIES`, `PERSISTENT_CREATED_DATE`, `PERSISTENT_EMAIL`, `PERSISTENT_TOKEN`)
VALUES
	('xpkNT7OGzGLqbUVqEthTLw==','2017-03-16 14:18:55','shun10114@gmail.com','GjBAc2JVBnU8MhyDDG9ZRw==');

/*!40000 ALTER TABLE `TB_PERSISTENT_LOGINS` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table TB_USER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_USER`;

CREATE TABLE `TB_USER` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_DELCHECK` int(11) NOT NULL,
  `USER_EMAIL` varchar(60) NOT NULL,
  `USER_LOCKED_AUTH` varchar(100) DEFAULT NULL,
  `USER_MODIFIED_BY` varchar(60) DEFAULT NULL,
  `USER_MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_NICKNAME` varchar(30) NOT NULL,
  `USER_PASSWORD` varchar(100) NOT NULL,
  `USER_RECEIVE_EMAIL` int(11) NOT NULL,
  `USER_STATE` varchar(20) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UK_idwnephhbxa1ssgxrodu90pru` (`USER_EMAIL`),
  UNIQUE KEY `UK_24e1ry1dmddo806n2eu664sfx` (`USER_NICKNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `TB_USER` WRITE;
/*!40000 ALTER TABLE `TB_USER` DISABLE KEYS */;

INSERT INTO `TB_USER` (`USER_ID`, `USER_CREATED_DATE`, `USER_DELCHECK`, `USER_EMAIL`, `USER_LOCKED_AUTH`, `USER_MODIFIED_BY`, `USER_MODIFIED_DATE`, `USER_NICKNAME`, `USER_PASSWORD`, `USER_RECEIVE_EMAIL`, `USER_STATE`)
VALUES
	(1,'2017-03-14 09:22:23',0,'shun10114@gmail.com',NULL,NULL,'2017-03-15 15:46:04','2222','$2a$10$BAbUl4VdlPNSgmniNsR6lub.jcOaevQ/tfMPA/yDeW037EojZqpdS',0,'ACTIVE'),
	(2,'2017-03-14 11:17:22',0,'shun10114@gmail.co',NULL,NULL,'2017-03-17 09:58:46','22321','$2a$10$eDtfRRIGSKQTnR0k76zQXOmDoTHaEx8nEJL4hGdwcQBIPYO96a83e',0,'ACTIVE'),
	(3,'2017-03-14 16:09:55',0,'cunsangbi@naver.com',NULL,NULL,'2017-03-17 09:58:46','43242342','$2a$10$pJWh6A0WqRDuu1feQs0Nj.KqDeCU6Z23t5Zo5QFS9IneNfflJcDUy',0,'ACTIVE'),
	(7,'2017-03-14 17:35:11',0,'cunsadsangbi@naver.com',NULL,NULL,'2017-03-17 09:58:46','dsa2','$2a$10$03wo0MsL83yBvaJCvNF2S.Mkzjk7qy3RFDwVSJeuEohwxJuVXYjwy',0,'ACTIVE'),
	(9,'2017-03-15 13:37:45',0,'ewqeqw@sdda.com',NULL,NULL,'2017-03-17 09:58:46','ewqcxzzx','$2a$10$KsiUtlzmAY.2j8WJ.QRMBOj9fXLQ8RAB3JTHASoz2n8posGnvAn4K',0,'ACTIVE'),
	(10,'2017-03-15 13:47:36',0,'shun32110114@gmail.com',NULL,NULL,'2017-03-17 09:58:46','vczxvcxz','$2a$10$W6.wbh6OmGEDHF4qg7HT.OKQPEOtE0CKPOmMuhMbN17b05fGFUUzG',0,'ACTIVE'),
	(11,'2017-03-15 14:00:07',0,'sssqda@dafda.com',NULL,NULL,'2017-03-17 09:58:46','2312321312dsa','$2a$10$ZCw67xkkSDIIMD7ho0V0KeV7YUhDxpb9azhArFF/cKD1WxOiIutq2',0,'ACTIVE'),
	(12,'2017-03-15 15:42:30',0,'2222@gmail.com',NULL,NULL,'2017-03-17 09:58:46','dsacxzcxz','$2a$10$cyPQZQBKQyvWpC9ZhQ83IeQu.a7Zs4eWirx208rndJp35Cnet7b2G',0,'ACTIVE'),
	(13,'2017-03-31 15:36:58',0,'cu32nsangbi@naver.com',NULL,NULL,'2017-03-31 15:36:58','3333','$2a$10$OskiGmVB0fFpj/XObM0hde/Iwks/kCY/EWBWeoMXsS2RntuZ0spsW',0,'ACTIVE');

/*!40000 ALTER TABLE `TB_USER` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table TB_USER_ATTEMPTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_USER_ATTEMPTS`;

CREATE TABLE `TB_USER_ATTEMPTS` (
  `USER_ATTEMPTS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ATTEMPTS_COUNTS` int(11) NOT NULL,
  `USER_ATTEMPTS_CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ATTEMPTS_EMAIL` varchar(60) NOT NULL,
  `USER_ATTEMPTS_LOGIN_IP` varchar(60) DEFAULT NULL,
  `USER_ATTEMPTS_SUCCESS_FLAG` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ATTEMPTS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `TB_USER_ATTEMPTS` WRITE;
/*!40000 ALTER TABLE `TB_USER_ATTEMPTS` DISABLE KEYS */;

INSERT INTO `TB_USER_ATTEMPTS` (`USER_ATTEMPTS_ID`, `USER_ATTEMPTS_COUNTS`, `USER_ATTEMPTS_CREATED_DATE`, `USER_ATTEMPTS_EMAIL`, `USER_ATTEMPTS_LOGIN_IP`, `USER_ATTEMPTS_SUCCESS_FLAG`)
VALUES
	(48,1,'2017-03-15 15:42:05','shun10114@gmail.com','',1),
	(49,0,'2017-03-15 15:42:44','2222@gmail.com','127.0.0.1',0),
	(50,0,'2017-03-15 15:46:09','shun10114@gmail.com','127.0.0.1',0),
	(51,1,'2017-03-15 15:46:50','2222@gmail.com','',1),
	(52,0,'2017-03-15 15:46:53','2222@gmail.com','127.0.0.1',0),
	(53,0,'2017-03-15 15:48:24','2222@gmail.com','127.0.0.1',0),
	(54,0,'2017-03-15 15:48:44','2222@gmail.com','127.0.0.1',0),
	(55,0,'2017-03-15 15:48:49','shun10114@gmail.com','127.0.0.1',0),
	(56,0,'2017-03-15 17:00:49','shun10114@gmail.com','127.0.0.1',0),
	(57,0,'2017-03-16 13:12:57','shun10114@gmail.com','127.0.0.1',0),
	(58,0,'2017-03-16 13:18:11','shun10114@gmail.com','127.0.0.1',0),
	(59,0,'2017-03-16 14:19:06','shun10114@gmail.com','127.0.0.1',0),
	(60,0,'2017-03-16 14:22:51','shun10114@gmail.com','127.0.0.1',0),
	(61,0,'2017-03-16 14:23:01','shun10114@gmail.com','127.0.0.1',0),
	(62,0,'2017-03-17 08:47:32','shun10114@gmail.com','127.0.0.1',0),
	(63,0,'2017-03-17 09:55:37','shun10114@gmail.com','127.0.0.1',0),
	(64,0,'2017-03-21 09:35:44','shun10114@gmail.com','127.0.0.1',0),
	(65,0,'2017-03-21 09:42:55','shun10114@gmail.com','127.0.0.1',0),
	(66,0,'2017-03-23 18:14:44','shun10114@gmail.com','127.0.0.1',0),
	(67,0,'2017-03-24 12:52:28','shun10114@gmail.com','127.0.0.1',0),
	(68,0,'2017-03-24 13:26:13','shun10114@gmail.com','127.0.0.1',0),
	(69,0,'2017-03-24 13:38:01','shun10114@gmail.com','127.0.0.1',0),
	(70,0,'2017-03-24 14:23:35','shun10114@gmail.com','127.0.0.1',0),
	(71,0,'2017-03-24 14:27:41','shun10114@gmail.com','127.0.0.1',0),
	(72,0,'2017-03-24 15:20:09','shun10114@gmail.com','127.0.0.1',0),
	(73,0,'2017-03-24 17:42:50','shun10114@gmail.com','127.0.0.1',0),
	(74,0,'2017-03-27 18:19:33','shun10114@gmail.com','127.0.0.1',0),
	(75,0,'2017-03-28 18:46:32','shun10114@gmail.com','127.0.0.1',0),
	(76,0,'2017-03-28 19:26:27','shun10114@gmail.com','127.0.0.1',0),
	(77,0,'2017-03-29 08:50:54','shun10114@gmail.com','127.0.0.1',0),
	(78,0,'2017-03-30 08:52:14','shun10114@gmail.com','127.0.0.1',0),
	(79,0,'2017-04-04 22:41:03','shun10114@gmail.com','127.0.0.1',0);

/*!40000 ALTER TABLE `TB_USER_ATTEMPTS` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table TB_USER_PROFILE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_USER_PROFILE`;

CREATE TABLE `TB_USER_PROFILE` (
  `USER_PROFILE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_PROFILE_TYPE` varchar(15) NOT NULL,
  PRIMARY KEY (`USER_PROFILE_ID`),
  UNIQUE KEY `UK_28lgbv40bmqn2twgoy5kxn5fx` (`USER_PROFILE_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `TB_USER_PROFILE` WRITE;
/*!40000 ALTER TABLE `TB_USER_PROFILE` DISABLE KEYS */;

INSERT INTO `TB_USER_PROFILE` (`USER_PROFILE_ID`, `USER_PROFILE_TYPE`)
VALUES
	(1,'GUEST'),
	(3,'STAR'),
	(4,'SUPERADMIN'),
	(2,'USER');

/*!40000 ALTER TABLE `TB_USER_PROFILE` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table TB_USER_PROFILE_REFER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TB_USER_PROFILE_REFER`;

CREATE TABLE `TB_USER_PROFILE_REFER` (
  `USER_ID` bigint(20) NOT NULL,
  `USER_PROFILE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`USER_PROFILE_ID`),
  KEY `FKf8b4mqr1f1rgnfv24to0qa7rq` (`USER_PROFILE_ID`),
  CONSTRAINT `FKf8b4mqr1f1rgnfv24to0qa7rq` FOREIGN KEY (`USER_PROFILE_ID`) REFERENCES `TB_USER_PROFILE` (`USER_PROFILE_ID`),
  CONSTRAINT `FKrpo7q8qoeejkifqth4apl0bv9` FOREIGN KEY (`USER_ID`) REFERENCES `TB_USER` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `TB_USER_PROFILE_REFER` WRITE;
/*!40000 ALTER TABLE `TB_USER_PROFILE_REFER` DISABLE KEYS */;

INSERT INTO `TB_USER_PROFILE_REFER` (`USER_ID`, `USER_PROFILE_ID`)
VALUES
	(1,4),
	(2,1),
	(3,1),
	(7,1),
	(9,1),
	(10,1),
	(11,1),
	(12,1),
	(13,1);

/*!40000 ALTER TABLE `TB_USER_PROFILE_REFER` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
