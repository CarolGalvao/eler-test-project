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
        printData();
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

    //Mostra sequencias de restrições
    public ArrayList<String[]> printRestriction() {
        ArrayList<String[]> sequenceOfConstraints = paths;
        sequenceOfConstraints.forEach(path -> {
            System.out.print(paths.lastIndexOf(path));
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
        return sequenceOfConstraints;
    }

    //Mostrar os dados para cada caminho
    public void printData() {
        ArrayList<String[]> sequenceOfData = paths;
        Map<Integer,  ArrayList<Pair<String, int[]>>> listOfData = new HashMap<>();

        sequenceOfData.forEach( path -> {
            ArrayList<Pair<String, int[]>> listOfDataPerPath = new ArrayList<>();
            Arrays.stream(path).sequential().forEach(
                    param -> {
                        if(param == null) return;
                        if(param.contains("if")){
                            param = param.replace("if (", "");
                            param = param.replace(")", "");
                        }
                        param = param.replaceAll(" ","");
                        if(param.contains(">")){
                            param = param.replace("valorCompra>", "");
                            int data = Integer.parseInt(param);
                            int[] dataList = {data+1, data+15};
                            Pair<String, int[]> paramData = new Pair<>("valorCompra", dataList);
                            listOfDataPerPath.add(paramData);
                        }
                        if(param.contains("<")){
                            param = param.replace("valorCompra<", "");
                            int data = Integer.parseInt(param);
                            int[] dataList = {data, data-15};
                            Pair<String, int[]> paramData = new Pair<>("valorCompra", dataList);
                            listOfDataPerPath.add(paramData);
                        }
                        if(param.contains("==")){
                            param = param.replace("tipoCliente==", "");
                            int data = Integer.parseInt(param);
                            int[] dataList = {data};
                            Pair<String, int[]> paramData = new Pair<>("tipoCliente", dataList);
                            listOfDataPerPath.add(paramData);
                        }
                        if(param.equals("primeiraCompra")){
                            int[] dataList = {1};
                            Pair<String, int[]> paramData = new Pair<>("primeiraCompra", dataList);
                            listOfDataPerPath.add(paramData);
                        }
                        if(param.equals("!primeiraCompra")){
                            int[] dataList = {0};
                            Pair<String, int[]> paramData = new Pair<>("primeiraCompra", dataList);
                            listOfDataPerPath.add(paramData);
                        }
                    }
            );
            listOfData.put(paths.lastIndexOf(path), listOfDataPerPath);
        });

        System.out.println("Lista de dados");
    //    Map<Integer,  ArrayList<Pair<String, int[]>>>
        listOfData.forEach( (integer, arrayList) -> {
            System.out.println("Caminho: " + integer);
            //System.out.println(arrayList);
            arrayList.forEach(stringPair -> {
                System.out.print("Parametro: " + stringPair.getKey());
                System.out.println(" Dados: " + Arrays.toString(stringPair.getValue()));
            });
            System.out.println("");
        });
    }
}
