package com.example.eler.test.project.processing;

import com.example.eler.test.project.binaryTree.Node;

import java.util.ArrayList;

public class Print {

    private final Graphic graphic = new Graphic();

    private final Paths pathsPrint = new Paths();

    private final Restrictions restrictions = new Restrictions();

    private final TestDataWithPath testData = new TestDataWithPath();

    private final DataMatrix dataMatrix = new DataMatrix();

    private final WriteTestClass writeTestClass = new WriteTestClass();

    public void printProcessing(Node root, String fileContent) {
        System.out.println(" ");
        System.out.println("Grafo: ");
        graphic.printTree(root);
        String[] path = new String[15];
        System.out.println(" ");
        System.out.println("Identificar caminhos: ");
        pathsPrint.printPaths(root, path, 0);
        System.out.println(" ");
        System.out.println("Sequência de restrições: ");
        restrictions.printRestriction(pathsPrint.getPaths());
        System.out.println("");
        ArrayList<ArrayList<Integer>> finalData = dataMatrix.dataMatrix(testData.printDataWithPath(pathsPrint.getPaths()));
        System.out.println("");
        System.out.println("Classe de Teste: ");
        writeTestClass.writingTestClass(printTestClass(fileContent), finalData);

    }

    public ArrayList<String> printTestClass(String fileContent){
        String[] arrays = fileContent.split("\n");
        ArrayList<String> classInformation = new ArrayList<>();
        for (String line: arrays) {
            String value = getInformation(line);
            if(value != null){
                classInformation.add(value);
            }
        }
        return classInformation;
    }

    private String getInformation(String line){
        if(line.contains("class")){
            line = line.replace("public class ", "");
            line = line.replace(" {", "");
        }else if(line.contains("public float ")){
            line = line.replace("public float ", "");
            line = line.replace(" (boolean primeiraCompra, int tipoCliente, float valorCompra) {", "");
        }else {
            line = null;
        }
        return line;
    }
}
