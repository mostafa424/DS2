package com.company;
import AVL.*;
import BST.BSTNode;

public class Main {

    public static <T extends Comparable<T>> void treeContents(BSTNode<T> node) {
        System.out.println(node.getVal() + "\t");
        if(node.getLeft() != null) {
            treeContents(node.getLeft());
        }
        if(node.getRight() != null) {
            treeContents(node.getRight());
        }
    }

    public static void main(String[] args) {
        AVL<Integer> test = new AVL<Integer>();
        test.insert(5);
        test.insert(3);
        test.insert(4);
        test.insert(11);
        test.insert(15);
        test.insert(20);
        test.insert(50);
        /**/
        System.out.println(test.search(5));
        System.out.println(test.search(11));
        System.out.println(test.getHeight());
    }
}
