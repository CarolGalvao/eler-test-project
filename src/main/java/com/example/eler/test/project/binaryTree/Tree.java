package com.example.eler.test.project.binaryTree;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Tree {

    public void setTree(String javaFile){
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
        System.out.println("Grafo: ");
        printTree(root);
        String path[] = new String[1000];
        System.out.println("Identificar caminhos: ");
        printPathsRecur(root, path, 0);
        System.out.println("Sequência de restrições: ");
    }

    public String cleanLine(String line) {
       if(line.contains("package") || line.contains("class") || line.isEmpty() || line.contains("}")) {
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
            if( !previous.isLeaf() || previous.getPrior() == null){
                previous.setLeft(newNode);
                newNode.setPrior(previous);
            }else {
                previous.getPrior().setRight(newNode);
                newNode.setPrior(previous.getPrior());
            }
        }
        return newNode;
    }

    //Mostra a arvore em ordem simétrica
    public void printTree (Node node){
        if( node != null ){
            System.out.println(node.getValue());
            printTree(node.getLeft());
            printTree(node.getRight());
        }
    }

    //Mostrar todos os caminhos da arvore
    void printPathsRecur(Node node, String path[], int pathLen)
    {
        if (node == null) {
            return;
        }
        path[pathLen] = node.getValue();
        pathLen++;

        if (node.getLeft() == null && node.getRight() == null){
            printArray(path, pathLen);
        } else {
            printPathsRecur(node.getLeft(), path, pathLen);
            printPathsRecur(node.getRight(), path, pathLen);
        }
    }

    void printArray(String ints[], int len)
    {
        int i;
        for (i = 0; i < len; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println("");
    }

}
