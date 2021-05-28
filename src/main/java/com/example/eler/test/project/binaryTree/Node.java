package com.example.eler.test.project.binaryTree;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
public class Node {

    private String value;
    private Edge left;
    private Edge right;
    private boolean leaf;

}
