package org.gucardev.utilities;

import org.gucardev.utilities.exception.ExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
