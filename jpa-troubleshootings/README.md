# JPA TroubleShootings

JPA 테스트를 진행하면서 마주친 문제들을 정리합니다.
<br/>
## LazyInitializationException

### 상황 설명
Post와 Comment 엔티티가 있고, 1:N 관계이다.
Post에 Comment를 추가하는 로직을 테스트하는 CommentServiceTest.createComment 테스트 도중
```
assertThat(persistPost.getComments()).hasSize(1);
```
부분에서
```
org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.ellyspace.jpatroubleshootings.Post.comments, could not initialize proxy - no Session
```
이런 예외가 발생했다.

**테스트 메소드에 트랜잭션이 걸려있지 않은데다가, Post를 통해 List<Comment\>를 꺼내오는 부분이 lazy loading으로 잡혔기 때문에 발생한 문제였다.**
<br/>

---

<br/>

### 해결 방법 1. Post 내부의 List<Comment\> 옵션을 fetch = FetchType.EAGER로 변경하기
```java
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private List<Comment> comments = new ArrayList<>();
```
비추천. Post를 조회할 때마다 매번 JOIN 쿼리를 날려서 Comment 정보까지 가져와야 하므로 매우 비효율적이다.
<br/>

### 해결 방법 2. 테스트 메소드에 @Transactional 붙이기
비추천. 테스트는 통과할지 몰라도, 트랜잭션이 보장되지 않는 상황에서 해당 메소드(CommentService.createComment)를 사용한다면, 자칫 테스트 코드는 통과하는데 프로덕션 코드는 의도한대로 동작하지 않는 상황이 발생할 수 있다.
➕ 한 가지 장점?이라면, 테스트 메소드에 @Transactional을 붙이면, 해당 테스트에서 건드린 영역에 대한 롤백을 자동으로 지원한다. (xxxRepository.deleteAll과 같은 수동적인 롤백 과정이 필요 없다.)
<br/>

### 해결 방법 3. JOIN FETCH로 테스트하기
Post와 Comment를 한 번에 조회하는 것이 관건이므로, PostRepository.fetchPost같은 `JOIN FETCH 쿼리`를 추가해서 테스트를 통과시킬 수 있다.
<br/>

### 해결 방법 4. 테스트 대상을 분리하기
Post와 Comment를 분리해서 생각하는 것인데, Comment 생성에 관한 테스트이므로, 단순히 Comment가 생성되었는지만 확인하는 방법이다.
```java
commentId = commentService.createComment(request);
persistComment = commentRepository.findById(commentId);

assertThat(persistComment).isNotNull();
```

