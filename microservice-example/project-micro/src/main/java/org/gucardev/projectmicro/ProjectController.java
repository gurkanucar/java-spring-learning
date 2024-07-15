package org.gucardev.projectmicro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @GetMapping
    public ResponseEntity<?> getProject() {
        return ResponseEntity.ok("projectsss");
    }
}
