package com.example.eler.test.project.processing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class ReadFile {

    public String readFromJARFile(String filePath){
        try {
            System.out.println("File:");
            Path path = Paths.get(filePath);
            StringBuilder javaFile = new StringBuilder();
            Files.lines(path).forEach(line -> {
                System.out.println(line);
                javaFile.append(line).append("\n");
            });
            return javaFile.toString();
        } catch (Exception e) {
            log.error("C=ReadFile m=readFromJARFile Error reading the file");
            return "";
        }
    }
}
