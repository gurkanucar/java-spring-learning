package org.gucardev.projectmicro.common.security.remote;

import org.gucardev.projectmicro.common.security.dto.LoginRequest;
import org.gucardev.projectmicro.common.security.dto.TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-micro")
public interface AuthClient {
    @PostMapping("/auth/generate-service-token")
    ResponseEntity<TokenDto> generateServiceToken(@RequestBody LoginRequest loginRequest);
}
