package org.gucardev.utilities;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.gucardev.utilities.exception.ExceptionMessage;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.gucardev.utilities.exception.ExceptionUtil.buildException;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/entity-ex")
    ResponseEntity<?> notFoundUserException() {
        throw buildException(ExceptionMessage.NOT_FOUND_EXCEPTION, "<USER_ID>");
    }


    @GetMapping("/validation-ex")
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
