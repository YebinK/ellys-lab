package com.ellyspace.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableFeignClients(basePackageClasses = HttpBinFeignClient.class)
public @interface EnableHttpBinFeignClient {
}
