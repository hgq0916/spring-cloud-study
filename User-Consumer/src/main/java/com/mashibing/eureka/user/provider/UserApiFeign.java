package com.mashibing.eureka.user.provider;

import com.mashibing.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="User-Provider")
public interface UserApiFeign extends UserApi {

}
