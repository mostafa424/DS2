package Dict;

import AVL.AVL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Dictionary implementation using AVL Tree.
 * Case in-sensitive.
 */
public class Dictionary {
    /**
     * AVL tree storing words.
     */
    private AVL<String> tree;
    /**
     * Number of items stored in dictionary.
     */
    private int size;

    /**
     * Default constructor.
     */
    public Dictionary() {
        setTree(new AVL<String>());
    }

    /**
     * Getter for AVL tree reference.
     *
     * @return reference to AVL tree where words are stored.
     */
    public AVL<String> getTree() {
        return tree;
    }

    /**
     * Setter for AVL tree reference.
     *
     * @param tree AVL tree to overwrite reference.
     */
    public void setTree(AVL<String> tree) {
        this.tree = tree;
    }

    /**
     * Getter for dictionary size.
     *
     * @return number of words stored in dictionary.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method to read a file and return a list containing strings found in file.
     *
     * @param path Absolute path of file to be read
     * @return <code>List</code> containing strings found in file.
     * @throws FileNotFoundException if path does not lead to file.
     */
    private List<String> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        List<String> res = new ArrayList<>();
        Scanner fileScan = new Scanner(file);
        while(fileScan.hasNext()) {
            res.add(fileScan.nextLine().strip());
        }
        return res;
    }

    /**
     * Method to read a dictionary file and insert words into tree.
     *
     * @param path Absolute path of dictionary file.
     */
    public void loadDict(String path) {
        try {
            List<String> words = readFile(path);
            for (String word : words) {
                this.size++;
                this.tree.insert(word.toLowerCase(Locale.ROOT));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
        }
    }

    /**
     * Tree height getter.
     *
     * @return height of tree.
     */
    public int getHeight() {
        return this.tree.getHeight();
    }

    /**
     * Method to insert words into dictionary tree.
     * Checks if word is duplicate first before inserting.
     *
     * @param word word to be inserted into dictionary.
     */
    public void insert(String word) {
        if(!tree.contains(word.toLowerCase(Locale.ROOT))) {
            this.tree.insert(word.toLowerCase(Locale.ROOT));
            this.size++;
        } else {
            System.out.println("Word already exists.");
        }
    }

    /**
     * Method to search dictionary for a word.
     *
     * @param word word to search for.
     * @return <code>boolean</code> value, true if found, false else.
     */
    public boolean find(String word) {
        return this.tree.contains(word.toLowerCase(Locale.ROOT));
    }

    /**
     * Method to delete a word stored in dictionary.
     * Checks if word exists before attempting to delete.
     *
     * @param word word to delete from dictionary.
     */
    public void delete(String word) {
        if(tree.contains(word.toLowerCase(Locale.ROOT))) {
            this.tree.delete(word.toLowerCase(Locale.ROOT));
            this.size--;
        } else {
            System.out.println("No such word found.");
        }
    }

    /**
     * Method to batch search for words, reads words from a file.
     *
     * @param path Absolute path of file to read words from.
     * @return <code>List</code> containing <code>boolean</code> values corresponding to whether each word was found or not.
     */
    public List<Boolean> batchFind(String path) {
        List<Boolean> res = new ArrayList<>();
        List<String> strs;
        try {
            strs = readFile(path);
            for(String str : strs) {
                res.add(this.find(str));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
        }
        return res;
    }

    /**
     * Method to batch delete words from the dictionary, reads words from a file.
     *
     * @param path Absolute path of file to read words from.
     */
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
