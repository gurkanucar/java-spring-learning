package org.gucardev.analyticsmicro.remote;

import org.gucardev.analyticsmicro.common.remote.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "project-micro", configuration = FeignConfig.class)
public interface ProjectClient {

    @GetMapping("/project")
    String getProjects();

}
