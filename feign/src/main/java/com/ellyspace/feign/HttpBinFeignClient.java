package com.ellyspace.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="httpBin", url="http://httpbin.org")
public interface HttpBinFeignClient {

    @GetMapping("/delay/{seconds}")
    Object requestWithDelay(@PathVariable("seconds") int seconds);
}
