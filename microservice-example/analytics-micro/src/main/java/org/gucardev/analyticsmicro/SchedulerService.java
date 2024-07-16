package org.gucardev.analyticsmicro;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.analyticsmicro.remote.ProjectClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {

    private final ProjectClient projectClient;

    @Scheduled(fixedRate = 5000)
    public void fetchProjects() {
        log.info("Fetching projects, {}", projectClient.getProjects());
    }
}
