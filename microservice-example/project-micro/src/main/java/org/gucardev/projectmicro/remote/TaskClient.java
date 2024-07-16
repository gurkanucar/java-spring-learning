package org.gucardev.projectmicro.remote;

import org.gucardev.projectmicro.security.config.InternalFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "task-micro", configuration = InternalFeignConfig.class)
public interface TaskClient {

    @GetMapping("/tasks")
    String getTasks();

}
