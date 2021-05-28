package com.example.eler.test.project.binaryTree;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Data
public class Tree {

    private Node root;
    private Node previous;

    public void setTree(String javaFile){
        Arrays.stream(javaFile.split("\n")).forEach(line -> {
            adding(cleanLine(line));
        });
    }

    public String cleanLine(String line){
       if(line.contains("package") || line.contains("class") || line.isEmpty()) return null;
       if(line.contains("public")) {
           String[] arrays = line.split("[(|)]");
           return arrays[1];
       }
        if(line.equals("(")){
            String[] arrays = line.split("[^0-9A-Za-z]");
            return arrays[1];
        }else {
            return line;
        }
    }

    public void adding (String value){
        if(value == null)return;
        Node newNode = new Node();
        newNode.setValue(value);
        if (root == null){
            this.root = newNode;
            System.out.println("Raiz:");
            System.out.println(root);
        }else{

        }
        previous = newNode;
    }

    public Edge newEdge(Node current, Node newNode){
        Edge edge = new Edge();
        edge.setStart(current);
        edge.setEnd(newNode);
        return edge;
    }
}
