package com.example.eler.test.project.binaryTree;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class TreeTest {

    private Tree tree = new Tree();

    @Test
    void readFromJARFileOk(){
        tree.setTree("package codigos;\n" +
                "\n" +
                "public class Pedido {\n" +
                "\n" +
                "    public float calculaTaxaDesconto (boolean primeiraCompra, String tipoCliente, float valorCompra) { \n" +
                "        if (valorCompra > 500) \n" +
                "            return 15; \n" +
                "        if (tipoCliente.equals(\"ouro\"))\n" +
                "            return 15; \n" +
                "        if (tipoCliente.equals(\"prata\"))\n" +
                "            return  10; \n" +
                "        if (primeiraCompra) \n" +
                "            return 10; \n" +
                "        if (valorCompra > 400) \n" +
                "            return 10;\n" +
                "        if (valorCompra > 200)\n" +
                "            return 5;\n" +
                "        if (tipoCliente.equals(\"bronze\")) \n" +
                "            return 5; \n" +
                "\n" +
                "        return 0;\n" +
                "    }\n" +
                "}");
    }


}