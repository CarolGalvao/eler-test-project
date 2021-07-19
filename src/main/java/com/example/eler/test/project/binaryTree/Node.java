package com.example.eler.test.project.binaryTree;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Node {

    private String value;
    private Node left;
    private Node right;
    private Node prior;
    private boolean leaf;

}
