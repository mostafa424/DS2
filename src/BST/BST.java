package BST;

import AVL.AVLNode;

/**
 * Binary search tree implementation.
 *
 * @param <T> Type of elements stored in binary search tree, must implement the Comparable interface.
 */
public class BST<T extends Comparable<T>> {
    /**
     * Reference to root node.
     */
    private BSTNode<T> root;
    /**
     * Height of tree rooted at root node.
     */
    private int height;

    /**
     * Default constructor.
     */
    public BST() {
        this.root = new BSTNode<T>(null);
    }

    /**
     * Root reference getter.
     *
     * @return reference to root.
     */
    public BSTNode<T> getRoot() {
        return root;
    }

    /**
     * Root reference setter.
     *
     * @param root Node object to overwrite root reference.
     */
    public void setRoot(BSTNode<T> root) {
        this.root = root;
    }

    /**
     * Height getter
     *
     * @return height of tree rooted at root.
     */
    public int getHeight() {
        return this.root.getHeight();
    }

    /**
     * Method to search BST tree for a value.
     *
     * @param val value to search for
     * @return <code>Boolean</code> value, true if found, false else.
     */
    public boolean contains(T val) {
        return this.root.contains(val);
    }

    /**
     * Method to search BST tree for node containing value.
     *
     * @param val value to search for
     * @return reference to <code>BSTNode</code> which contains value.
     */
    public BSTNode<T> search(T val) {
        return this.root.search(val);
    }

    /**
     * Method to insert value
     *
     * @param obj value to insert into BST.
     */
    public void insert(T obj) {
        this.root.insert(obj);
    }

    /**
     * Method to delete value
     *
     * @param obj value of node to remove from BST.
     */
    public void delete(T obj) {
        if(!this.root.contains(obj)){
            return;
        }
        this.root.delete(obj);
    }

}
