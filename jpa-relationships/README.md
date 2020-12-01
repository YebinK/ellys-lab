# JPA Relationships

JPA 연관관계들에 대한 여러가지 실험을 해보는 곳.
<br/>
## N : N
`Member` 엔티티와 `Mission` 엔티티가 있다.
`Member`는 `Mission`을 신청할 수 있고, 하나의 `Mission`은 여러 명의 `Member`에게 할당될 수 있다.
따라서 둘의 관계를 `N : N`으로 정의하고, 이를 `1 : N`, `N : 1`로 풀어보았다.
그러면서 Member - AssignInfo - Mission 이렇게 3개의 엔티티가 되었다.

## 1 : N
`Post` 엔티티와 `Comment` 엔티티가 있다.
하나의 Post에는 여러 개의 Comment가 달릴 수 있고, Comment의 생명주기는 Post에 종속적이기 때문에 
둘의 관계를 1 : N으로 정의했다.


## 궁금증: 일대다 단방향에서 @JoinColumn을 쓰는 것 vs 다대일 단방향에서 @JoinColumn을 쓰는 것
-> 차이가 없다. 둘 다 Member 쪽에 Team을 참조하는 칼럼이 생긴다. (테이블 상에서)
다만, 일대다 단방향에서 @JoinColumn을 같이 안 적어주면 디폴트로 조인 테이블을 생성하는 전략을 취한다.
하지만 일대다 단방향에서 @JoinColumn을 써버리면 Member 테이블에 FK 컬럼이 추가되고,
일대다 단방향에서는 `다`의 id가 null인 상태로 INSERT된다. 그리고 그만큼 UPDATE 쿼리가 추가로 나간다.

`Team`(One), `Member`(Many) 정의.

**1. 일대다 단방향 (@OneToMany)**
```
Hibernate: insert into team (id, name) values (null, ?)
Hibernate: insert into member (id, name) values (null, ?)
Hibernate: insert into member (id, name) values (null, ?)
Hibernate: insert into member (id, name) values (null, ?)
Hibernate: update member set team_member=? where id=?
Hibernate: update member set team_member=? where id=?
Hibernate: update member set team_member=? where id=?
Hibernate: select member0_.id as id1_1_, member0_.name as name2_1_ from member member0_ //memberRepository.findAll()
```
<br/>
일대다 단방향에서는 `다`의 FK 컬럼(team_member)이 null인 상태로 INSERT된다. 그리고 그만큼 FK 컬럼에 대한 UPDATE 쿼리가 추가로 나간다.


**2. 다대일 단방향 (@ManyToOne)**
```
Hibernate: insert into team (id, name) values (null, ?)
Hibernate: insert into member (id, name, member_team) values (null, ?, ?)
Hibernate: insert into member (id, name, member_team) values (null, ?, ?)
Hibernate: insert into member (id, name, member_team) values (null, ?, ?)
Hibernate: select member0_.id as id1_1_, member0_.name as name2_1_, member0_.member_team as member_t3_1_ from member member0_
```

<br/>
다대일 단방향에서는 크게 문제 없이 동작한다.