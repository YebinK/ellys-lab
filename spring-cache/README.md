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

## `@Cacheable`의 동작 원리? DB는 어디에 있지?