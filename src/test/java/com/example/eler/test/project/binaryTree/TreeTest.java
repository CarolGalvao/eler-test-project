package com.example.eler.test.project.binaryTree;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class TreeTest {

    private Tree tree = new Tree();

    @Test
    void readFromJARFileOk(){
        System.out.println(tree.cleanLine("public float calculaTaxaDesconto (boolean primeiraCompra, String tipoCliente, float valorCompra) {"));
    }


}