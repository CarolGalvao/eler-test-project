package com.example.eler.test.project.binaryTree;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Edge {

    private String conditional;
    private Node start;
    private Node end;

}
