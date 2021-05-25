package com.example.eler.test.project.binaryTree;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Tree {

    private Node root;

    public void adding (String value){
        Node newNode = new Node();
        newNode.setValue(value);
        if (root == null){
            this.root = newNode;
        }else{
            Node current = this.root;
            while(true){
                if (newNode.getValue().compareTo(current.getValue()) == -1){
                    if (current.getLeft() != null){
                        current = current.getLeft();
                    }else{
                        current.setLeft(newNode);
                        break;
                    }
                }else{ //se for maior ou igual
                    if (current.getRight() != null){
                        current = current.getRight();
                    }else{
                        current.setRight(newNode);
                        break;
                    }
                }
            }
        }
    }
}
