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
                            addPurchaseValue(listOfDataPerPath,param,1,15);
                        }
                        if(param.contains("<")){
                            param = param.replace("valorCompra<", "");
                            addPurchaseValue(listOfDataPerPath,param,0,-15);
                        }
                        if(param.contains("==")){
                            param = param.replace("tipoCliente==", "");
                            int data = Integer.parseInt(param);
                            int[] dataList = {data};
                            Pair<String, int[]> paramData = new Pair<>("tipoCliente", dataList);
                            listOfDataPerPath.add(paramData);
                        }
                        if(param.contains("!=")){
                            param = param.replace("tipoCliente!=", "");
                            int data = Integer.parseInt(param);
                            int[] dataList = differentTypeOfCustomer(data);
                            Pair<String, int[]> paramData = new Pair<>("tipoCliente", dataList);
                            listOfDataPerPath.add(paramData);
                        }
                        if(param.equals("primeiraCompra")){
                            int[] dataList = {1};
                            addFirstBuy(dataList,listOfDataPerPath);
                        }
                        if(param.equals("!primeiraCompra")){
                            int[] dataList = {0};
                            addFirstBuy(dataList,listOfDataPerPath);
                        }
                    }
            );
            listOfData.put(paths.lastIndexOf(path), listOfDataPerPath);
        });

        printTestData(listOfData);
    }

    private void printTestData(Map<Integer,  ArrayList<Pair<String, int[]>>> listOfData) {
        System.out.println("Lista de dados");
        listOfData.forEach( (integer, arrayList) -> {
            System.out.println("Caminho: " + integer);
            arrayList.forEach(stringPair -> {
                System.out.print("Parametro: " + stringPair.getKey());
                System.out.println(" Dados: " + Arrays.toString(stringPair.getValue()));
            });
            System.out.println("");
        });
    }

    private void addFirstBuy (int[] dataList, ArrayList<Pair<String, int[]>> listOfDataPerPath) {
        Pair<String, int[]> paramData = new Pair<>("primeiraCompra", dataList);
        listOfDataPerPath.add(paramData);
    }

   private void addPurchaseValue(ArrayList<Pair<String, int[]>> listOfDataPerPath, String param, int add1, int add2) {
       int data = Integer.parseInt(param);
       int[] dataList = {data + add1, data + add2};
       Pair<String, int[]> paramData = new Pair<>("valorCompra", dataList);
       listOfDataPerPath.add(paramData);
   }

   private int[] differentTypeOfCustomer(int customer){
        ArrayList<Integer> customers = new ArrayList<>();
        customers.add(1);
        customers.add(2);
        customers.add(3);
        customers.remove(Integer.valueOf(customer));
        return customers.stream().mapToInt(i -> i).toArray();
   }
}
