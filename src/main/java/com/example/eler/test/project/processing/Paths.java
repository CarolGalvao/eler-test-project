package com.example.eler.test.project.processing;

import com.example.eler.test.project.binaryTree.Node;

import java.util.ArrayList;

public class Paths {

    ArrayList<String[]> paths = new ArrayList<>();
    int pathNumber = 0;

    //Mostrar todos os caminhos da arvore
    public void printPaths(Node node, String path[], int pathLen) {
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
        System.out.print(pathNumber + " : ");
        pathNumber++;
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

    public ArrayList<String[]> getPaths(){
        return paths;
    }
}
