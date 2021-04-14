# Spring Cloud OpenFeign readTimeout 테스트

## httpbin을 사용한 Feign readTimeout 테스트

Feign은 타 서버 API를 호출해 데이터를 받아올 때 유용하게 쓰입니다. (특히나 배달의민족 같은 MSA 서비스에서는 더더욱!)
Spring Boot에서도 `spring-cloud-starter-openfeign` 의존성을 추가하면 Feign을 쉽게 사용할 수 있습니다. 

[httpbin](https://httpbin.org/) 는 간단한 HTTP 요청/응답 테스트를 위한 사이트이며,
 해당 사이트에서 제공하는 다양한 API를 호출해 테스트를 할 수 있습니다.   

우선, yml 파일을 이용해 Feign의 readTimeout 설정을 추가합니다.  

참고:
`connectTimeout`: 해당 서버와 커넥션을 맺기까지의 시간
`readTimeout`: 해당 서버와 커넥션을 맺은 이후 응답이 돌아오기까지의 시간


```yaml
feign:
  client:
    config:
      default:  //전역 설정 (모든 FeignClient에 적용된다.)
        connectTimeout: 1000
        readTimeout: 3000
```
이렇게 설정할 수 있다. 

우선, httpbin 서버를 찌르기 위한 FeignClient 하나를 만든다.

```java
@FeignClient(value = "httpBin", url = "http://httpbin.org")
public interface HttpBinClient {
    /**
     * Feign readTimeout 설정 테스트를 위한 API <br/>
     */
    @GetMapping("/delay/{seconds}")
    Object requestWithDelay(@PathVariable("seconds") int seconds);
}
```

https://httpbin.org/#/Dynamic_data/get_delay__delay_ 이 메소드를 





---

참고

[Spring Cloud OpenFeign Documentation](https://docs.spring.io/spring-cloud-openfeign/docs/2.2.7.RELEASE/reference/html/)

[우아한 Feign 적용기](https://woowabros.github.io/experience/2019/05/29/feign.html)