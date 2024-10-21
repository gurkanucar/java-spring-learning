package org.gucardev.utilities;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.gucardev.utilities.exception.ExceptionMessage;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.gucardev.utilities.exception.ExceptionUtil.buildException;


@RestController
@RequestMapping("/api")
public class Controller {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/entity-ex")
    ResponseEntity<?> notFoundUserException() {
        //new ObjectMapper().writeValueAsString(a)
        var a= SecurityContextHolder.getContext().getAuthentication();
        throw buildException(ExceptionMessage.NOT_FOUND_EXCEPTION, "<USER_ID>");
    }

    @PostMapping("/validation-ex")
    ResponseEntity<?> notFoundUserException(@Valid @RequestBody Address address) {
        return ResponseEntity.ok(address);
    }

    @Getter
    @Setter
    public static class Address {
        @NotBlank
        private String street;
        @NotBlank
        private String city;
        @Length(min = 5, max = 7)
        private String title;
    }

}
