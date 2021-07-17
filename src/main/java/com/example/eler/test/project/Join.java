package com.example.eler.test.project;

import com.example.eler.test.project.binaryTree.Tree;
import com.example.eler.test.project.processing.Print;
import org.springframework.stereotype.Component;

@Component
public class Join {

    private final Print print = new Print();

    public void callAll(){
        ReadFile readFile = new ReadFile();
        Tree tree = new Tree();
        String fileContent = readFile.readFromJARFile("src/main/resources/Pedido.java");
        print.printProcessing(tree.setTree(fileContent));
    }
}
