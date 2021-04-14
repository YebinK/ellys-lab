# Spring Cloud OpenFeign - httpbin을 사용한 readTimeout 테스트

Feign은 타 서버 API를 호출해 데이터를 받아올 때 유용하게 쓰입니다. ???????? (특히나 배달의민족 같은 MSA 서비스에서는 더더욱!)
Spring Boot에서도 `spring-cloud-starter-openfeign` 의존성을 추가해 Feign을 쉽게 사용할 수 있습니다. 

[httpbin](https://httpbin.org/) 는 간단한 HTTP 요청/응답 테스트를 위한 사이트이며,
 해당 사이트에서 제공하는 다양한 API를 호출해 테스트를 할 수 있습니다.   



우선, 테스트를 위한 FeignClient를 하나 만들어봅니다.

```java
@FeignClient(value="httpBin", url="http://httpbin.org")
public interface HttpBinFeignClient {

    @GetMapping("/delay/{seconds}")
    Object requestWithDelay(@PathVariable("seconds") int seconds);
}
```

간단하게 httpbin에서 제공하는 [delay API](https://httpbin.org/#/Dynamic_data/get_delay__delay_) 를 호출해봅시다.

`/delay/5` 이렇게 5를 넣어 보내면 3초 후 응답이 내려집니다.

FeignClient를 만들었으니 이제 해당 FeignClient를 Enable시켜줄 어노테이션을 하나 만들어줍니다.

```java

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableFeignClients(basePackageClasses = HttpBinFeignClient.class)
public @interface EnableHttpBinFeignClient {
}

```


마지막으로, yml 파일에 Feign의 readTimeout 설정을 추가합니다.  

참고:
`connectTimeout`: 해당 서버와 커넥션을 맺기까지의 시간
`readTimeout`: 해당 서버와 커넥션을 맺은 이후 응답이 돌아오기까지의 시간


```yaml
feign:
  client:
    config:
      default:  #전역 설정 (모든 FeignClient에 적용된다.)
        connectTimeout: 1000
        readTimeout: 3000
```
readTimeout을 3초로 설정했습니다. 즉, 커넥션을 맺은 이후 응답까지 3초 이상이 걸리면 exception이 발생하게 됩니다.

참고: 
만약 여기서 클라이언트마다 다른 설정을 주고 싶다면, `default:` 대신 `클라이언트이름:` 로 주면 됩니다.
 
이제 테스트를 위한 설정은 준비를 마쳤으니, 테스트를 짜봅시다.








---

참고

[Spring Cloud OpenFeign Documentation](https://docs.spring.io/spring-cloud-openfeign/docs/2.2.7.RELEASE/reference/html/)

[우아한 Feign 적용기](https://woowabros.github.io/experience/2019/05/29/feign.html)