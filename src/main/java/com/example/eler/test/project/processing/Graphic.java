package com.example.eler.test.project.processing;

import com.example.eler.test.project.binaryTree.Node;

public class Graphic {

    //Mostra a arvore em ordem simétrica
    public void printTree (Node node) {
        if( node != null ) {
            System.out.println(node.getValue());
            printTree(node.getLeft());
            printTree(node.getRight());
        }
    }
}
