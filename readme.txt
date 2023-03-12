using spring boot 2.6.6
using sdk 17
---------uploading-------------------

코드 짜면서 겪었던 문제점

1.naver 로그인시 status 500 에러가 발생
에러내용 : org.springframework.dao.DataIntegrityViolationException: not-null property references a null or transient value
원인 : attribute.get()에서 받는 값이 null로 인식이 안되는 현상이 발생
