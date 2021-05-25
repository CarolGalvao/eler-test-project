package com.example.eler.test.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ReadFileTest {

    @InjectMocks
    ReadFile readFile = new ReadFile();

    private static String expected = "package codigos;\n" +
            "\n" +
            "public class Pedido {\n" +
            "\n" +
            "    public float calculaTaxaDesconto(boolean primeiraCompra, String tipoCliente, float valorCompra) {\n" +
            "        float taxa = 0;\n" +
            "        if (valorCompra > 500 || tipoCliente.equals(\"ouro\"))\n" +
            "            return 15;\n" +
            "        else\n" +
            "        if(tipoCliente.equals(\"prata\") || primeiraCompra || valorCompra > 400)\n" +
            "            taxa = 10;\n" +
            "        else\n" +
            "            //alterei o == para evitar lançamento de erro NullPointerException\n" +
            "        if(valorCompra > 200 || tipoCliente.equals(\"bronze\"))\n" +
            "            taxa = 5;\n" +
            "        return taxa;\n" +
            "    }\n" +
            "}\n";

    @Test
    void readFromJARFileOk() {
        String ans = readFile.readFromJARFile("src/main/resources/Pedido.java");
        System.out.println(ans);
        Assertions.assertEquals(expected, ans);
    }

    @Test
    void readFromJARFileError() {
        String ans = readFile.readFromJARFile("error");
        System.out.println(ans);
        Assertions.assertEquals("", ans);
    }
}
