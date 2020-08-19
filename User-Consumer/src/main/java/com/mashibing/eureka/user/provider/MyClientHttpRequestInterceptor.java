package com.mashibing.eureka.user.provider;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author gangquan.hu
 * @Package: MyClientHttpRequestInterceptor
 * @Description: TODO
 * @date 2020/8/19 10:29
 */
public class MyClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
      ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

    System.out.println("拦截到了请求..."+httpRequest);

    return clientHttpRequestExecution.execute(httpRequest,bytes);
  }
}
