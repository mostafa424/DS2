package RedBlack;

import AVL.AVL;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class RedBlackDriver {
    public void test(){
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
        redBlackTree.insert(10);
        redBlackTree.insert(5);
        redBlackTree.insert(1);
        redBlackTree.insert(7);
        redBlackTree.insert(30);
        redBlackTree.insert(25);
        redBlackTree.insert(40);
        redBlackTree.insert(28);
        redBlackTree.delete(7);
        redBlackTree.delete(30);
        redBlackTree.delete(10);
        redBlackTree.delete(5);
        redBlackTree.delete(1);
        redBlackTree.delete(25);
        redBlackTree.delete(28);
        redBlackTree.delete(40);
        traverse(redBlackTree.getRoot());
    }

    private void traverse(RedBlackNode<Integer> node){
        if (node == null) {
            return;
        }

        traverse((RedBlackNode<Integer>) node.getLeft());
        System.out.println("node value: "+node.getVal() + " node color: "+node.getBlack());
        traverse((RedBlackNode<Integer>) node.getRight());
    }
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press 1 to test Red BlackTrees,2 to compare results, 3 to exit : ");
        int in = sc.nextInt();
        switch (in) {
            case 1:
                System.out.println("You are testing RedBlack tree code");
                RedBlackTree<Integer> test = new RedBlackTree<>();
                while (true) {
                    System.out.println("Select the operation you want to perform by pressing the respective number: ");
                    System.out.println("1:search");
                    System.out.println("2:insert");
                    System.out.println("3:delete");
                    System.out.println("4:getRoot");
                    System.out.println("5:isEmpty");
                    System.out.println("6:clear");
                    System.out.println("7:contains");
                    System.out.println("8:exit");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            System.out.print("Enter search number: ");
                            int search = sc.nextInt();
                            System.out.println(test.search(search));
                            break;

                        case 2:
                            System.out.print("Enter number to be inserted: ");
                            int insertNumber = sc.nextInt();
                            if(test.insert(insertNumber)) {
                                System.out.println("Operation Successful");
                            } else {
                                System.out.println("Operation failed, already present in tree.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter number to be deleted: ");
                            int deletedNumber = sc.nextInt();
                            if(test.delete(deletedNumber)){
                                System.out.println("Operation Successful");
                            } else {
                                System.out.println("Operation failed, no such element in tree.");
                            }
                            break;
                        case 4:
                            System.out.println(test.getRoot());
                        case 5:
                            System.out.println(test.isEmpty());
                        case 6:
                            test.clear();
                        case 7:
                            System.out.print("Enter search number: ");
                            int contain = sc.nextInt();
                            System.out.println(test.contains(contain));
                            break;
                    }
                    if (option == 8) {
                        System.out.println("Exiting....");
                        break;
                    }
                }
                break;
            case 2:
                System.out.println("You are now comparing RedBlack and AVL trees");
                RedBlackTree<String> test2 = new RedBlackTree<String>();
                AVL<String> test3 = new AVL<String>();
                int[] cases = {5,10,25,50,100,250,500};
                List<Long> avlDel = new ArrayList<Long>();
                List<Long> avlIns = new ArrayList<Long>();
                List<Long> rblDel = new ArrayList<Long>();
                List<Long> rblIns = new ArrayList<Long>();
                for(int i = 0; i < cases.length; i++) {
                    String[] strings = new String[cases[i]];
                    for(int j = 0; j < strings.length; j++) {
                        strings[j] = generateRandomString(20);
                    }
                    long timeBefore = System.nanoTime();
                    for(int j = 0; j < strings.length; j++) {
                        test3.insert(strings[j]);
                    }
                    long timeAfter = System.nanoTime();
                    avlIns.add(timeAfter-timeBefore);
                    timeBefore = System.nanoTime();
                    for(int j = 0; j < strings.length; j++) {
                        test3.delete(strings[j]);
                    }
                    timeAfter = System.nanoTime();
                    avlDel.add(timeAfter-timeBefore);
                    timeBefore = System.nanoTime();
                    for(int j = 0; j < strings.length; j++) {
                        test2.insert(strings[j]);
                    }
                    timeAfter = System.nanoTime();
                    rblIns.add(timeAfter-timeBefore);
                    timeBefore = System.nanoTime();
                    for(int j = 0; j < strings.length; j++) {
                        test2.delete(strings[j]);
                    }
                    timeAfter = System.nanoTime();
                    rblDel.add(timeAfter-timeBefore);
                }

                System.out.println("Data For AVL Insertions:");
                System.out.println(avlIns.toString());
                System.out.println("Data For AVL Deletions:");
                System.out.println(avlDel.toString());
                System.out.println("Data For Red Black Insertions:");
                System.out.println(rblIns.toString());
                System.out.println("Data For Red Black Deletions:");
                System.out.println(rblDel.toString());
                return;
            case 3:
                System.out.println("Exiting....");
                return;
        }
    }

    private static String generateRandomString(int n) {
        Random random = new Random();
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            char ch = (char) (random.nextInt(57) + 65);
            str.append(ch);
        }
        return str.toString();
    }

}


