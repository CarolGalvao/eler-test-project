package com.example.eler.test.project.processing;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class DataMatrix {

    public  Set<ArrayList<Integer>> dataMatrix(Map<Integer, Map<String, int[]>> listOfDataPerPath) {
        Set<ArrayList<Integer>> finalData = new HashSet<>();
        listOfDataPerPath.forEach((integer, stringMap) -> {
            int biggerSizeMap = -1 ;
            for (String key : stringMap.keySet()) {
                int actualSize = Arrays.stream(stringMap.get(key)).toArray().length;
                if(actualSize > biggerSizeMap)biggerSizeMap = actualSize;
            }
            for (int i = 0; i < biggerSizeMap; i++){
                int firstPurchase = 0;
                int typeOfCustomer = 0;
                int value = 0;

                if(stringMap.get("tipoCliente") != null) {

                    List<Integer> typesOfCustomers = Arrays.stream(
                            stringMap.get("tipoCliente")).boxed().collect(Collectors.toList());
                    if(typesOfCustomers.size() - 1 > i) typeOfCustomer = typesOfCustomers.get(i);
                    else typeOfCustomer = typesOfCustomers.get(typesOfCustomers.size()-1);

                }if(stringMap.get("primeiraCompra") != null) {

                    List<Integer> firstPurchases = Arrays.stream(
                            stringMap.get("primeiraCompra")).boxed().collect(Collectors.toList());
                    if(firstPurchases.size() - 1 > i) firstPurchase = firstPurchases.get(i);
                    else firstPurchase = firstPurchases.get(firstPurchases.size()-1);

                }if(stringMap.get("valorCompra") != null){

                    List<Integer> values = Arrays.stream(
                            stringMap.get("valorCompra")).boxed().collect(Collectors.toList());
                    if(values.size() - 1 > i) value = values.get(i);
                    else value = values.get(values.size()-1);
                }
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(value);
                arrayList.add(firstPurchase);
                arrayList.add(typeOfCustomer);

                AtomicBoolean equal = new AtomicBoolean(false);
                finalData.forEach(integers -> {
                    if(integers.contains(arrayList)){
                        equal.set(true);
                    }
                });
                if(!equal.get())finalData.add(arrayList);
            }
        });
        printMatrix(finalData);
        return finalData;
    }

    private void printMatrix(Set<ArrayList<Integer>> data){
        System.out.println("Matriz de dados");
        System.out.println("Valor   Primeira Compra   Tipo de Cliente");
        data.forEach(datas -> {
                for (int value: datas) {
                    System.out.print(value + "             ");
                }
                System.out.println("");
        });
    }
}
