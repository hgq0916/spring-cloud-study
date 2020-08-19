package com.mashibing.eureka.user.provider;

import java.util.concurrent.atomic.AtomicBoolean;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Service;

/**
 * 健康检查
 */
@Service
public class ProviderHealthService implements HealthIndicator {

  private AtomicBoolean status = new AtomicBoolean(true);

  public void setStatus(boolean flag){
    status.set(flag);
  }

  @Override
  public Health health() {
    if(status.get()){
     return Health.up().build();
    }else {
      return Health.down().build();
    }

  }

}
