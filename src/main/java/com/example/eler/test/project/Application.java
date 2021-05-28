package com.example.eler.test.project;

import com.example.eler.test.project.binaryTree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	@Autowired
	private ReadFile readFile;

	@Autowired
	private Tree tree;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
