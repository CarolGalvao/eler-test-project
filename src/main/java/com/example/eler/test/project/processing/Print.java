package com.example.eler.test.project.processing;

import com.example.eler.test.project.binaryTree.Node;

public class Print {

    private final Graphic graphic = new Graphic();

    private final Paths pathsPrint = new Paths();

    private final Restrictions restrictions = new Restrictions();

    private final TestData testData = new TestData();

    public void printProcessing(Node root) {
        System.out.println(" ");
        System.out.println("Grafo: ");
        graphic.printTree(root);
        String path[] = new String[15];
        System.out.println(" ");
        System.out.println("Identificar caminhos: ");
        pathsPrint.printPaths(root, path, 0);
        System.out.println(" ");
        System.out.println("Sequência de restrições: ");
        restrictions.printRestriction(pathsPrint.getPaths());
        testData.printData(pathsPrint.getPaths());
    }
}
