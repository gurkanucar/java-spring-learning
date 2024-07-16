package org.gucardev.analyticsmicro.remote;

import org.gucardev.analyticsmicro.security.config.InternalFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "project-micro", configuration = InternalFeignConfig.class)
public interface ProjectClient {

    @GetMapping("/project")
    String getProjects();

}
