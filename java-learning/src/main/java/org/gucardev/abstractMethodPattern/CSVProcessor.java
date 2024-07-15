package org.gucardev.abstractMethodPattern;

import java.io.InputStream;

public class CSVProcessor extends BaseProcessor {
    public CSVProcessor(String path) {
        super(path);
    }

    @Override
    public <T> T read(InputStream is) {
        System.out.println("CSVProcessor reading...");
        return null;
    }

    @Override
    public <T> T process(T payload) {
        System.out.println("CSVProcessor processing...");
        return null;
    }

    @Override
    public <T> T write(T payload) {
        System.out.println("CSVProcessor writing...");
        return null;
    }
}
