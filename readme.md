# spring-boot-project
### 간단한 게시판을 만드는 프로젝트 입니다.
--------
#### 사용한 개발 스택
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/></a>  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"/></a>  <img src="https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=mariadb&logoColor=white"/></a>  <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white"/></a>  <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=amazonrds&logoColor=white"/></a>  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=white"/></a>


---------
웹 사이트 주소
http://ec2-13-124-18-162.ap-northeast-2.compute.amazonaws.com:8080

------
### 사용방법

  1.google 혹은 naver로 로그인한다.


      1-1.google 로그인일 경우 사용자의 이메일과 닉네임을 필수로 제공해야합니다. 
      1-2.naver 로그인일 경우 사용자의 이름,이메일,별명,프로필 사진을 필수로 제공해야합니다.(제공하신 실명은 실제로 사용되지 않습니다.)

  2.로그인 후 글 등록을 통해 글 작성을 하면 완료


<br><br><br><br><br><br>

<br><br><br><br><br><br><br><br><br><br><br>
스프링 부트 3.2.x 버전에서 구현하고 싶은 사람들의 에러 해결을 조금이라도 돕기 위해서 쓴 글

spring boot 3.2.1
using sdk 17
gradle version 7.5.1


---------uploading-------------------

사용하는 책 : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 
              저자 이동욱

코드 짜면서 겪었던 문제점

1.database 연동 불가
  mysql과의 연동 실패
원인 : spring boot 의 버전을 변경하면서 application.properties에 있던 내용을 변경해줘야 한다.
해결 : spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect 삭제

2.extends WebSecurityConfigurerAdapter를 찾을 수 없다는 오류

원인 : spring boot의 버전을 3.0이상을 사용하게 되면서 Spring Security의 설정 방식이 변경하게 됨
해결 : extends WebSecurityConfigurerAdapter를 제거하고
       @Override를 @bean으로 변경
       protected void configure(HttpSecurity http) throws Exception 를 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 로 변경
       antMatcher() -> requestMatcher()로 변경
       return http.builder();를 추가


3.naver 로그인시 status 500 에러가 발생
에러내용 : org.springframework.dao.DataIntegrityViolationException: not-null property references a null or transient value
원인 : google은 attribute.get()을 써서 값을 받아오지만 naver는 response를 써서 값을 받아온다. 오타였다.


4.naver로 로그인 후 글 작성시 500에러를 반환
에러 내용 :No key, method or field with name 'modifiedDate' on line 35] with root cause
원인 : h2-console로 확인했을 때 CREATED_DATE  	MODIFIED_DATE 이 null로 반환 되는 것을 확인
application.java에 @EnableJpaAuditing를 추가
