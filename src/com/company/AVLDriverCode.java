package com.company;

import AVL.AVL;
import Dict.Dictionary;

import java.util.Arrays;
import java.util.Scanner;

public class AVLDriverCode {
    public void run(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Press 1 to test AVLTree,2 to test Dictionary, 3 to exit : ");
        int in = sc.nextInt();
        switch (in){
            case 1:
                System.out.println("You are testing AVL tree code");
                AVL<Integer> test= new AVL<Integer>();
                while(true){
                    System.out.println("Select the operation you want to perform by pressing the respective number: ");
                    System.out.println("1:search");
                    System.out.println("2:insert");
                    System.out.println("3:delete");
                    System.out.println("4:Print Height");
                    System.out.println("5:exit");
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
                        case 4: System.out.println(test.getHeight());
                    }
                    if(option == 5){System.out.println("Exiting....");break;}
                }
                break;
            case 2:
                System.out.println("You are testing Dictionary code");
                Dictionary test2= new Dictionary();
                System.out.println("Enter Absolute Path of Dictionary");
                String path=sc.next();
                test2.loadDict(path);
                while(true){
                    System.out.println("Select the operation you want to perform by pressing the respective number: ");
                    System.out.println("1:Find");
                    System.out.println("2:Insert word");
                    System.out.println("3:delete word");
                    System.out.println("4:Find by Batch");
                    System.out.println("5:delete by Batch");
                    System.out.println("6:size of Dictionary");
                    System.out.println("7:exit");
                    int option = sc.nextInt();
                    switch(option){
                        case 1:
                            System.out.print("Enter search word: ");
                            String search = sc.next();
                            System.out.println(test2.find(search));
                            break;

                        case 2:
                            System.out.print("Enter word to be inserted: ");
                            String Contact = sc.next();
                            test2.insert(Contact);
                            System.out.println("Operation Successful");
                            break;
                        case 3:
                            System.out.print("Enter word to be deleted: ");
                            String deletedContact = sc.next();
                            test2.delete(deletedContact);
                            System.out.println("Operation Successful");
                            break;
                        case 4:
                            System.out.println("Enter Absolute Path of Queries");
                            String q_path=sc.next();
                            System.out.println(Arrays.toString(test2.batchFind(q_path).toArray()));
                            break;
                        case 5:
                            System.out.println("Enter Absolute Path of Deletions");
                            String d_path=sc.next();
                            test2.batchDel(d_path);
                            break;
                        case 6: System.out.println(test2.getSize());
                    }
                    if(option == 7){System.out.println("Exiting....");break;}
                }
                break;
            case 3:
                System.out.print("Exiting...");
                return;
        }
    }
}
