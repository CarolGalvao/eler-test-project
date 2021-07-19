package com.example.eler.test.project.processing;

import java.util.*;
import java.util.stream.Collectors;

public class TestDataWithPath {

    //Mostrar os dados para cada caminho
    public Map<Integer, Map<String, int[]>> printDataWithPath(ArrayList<String[]> paths) {
        Map<Integer, Map<String, int[]>> listOfData = new HashMap<>();

        paths.forEach(path -> {
            Map<String, int[]> listOfDataPerPath = new HashMap<>();
            Arrays.stream(path).sequential().forEach(
                    param -> {
                        if (param == null) return;
                        if (param.contains("if")) {
                            param = param.replace("if (", "");
                            param = param.replace(")", "");
                        }
                        param = param.replaceAll(" ", "");
                        if (param.contains(">")) {
                            param = param.replace("valorCompra>", "");
                            addPurchaseValue(listOfDataPerPath, param, 1, 15);
                        }if (param.contains("<")) {
                            param = param.replace("valorCompra<", "");
                            addPurchaseValue(listOfDataPerPath, param, 0, -15);
                        }if (param.contains("==")) {
                            param = param.replace("tipoCliente==", "");
                            int data = Integer.parseInt(param);
                            int[] dataList = {data};
                            addDifferent(listOfDataPerPath, "tipoCliente", dataList);
                        }if (param.contains("!=")) {
                            param = param.replace("tipoCliente!=", "");
                            int data = Integer.parseInt(param);
                            differentTypeOfCustomer(listOfDataPerPath, data);
                        }if (param.equals("primeiraCompra")) {
                            int[] dataList = {1};
                            listOfDataPerPath.put("primeiraCompra", dataList);
                        }if (param.equals("!primeiraCompra")) {
                            int[] dataList = {0};
                            listOfDataPerPath.put("primeiraCompra", dataList);
                        } if (param.contains("return")) {
                            param = param.replace("return", "");
                            param = param.replace(";", "");
                            int[] data = {Integer.parseInt(param)};
                            listOfDataPerPath.put("return", data);
                        }
                    }
            );
            listOfData.put(paths.lastIndexOf(path), listOfDataPerPath);
        });
        printTestData(listOfData);
        return listOfData;
    }

    private void printTestData(Map<Integer, Map<String, int[]>> listOfData) {
        System.out.println("Lista de dados");
        listOfData.forEach((integer, stringMap) -> {
            System.out.println("Caminho: " + integer);
            for (String key : stringMap.keySet()) {
                if(!key.equals("return")){
                    System.out.println(" Parametro: " + key + " Dado: " + Arrays.toString(stringMap.get(key)));
                }
            }
        });
    }

    private void addPurchaseValue(Map<String, int[]> listOfDataPerPath, String param, int add1, int add2) {
        int data = Integer.parseInt(param);
        int[] dataList = {data + add1, data + add2};
        addDifferent(listOfDataPerPath, "valorCompra", dataList);
    }

    private void differentTypeOfCustomer(Map<String, int[]> listOfDataPerPath, int customer) {
        ArrayList<Integer> customers = new ArrayList<>(
                Arrays.stream(new int[]{0, 1, 2, 3}).boxed().collect(Collectors.toList()));
        customers.remove(Integer.valueOf(customer));
        addDifferent(listOfDataPerPath,"tipoCliente", customers.stream().mapToInt(j -> j).toArray());
    }

    private void addDifferent(Map<String, int[]> listOfDataPerPath, String param, int[] dataList) {
        if (listOfDataPerPath.isEmpty() || listOfDataPerPath.get(param) == null) {
            listOfDataPerPath.put(param, dataList);
        } else {
            int[] previousData = listOfDataPerPath.get(param);
            Set<Integer> newData = new HashSet();
            for (int dataInList : dataList) {
                newData.add(dataInList);
            }for (int dataInPreviousList : previousData) {
                newData.add(dataInPreviousList);
            }
            listOfDataPerPath.replace(param, newData.stream().mapToInt(j -> j).toArray());
        }
    }
}
