package com.example.eler.test.project.processing;

import java.util.ArrayList;

public class WriteTestClass {

    public void writingTestClass(ArrayList<String> classInformation, ArrayList<ArrayList<Integer>> finalData) {
        standardImport();
        initClass(classInformation.get(0));
        finalData.forEach(data -> {
            writeTest(data,classInformation);
        });
        System.out.println("}");

    }

    private void standardImport() {
        System.out.println("");
        System.out.println("import org.junit.Before;\n" +
                "import org.junit.BeforeClass;\n" +
                "import org.junit.Test;\n" +
                "\n" +
                "import static org.junit.Assert.assertEquals;");
    }

    private void initClass(String className) {
        System.out.println("public class "+  className +"Test {");
        System.out.println("");
        System.out.println("    private " + className + " "+ className.toLowerCase() + ";");
        System.out.println("\n" +
                "    @BeforeClass\n" +
                "    public static void setupClass(){\n" +
                "        System.out.println(\"Antes de todos os testes\");\n" +
                "    }\n" +
                "\n" +
                "    @Before\n" +
                "    public void setup(){\n" +
                "        System.out.println(\"Antes de cada teste\");");
        System.out.println("        " + className.toLowerCase() + " = new " + className + "();");
        System.out.println("    }");
        System.out.println("");
    }

    private void writeTest(ArrayList<Integer> data, ArrayList<String> classInformation) {
        classInformation.set(1, classInformation.get(1).replaceAll(" ",""));
        System.out.println("    @Test");
        System.out.println("    public void testParam" + "V" + data.get(0) +"P" + data.get(1) + "C"+ data.get(2)
                            + "Result" + data.get(3) + "() {");
        System.out.println("        assertEquals(" + data.get(3) + "," +
                classInformation.get(0).toLowerCase() + "." + classInformation.get(1) +
                "(" + firstPurchase(data.get(1)) + "," + data.get(2) + "," + data.get(0) + "), 0);");
        System.out.println("    }");
        System.out.println("");
    }

    private boolean firstPurchase(Integer purchase) {
        return purchase != 0;
    }
}
