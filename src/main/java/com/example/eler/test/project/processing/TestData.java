package com.example.eler.test.project.processing;

import com.example.eler.test.project.component.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestData {

    //Mostrar os dados para cada caminho
    public void printData(ArrayList<String[]> paths) {
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
                        //TODO
                        //tipo cliente !=
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
