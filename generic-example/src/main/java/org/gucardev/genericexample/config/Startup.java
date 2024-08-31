package org.gucardev.genericexample.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Startup implements CommandLineRunner {

    private final DataGeneratorService dataGeneratorService;

    public Startup(DataGeneratorService dataGeneratorService) {
        this.dataGeneratorService = dataGeneratorService;
    }

    @Override
    public void run(String... args) throws Exception {
        dataGeneratorService.generateData();

    }
}
