package com.company;
import BST.BST;
import BST.BSTNode;

public class Main {
    public static void traverse(BSTNode test){
        if(test==null){return;}
        traverse(test.getLeft());
        System.out.print(test.getVal()+" ");
        traverse(test.getRight());
    }
    public static void main(String[] args) {
	BST test=new BST();
    test.insert(test.getRoot(),10);
    test.insert(test.getRoot(),4);
    test.insert(test.getRoot(),6);
    test.insert(test.getRoot(),3);
    test.insert(test.getRoot(),8);
    test.insert(test.getRoot(),12);
    test.insert(test.getRoot(),11);
    test.insert(test.getRoot(),15);
    test.insert(test.getRoot(),13);
    traverse(test.getRoot());
    test.delete(test.getRoot(), 6,'l');
    test.delete(test.getRoot(), 7,'r');
    test.delete(test.getRoot(), 12,'r');
    test.delete(test.getRoot(), 8,'r');
    System.out.println();
    traverse(test.getRoot());
    System.out.println();
    System.out.println(test.search(test.getRoot(),10));
    System.out.println(test.search(test.getRoot(),6));
    System.out.println(test.search(test.getRoot(),8));
    System.out.println(test.get_height());
    }
}
