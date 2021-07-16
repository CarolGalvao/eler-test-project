package com.example.eler.test.project.binaryTree;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Data
public class Tree {

    ArrayList<String[]> paths = new ArrayList<>();

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

        System.out.println(" ");
        System.out.println("Grafo: ");
        printTree(root);
        String path[] = new String[15];
        System.out.println(" ");
        System.out.println("Identificar caminhos: ");
        printPaths(root, path, 0);
        System.out.println(" ");
        System.out.println("Sequência de restrições: ");
        printRestriction();
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

    //Mostra a arvore em ordem simétrica
    public void printTree (Node node) {
        if( node != null ) {
            System.out.println(node.getValue());
            printTree(node.getLeft());
            printTree(node.getRight());
        }
    }

    //Mostrar todos os caminhos da arvore
    void printPaths(Node node, String path[], int pathLen) {
        if (node == null) {
            return;
        }
        String pathClone[] = path.clone();
        pathClone[pathLen] = node.getValue();
        pathLen++;

        if (node.isLeaf()) {
            printArray(pathClone, pathLen, node);
        } else {
            printPaths(node.getLeft(), pathClone, pathLen);
            printPaths(node.getRight(), pathClone, pathLen);
        }
    }

    void printArray(String ints[], int len, Node node ) {
        for (int i = 0; i < len; i++) {
            if(i != len - 2 || node.getPrior().getRight().getValue().equals(ints[i + 1]) ){
                ints[i] = ints[i].replace(">", "<");
                ints[i] = ints[i].replace("==", "!=");
                ints[i] = ints[i].replace("(primeiraCompra)", "(!primeiraCompra)");
            }
            System.out.print(ints[i]);
        }
        paths.add(ints);
        System.out.println("");
    }

    //Mostra sequencias de restrições
    public void printRestriction () {
        paths.stream().forEach(path ->{
            Arrays.stream(path).sequential().forEach(
                    param -> {
                        if(param == null) return;
                        if(param.contains("if")){
                            param = param.replace("if (", "");
                            param = param.replace(")", "");
                            System.out.print(param);

                        }
                    }
            );
            System.out.println();
        });
    }
}
