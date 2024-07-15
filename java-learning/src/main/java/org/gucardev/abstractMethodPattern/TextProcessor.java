package org.gucardev.abstractMethodPattern;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TextProcessor extends BaseProcessor<StringBuilder> {
    public TextProcessor(String path) {
        super(path);
    }

    @Override
    public StringBuilder read(InputStream is) throws Exception {
        System.out.println("TextProcessor reading...");
        Scanner scanner = new Scanner(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.next()).append(" ");
        }
        scanner.close();
        return sb;
    }

    @Override
    public StringBuilder process(StringBuilder payload) throws Exception {
        System.out.println("TextProcessor processing...");
        int length = payload.length();
        payload.insert(0, "Processed Text Length: " + length + " - ");
        return payload;
    }

    @Override
    public void write(StringBuilder payload) throws Exception {
        System.out.println("TextProcessor writing...");
        try (FileWriter writer = new FileWriter("./processed_" + path)) {
            writer.write(payload.toString());
        }
    }
}

