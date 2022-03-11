package Dict;

import AVL.AVL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
    private AVL<String> tree;
    private int size;

    public Dictionary() {
        this.tree=new AVL<String>();
    }

    public AVL<String> getTree() {
        return tree;
    }

    public void setTree(AVL<String> tree) {
        this.tree = tree;
    }

    public int getSize() {
        return size;
    }

    private List<String> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        List<String> res = new ArrayList<>();
        Scanner fileScan = new Scanner(file);
        while(fileScan.hasNext()) {
            res.add(fileScan.nextLine());
        }
        return res;
    }

    public void loadDict(String path) {
        try {
            List<String> words = readFile(path);
            for (String word : words) {
                this.size++;
                this.tree.insert(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
        }
    }

    public int getHeight() {
        return this.tree.getHeight();
    }

    public void insert(String word) {
        if(!tree.search(word)) {
            this.tree.insert(word);
            this.size++;
        } else {
            System.out.println("Word already exists.");
        }
    }

    public boolean find(String word) {
        return this.tree.search(word);
    }

    public void delete(String word) {
        if(tree.search(word)) {
            this.tree.delete(word);
            this.size--;
        } else {
            System.out.println("No such word found.");
        }
    }

    public List<Boolean> batchFind(String path) {
        List<Boolean> res = new ArrayList<>();
        List<String> strs;
        try {
            strs = readFile(path);
            for(String str : strs) {
                res.add(Boolean.parseBoolean(str));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
        }
        return res;
    }

    public void batchDel(String path) {
        List<String> strs;
        try {
            strs = readFile(path);
            for(String str : strs) {
                this.delete(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
        }
    }
}
