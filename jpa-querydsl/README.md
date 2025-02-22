# JPA QueryDSL

## 컬렉션 페치조인과 페이징 API를 같이 썼을 때 메모리 초과 문제 발생
`Member` - `Team` 관계는 `N:1`

TeamQueryRepository.findThreeTeams()와 같이 페이징 API와 fetchJoin을 함께 사용할 경우,

```text
Hibernate: 
    select
        team0_.id as id1_1_0_,
        members1_.id as id1_0_1_,
        team0_.name as name2_1_0_,
        members1_.name as name2_0_1_,
        members1_.team_id as team_id3_0_1_,
        members1_.team_id as team_id3_0_0__,
        members1_.id as id1_0_0__ 
    from
        team team0_ 
    left outer join
        member members1_ 
            on team0_.id=members1_.team_id
``` 

발생한 쿼리는 다음과 같다. limit 쿼리가 나가지 않는다!

페치조인과 페이징을 함께 쓰면 페이징이 어플리케이션 단에서 조정된다.

### Q. 특정 Team에 해당하는 Member들을 페이징 처리해서 가져오고 싶다면 어떻게 해야할까?

우선 찾은 해결책은 Team에서 Member를 꺼내지 않고, **Member에 바로 접근**하는 것이다.
MemberQueryRepository.findMembersByTeam()을 참고하자.


<br/>
---
<br/>

## countDistinct() vs count() vs fetchCount() 뭐가 다를까?

**1. countDistinct()**
<br/>
> memberQueryRepository.findMembersCountDistinct()
```sql
select
    count(distinct member0_.id) as col_0_0_ 
from
    member member0_ limit ?
```
특이점: limit이 걸린다.
❗️ 전체 limit이 걸리는 것 같긴 한데 왜, 얼마나 걸리는지는 모르겠다. 아직 풀리지 않는 의문

**2. count()** 
<br/>
> memberQueryRepository.findMembersCount()
```sql
select
    count(member0_.id) as col_0_0_ 
from
    member member0_ limit ?
```
특이점: limit이 걸린다.

**3. fetchCount()** 
<br/>
> memberQueryRepository.findMembersFetchCount()
```sql
select
    count(member0_.id) as col_0_0_ 
from
    member member0_
```
특이점: count() 쿼리로 변경되어 실행된다. limit이 없다.