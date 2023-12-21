package com.keyin.mainservice;

import com.keyin.binarytree.BinarySearchTree;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/treeify")
@CrossOrigin(origins = "http://localhost:3000")
public class TreeController {

    private final List<BinarySearchTree> trees = new ArrayList<>();

    @PostMapping("/process-numbers")
    public String processInput(@RequestParam("numbers") String numbersEntered) {
        BinarySearchTree bsTree = new BinarySearchTree();

        String[] numbersList = numbersEntered.split("\\s+");
        for (String number : numbersList) {
            int num = Integer.parseInt(number.trim());
            bsTree.insert(num);
        }

        String jsonFormatTree = bsTree.serialize();
        trees.add(bsTree);

        return jsonFormatTree;
    }

    @GetMapping("/previous-trees")
    public List<String> previousTrees() {
        List<String> jsonTrees = new ArrayList<>();
        for (BinarySearchTree tree : trees) {
            jsonTrees.add(tree.serialize());
        }
        return jsonTrees;
    }
}

