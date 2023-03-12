using spring boot 2.7.5
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

2.naver 로그인시 status 500 에러가 발생
에러내용 : org.springframework.dao.DataIntegrityViolationException: not-null property references a null or transient value
원인 : google은 attribute.get()을 써서 값을 받아오지만 naver는 response를 써서 값을 받아온다. 오타였다.
