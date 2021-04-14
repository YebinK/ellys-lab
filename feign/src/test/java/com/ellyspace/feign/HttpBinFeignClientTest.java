package com.ellyspace.feign;

import feign.RetryableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@EnableHttpBinFeignClient
@ExtendWith(SpringExtension.class)
@SpringBootTest
class HttpBinFeignClientTest {
    @Autowired
    private HttpBinFeignClient httpBinFeignClient;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("[성공 케이스] readTimeout보다 응답이 빠를 때")
    void requestWithDelay_success(int delaySeconds) {
        Object response = httpBinFeignClient.requestWithDelay(delaySeconds);

        assertThat(response).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    @DisplayName("[실패 케이스] readTimeout보다 응답이 느릴 때")
    void requestWithDelay_fail(int delaySeconds) {
        assertThatThrownBy(() -> httpBinFeignClient.requestWithDelay(delaySeconds))
                .isInstanceOf(RetryableException.class)
                .hasMessageContaining("Read timed out executing GET");
    }
}