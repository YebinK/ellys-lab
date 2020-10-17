# Spring Cache

도란도란 프로젝트의 전체조회(`findAll()`) 성능을 높이기 위해 Cache 실습을 해본다.

## 캐시 적용 전
![image](https://user-images.githubusercontent.com/19922698/96353048-88a4ae00-1103-11eb-9965-bf1b46174d51.png)

<br/>

3초 단위로 가져오는 것을 볼 수 있다. 

<br/>

## 캐시(`@Cacheable`) 적용 후
![image](https://user-images.githubusercontent.com/19922698/96353173-b3dbcd00-1104-11eb-9ccc-7f089ea38f8c.png)

<br/>

캐시된 부분에 한해서는 3초 딜레이 없이 바로 가져온다.
즉, `PostRepository.findByIdWithCache()`로직을 타기 전에 가져온다.

<br/>

## `@Cacheable`의 동작 원리? 어디에 저장되지?
1. Spring AOP로 동작한다. (AOP 의존성은 `spring-boot-starter-web`에 포함되어 있다.)
2. 어노테이션은 `class`, `method`에 걸 수 있다.
![image](https://user-images.githubusercontent.com/19922698/96353630-e5569780-1108-11eb-9069-e4548f2b462a.png)
3. 메모리에 저장한다. (생각해보니 당연한 이야기!)

<br/>

## 캐시를 언제 써야 할까?
1. 반복적으로 같은 결과를 리턴해야 할 때
2. 시간이 오래 걸리는 작업을 할 때 (API 호출, 데이터베이스 쿼리 등)
    단, 데이터가 자주 변경되어 결과도 자주 바뀌거나, 소요 시간이 짧은 작업들은 굳이 할 필요가 없다. 오히려 역효과가 날 수도 있다. (캐시 확인, 저장 과정이 추가되기 때문)
    
    
<br/>

---

### Q. 도란도란 프로젝트의 글 전체조회 시에 캐시를 적용하는 게 과연 성능 향상에 도움이 될까?

결론부터 말하자면 그렇지 않다. 글은 자주 자주 올라오고, 그럼 매번 캐시를 갱신해주어야 하는 문제가 생긴다. (사용자가 많아질수록 역효과가 날 수 있다.)
특정 지도 범위 내의 글만 받아오거나, 인덱스를 태우는 방식이 더 적합할 수도 있겠다.  