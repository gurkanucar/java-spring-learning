package org.gucardev.projectmicro;

import lombok.RequiredArgsConstructor;
import org.gucardev.projectmicro.common.remote.TaskClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final TaskClient taskClient;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping
    public ResponseEntity<?> getProject() {
        return ResponseEntity.ok(taskClient.getTasks());
    }
}
