package com.example.eler.test.project.processing;

import com.example.eler.test.project.component.Pair;

import java.util.*;

public class TestData {

    //Mostrar os dados para cada caminho
    public void printData(ArrayList<String[]> paths) {
        ArrayList<String[]> sequenceOfData = paths;
        Map<Integer,  Map<String, int[]>> listOfData = new HashMap<>();

        sequenceOfData.forEach( path -> {
            Map<String, int[]> listOfDataPerPath = new HashMap<>();
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
                            equalTypeOfCustomer(listOfDataPerPath, dataList);
                        }
                        if(param.contains("!=")){
                            param = param.replace("tipoCliente!=", "");
                            int data = Integer.parseInt(param);
                            differentTypeOfCustomer(listOfDataPerPath, data);
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

    private void printTestData(Map<Integer,  Map<String, int[]>> listOfData) {
        System.out.println("Lista de dados");
        listOfData.forEach((integer, stringMap) -> {
            System.out.println("Caminho: " + integer);
            for (String key: stringMap.keySet()){
                    System.out.println(" Parametro: " + key+ " Dado: " + Arrays.toString(stringMap.get(key)));
                }
        });
    }

    private void addFirstBuy (int[] dataList, Map<String, int[]> listOfDataPerPath) {
        listOfDataPerPath.put("primeiraCompra", dataList);
    }

    private void addPurchaseValue(Map<String, int[]> listOfDataPerPath, String param, int add1, int add2) {
        int data = Integer.parseInt(param);
        int[] dataList = {data + add1, data + add2};
        if( listOfDataPerPath.isEmpty() || listOfDataPerPath.get("valorCompra") == null){
            listOfDataPerPath.put("valorCompra", dataList);
        } else {
            int[] previousData = listOfDataPerPath.get("valorCompra");
            Set<Integer> newData = new HashSet();
            for (int dataInList : dataList) {
                newData.add(dataInList);
            }
            for (int dataInPreviousList: previousData) {
                newData.add(dataInPreviousList);
            }

            listOfDataPerPath.replace("valorCompra", newData.stream().mapToInt(j -> j).toArray());
        }
    }
   private Map<String, int[]> equalTypeOfCustomer(Map<String, int[]> listOfDataPerPath, int[] dataList){
       if( listOfDataPerPath.isEmpty() || listOfDataPerPath.get("tipoCliente") == null){
           listOfDataPerPath.put("tipoCliente", dataList);
       } else {
           int[] previousData = listOfDataPerPath.get("tipoCliente");
           Set<Integer> newData = new HashSet();
           for (int dataInList : dataList) {
               newData.add(dataInList);
           }
           for (int dataInPreviousList: previousData) {
               newData.add(dataInPreviousList);
           }

           listOfDataPerPath.replace("tipoCliente", newData.stream().mapToInt(j -> j).toArray());
       }
       return listOfDataPerPath;
   }
   private Map<String, int[]> differentTypeOfCustomer(Map<String, int[]> listOfDataPerPath, int customer){
        ArrayList<Integer> customers = new ArrayList<>();
        customers.add(1);
        customers.add(2);
        customers.add(3);
        customers.remove(Integer.valueOf(customer));
       if( listOfDataPerPath.isEmpty() || listOfDataPerPath.get("tipoCliente") == null){
           listOfDataPerPath.put("tipoCliente", customers.stream().mapToInt(i -> i).toArray());
       } else {
           int[] previousData = listOfDataPerPath.get("tipoCliente");
           Set<Integer> newData = new HashSet();
           newData.addAll(customers);
           for (int dataInPreviousList: previousData) {
               newData.add(dataInPreviousList);
           }

           listOfDataPerPath.replace("tipoCliente", newData.stream().mapToInt(j -> j).toArray());
       }
       return listOfDataPerPath;
   }
}
