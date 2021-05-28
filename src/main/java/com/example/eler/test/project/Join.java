package com.example.eler.test.project;

import com.example.eler.test.project.binaryTree.Tree;
import org.springframework.stereotype.Service;

@Service
public class Join {

    public void callAll(){
        ReadFile readFile = new ReadFile();
        Tree tree = new Tree();
        String fileContent = readFile.readFromJARFile("src/main/resources/Pedido.java");
        tree.setTree(fileContent);
    }
}
