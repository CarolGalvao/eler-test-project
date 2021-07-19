package com.example.eler.test.project.processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class DataMatrix {

    public  ArrayList<ArrayList<Integer>> dataMatrix(Map<Integer, Map<String, int[]>> listOfDataPerPath) {
        ArrayList<ArrayList<Integer>> finalData = new ArrayList<>();
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
                int result = 0;

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
                }if(stringMap.get("return") != null){
                    result = stringMap.get("return")[0];
                }
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(value);
                arrayList.add(firstPurchase);
                arrayList.add(typeOfCustomer);

                AtomicBoolean equal = new AtomicBoolean(false);
                finalData.forEach(integers -> {
                    arrayList.add( integers.get(3));
                    if(integers.equals(arrayList)){
                        equal.set(true);
                    }
                    arrayList.remove(integers.get(3));
                });
                arrayList.add(result);
                if(!equal.get())finalData.add(arrayList);
            }
        });
        printMatrix(finalData);
        return finalData;
    }

    private void printMatrix(ArrayList<ArrayList<Integer>> data){
        System.out.println("");
        System.out.println("Casos de Teste");
        System.out.println("Valor   Primeira Compra   Cliente     Resultado");
        data.forEach(datas -> {
                for (int value: datas) {
                    System.out.print(value + "             ");
                }
                System.out.println("");
        });
    }
}
