package org.gucardev.mixed.feign_transaction.client;

import org.gucardev.mixed.feign_transaction.aspect.RollbackApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

//@CustomFeignAnnotation(rollbackUrl = "/myFeignClient/lookups?rollback=true")
@FeignClient(name = "myFeignClient", url = "http://localhost:8080")
public interface MyFeignClient {

    @RollbackApi(rollbackUrl = "/callApi/lookups?rollback=true")
    @PostMapping("/lookups")
    ResponseEntity<Object> callApi();
}
