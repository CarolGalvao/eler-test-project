package com.example.eler.test.project.binaryTree;

import com.example.eler.test.project.component.Pair;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class Tree {

    ArrayList<String[]> paths = new ArrayList<>();
    int pathNumber = 0;

    public Node setTree(String javaFile){
        String[] arrays = javaFile.split("\n");
        Node previous = null;
        Node root = null;
        for (String line: arrays) {
            String value = cleanLine(line);
            if(value != null){
                Node node = adding(value, previous);
                previous = node;
                if(root == null){
                    root = node;
                }
            }
        }

        return root;
    }

    public String cleanLine(String line) {
       if(line.contains("package") || line.contains("class") || line.isEmpty() || line.contains("}") || line.contains("//")) {
           return null;
       }
       if(line.contains("public")) {
           String[] arrays = line.split("[(|)]");
           return arrays[1];
       }
        if(line.equals("(")) {
            String[] arrays = line.split("[^0-9A-Za-z]");
            return arrays[1];
        }else {
            return line;
        }
    }

    public Node adding (String value, Node previous) {
        Node newNode = new Node();
        newNode.setValue(value);
        newNode.setLeaf(newNode.getValue().contains("return"));
        if (previous != null) {
            if( !previous.isLeaf() || previous.getPrior() == null) {
                previous.setLeft(newNode);
                newNode.setPrior(previous);
            }else {
                previous.getPrior().setRight(newNode);
                newNode.setPrior(previous.getPrior());
            }
        }
        return newNode;
    }
}
