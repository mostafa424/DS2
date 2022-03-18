package RedBlack;

import AVL.AVL;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RedBlackDriver {
    public void test(){
        RedBlackNode<Integer>root = new RedBlackNode<Integer>(null,10,1);
        root.setLeft(new RedBlackNode<>(root,5,1));
        root.setRight(new RedBlackNode<>((RedBlackNode<Integer>) root,30,1));
        root.getLeft().setLeft(new RedBlackNode<Integer>((RedBlackNode<Integer>) root.getLeft(),1,1));
        root.getLeft().setRight(new RedBlackNode<Integer>((RedBlackNode<Integer>) root.getLeft(),7,1));
        root.getRight().setLeft(new RedBlackNode<>((RedBlackNode<Integer>) root.getRight(),25,0));
        root.getRight().setRight(new RedBlackNode<>((RedBlackNode<Integer>) root.getRight(),40,1));
        root.getRight().getLeft().setLeft(new RedBlackNode<Integer>((RedBlackNode<Integer>) root.getRight().getLeft(),20,1));
        root.getRight().getLeft().setRight(new RedBlackNode<Integer>((RedBlackNode<Integer>) root.getRight().getLeft(),28,1));
        print(root);
        root.delete(1);
        System.out.println("tree after deletion");
        print(root);
    }
    private void print(RedBlackNode<Integer> node){
        if(node == null){
            return;
        }
            print((RedBlackNode<Integer>) node.getLeft());
            /* then print the data of node */
            System.out.println("node value: "+node.getVal() + " "+"node color is: "+node.black);
            /* now recur on right child */
            print((RedBlackNode<Integer>) node.getRight());
        }
    public void run(){
            Scanner sc= new Scanner(System.in);
            System.out.print("Press 1 to test Red BlackTrees,2 to compare results, 3 to exit : ");
            int in = sc.nextInt();
            switch (in){
                case 1:
                    System.out.println("You are testing RedBlack tree code");
                    RedBlackTree<Integer> test= new RedBlackTree<>();
                    while(true){
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
                        switch(option){
                            case 1:
                                System.out.print("Enter search number: ");
                                int search = sc.nextInt();
                                System.out.println(test.search(search));
                                break;

                            case 2:
                                System.out.print("Enter number to be inserted: ");
                                int insertNumber = sc.nextInt();
                                test.insert(insertNumber);
                                System.out.println("Operation Successful");
                                break;
                            case 3:
                                System.out.print("Enter number to be deleted: ");
                                int deletedNumber = sc.nextInt();
                                test.delete(deletedNumber);
                                System.out.println("Operation Successful");
                                break;
                            case 4: System.out.println(test.getRoot());
                            case 5: System.out.println(test.isEmpty());
                            case 6: test.clear();
                            case 7:
                                System.out.print("Enter search number: ");
                                int contain = sc.nextInt();
                                System.out.println(test.search(contain));
                                break;
                        }
                        if(option == 8){System.out.println("Exiting....");break;}
                    }
                break;
                case 2:
                    System.out.println("You are now comparing RedBlack and AVL trees");
                    RedBlackTree<String> test2= new RedBlackTree<>();
                    AVL<String> test3= new AVL<String>();
                    long[][] AVLInsert = new long[11][3];
                    long[][] AVLDelete = new long[11][3];
                    long[][] redBlackInsert = new long[11][3];
                    long[][] redBlackDelete = new long[11][3];
                    for(int i=0;i<=10;i++){
                        String randomString = getAlphaNumericString(i+1);
                        int n=10;
                        while(n<=1000){
                            long before = System.currentTimeMillis();
                            test2.insert(randomString);
                            long after = System.currentTimeMillis();
                            redBlackInsert[i][(int)Math.log10(n)-1]=after-before;
                            n*=10;
                        }
                    }
                    for(int i=0;i<=10;i++){
                        String randomString = getAlphaNumericString(i+1);
                        int n=10;
                        while(n<=1000){
                            long before = System.currentTimeMillis();
                            test2.delete(randomString);
                            long after = System.currentTimeMillis();
                            redBlackDelete[i][(int)Math.log10(n)-1]=after-before;
                            n*=10;
                        }
                    }
                    for(int i=0;i<=10;i++){
                        String randomString = getAlphaNumericString(i+1);
                        int n=10;
                        while(n<=1000){
                            long before = System.currentTimeMillis();
                            test3.insert(randomString);
                            long after = System.currentTimeMillis();
                            AVLInsert[i][(int)Math.log10(n)-1]=after-before;
                            n*=10;
                        }
                    }
                    for(int i=0;i<=10;i++){
                        String randomString = getAlphaNumericString(i+1);
                        int n=10;
                        while(n<=1000){
                            long before = System.currentTimeMillis();
                            test3.delete(randomString);
                            long after = System.currentTimeMillis();
                            AVLDelete[i][(int)Math.log10(n)-1]=after-before;
                            n*=10;
                        }
                    }
                    System.out.println("Data For AVL Insertions:");
                    for(long[] row:AVLInsert){
                        System.out.println(Arrays.toString(row));
                    }
                    System.out.println("Data For AVL Deletions:");
                    for(long[] row:AVLDelete){
                        System.out.println(Arrays.toString(row));
                    }
                    System.out.println("Data For Red Black Insertions:");
                    for(long[] row:redBlackInsert){
                        System.out.println(Arrays.toString(row));
                    }
                    System.out.println("Data For Red Black Deletions:");
                    for(long[] row:redBlackDelete){
                        System.out.println(Arrays.toString(row));
                    }
                return;
                case 3:
                    System.out.println("Exiting....");return;
            }
        }
    private static String getAlphaNumericString(int n)
    {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, StandardCharsets.UTF_8);

        // Create a StringBuffer to store the result
        StringBuilder r = new StringBuilder();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {

                r.append(ch);
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }
    }


