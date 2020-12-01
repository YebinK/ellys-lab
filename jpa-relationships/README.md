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

### OneToMany 단방향에서 CascadeType.ALL로 걸 때
`Team` - `Member`