package com.example.eler.test.project.processing;

import java.util.ArrayList;
import java.util.Arrays;

public class Restrictions {

    //Mostra sequencias de restrições
    public ArrayList<String[]> printRestriction(ArrayList<String[]> paths) {
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
}
