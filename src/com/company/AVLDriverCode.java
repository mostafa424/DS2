package com.company;

import AVL.AVL;
import Dict.Dictionary;

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
                    System.out.println("4:exit");
                    int option = sc.nextInt();
                    switch(option){
                        case 1:
                            System.out.print("Enter search number: ");
                            int search = sc.nextInt();
                            System.out.print(test.search(search));
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
                    }
                    if(option == 4){break;}
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
                    System.out.println("2:Insert contact");
                    System.out.println("3:delete Contact");
                    System.out.println("4:exit");
                    int option = sc.nextInt();
                    switch(option){
                        case 1:
                            System.out.print("Enter search Contact: ");
                            String search = sc.next();
                            System.out.print(test2.find(search));
                            break;

                        case 2:
                            System.out.print("Enter number to be inserted: ");
                            String Contact = sc.next();
                            test2.insert(Contact);
                            System.out.println("Operation Successful");
                            break;
                        case 3:
                            System.out.print("Enter number to be deleted: ");
                            String deletedContact = sc.next();
                            test2.delete(deletedContact);
                            System.out.println("Operation Successful");
                            break;
                    }
                    if(option == 4){break;}
                }
                break;
            case 3:
                System.out.print("Exiting...");
                return;
        }
    }
}
