package com.keyin.binarytree;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import com.fasterxml.jackson.annotation.JsonInclude;

public class BinarySearchTree {


    private BinaryNode root;
    public BinaryNode getRoot() {
        return root;
    }

    public void setRoot(BinaryNode root) {
        this.root = root;
    }
    public BinarySearchTree() {
        this.root = null;
    }

    private BinaryNode insert(BinaryNode currentNode, int value) {
        if (currentNode == null) {
            return new BinaryNode(value);
        } else if (value <= currentNode.getValue()) {
            currentNode.setLeft(insert(currentNode.getLeft(), value));
        } else {
            currentNode.setRight(insert(currentNode.getRight(), value));
        }
        return currentNode;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    public void preOrder(BinaryNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + " ");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void inOrder(BinaryNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.print(node.getValue() + " ");
        inOrder(node.getRight());
    }

    public void postOrder(BinaryNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getValue() + " ");
    }

    public void levelOrder() {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode presentNode = queue.remove();
            System.out.print(presentNode.getValue() + " ");
            if (presentNode.getLeft() != null) {
                queue.add(presentNode.getLeft());
            }
            if (presentNode.getRight() != null) {
                queue.add(presentNode.getRight());
            }
        }
    }

    public BinaryNode search(BinaryNode node, int value) {
        if (node == null) {
            System.out.println("Value: " + value + " not found");
            return null;
        } else if (node.getValue() == value) {
            System.out.println("Value: " + value + " found");
            return node;
        } else if (value < node.getValue()) {
            return search(node.getLeft(), value);
        } else {
            return search(node.getRight(), value);
        }
    }

    public static BinaryNode minimumNode(BinaryNode root) {
        if (root.getLeft() == null) {
            return root;
        } else {
            return minimumNode(root.getLeft());
        }
    }

    public static BinaryNode maximumNode(BinaryNode root) {
        if (root.getRight() == null) {
            return root;
        } else {
            return maximumNode(root.getRight());
        }
    }

    public void deleteNode(int value) {
        root = deleteNode(root, value);
    }

    public BinaryNode deleteNode(BinaryNode root, int value) {
        if (root == null) {
            System.out.println("Value not found");
            return null;
        }
        if (value < root.getValue()) {
            root.setLeft(deleteNode(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(deleteNode(root.getRight(), value));
        } else {
            if (root.getLeft() != null && root.getRight() != null) {
                BinaryNode minNodeForRight = minimumNode(root.getRight());
                root.setValue(minNodeForRight.getValue());
                root.setRight(deleteNode(root.getRight(), minNodeForRight.getValue()));
            } else if (root.getLeft() != null) {
                root = root.getLeft();
            } else if (root.getRight() != null) {
                root = root.getRight();
            } else {
                root = null;
            }
        }
        return root;
    }

    public void deleteBST() {
        root = null;
        System.out.println("Tree deleted");
    }


    public String serialize() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(root);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static BinarySearchTree deserialize(String json) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            BinaryNode rootNode = objectMapper.readValue(json, BinaryNode.class);

            BinarySearchTree tree = new BinarySearchTree();
            tree.root = rootNode;
            return tree;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}