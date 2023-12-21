package com.keyin.binarytree;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class BinaryNode {

    private int value;
    private int height;
    private BinaryNode left;
    private BinaryNode right;

    public BinaryNode(int value) {
        this.value = value;
        this.height = 1;
    }

    @JsonCreator
    public BinaryNode(@JsonProperty("value") int value,
                      @JsonProperty("left") BinaryNode left,
                      @JsonProperty("right") BinaryNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
        updateHeight();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
        updateHeight();
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
        updateHeight();
    }

    private void updateHeight() {
        this.height = 1 + Math.max(height(left), height(right));
    }

    private int height(BinaryNode node) {
        return node == null ? 0 : node.getHeight();
    }

    @JsonProperty("value")
    public int getJsonValue() {
        return value;
    }

    @JsonProperty("left")
    public BinaryNode getJsonLeft() {
        return left;
    }

    @JsonProperty("right")
    public BinaryNode getJsonRight() {
        return right;
    }
}
