package org.gucardev.abstractMethodPattern;

import java.io.InputStream;

public class CSVProcessor extends BaseProcessor<Void> {
    public CSVProcessor(String path) {
        super(path);
    }

    @Override
    public Void read(InputStream is) throws Exception {
        System.out.println("CSVProcessor reading...");
        // Implement CSV reading logic
        return null;
    }

    @Override
    public Void process(Void payload) throws Exception {
        System.out.println("CSVProcessor processing...");
        // Implement CSV processing logic
        return null;
    }

    @Override
    public void write(Void payload) throws Exception {
        System.out.println("CSVProcessor writing...");
        // Implement CSV writing logic
    }
}