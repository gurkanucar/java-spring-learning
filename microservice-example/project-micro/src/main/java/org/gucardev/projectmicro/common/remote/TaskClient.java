package org.gucardev.projectmicro.common.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "task-micro", configuration = FeignConfig.class)
public interface TaskClient {

    @GetMapping("/tasks")
    String getTasks();

}
