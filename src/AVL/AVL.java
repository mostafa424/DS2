package AVL;

/**
 * AVL Tree implementation.
 *
 * @param <T> Type of elements stored in AVL tree, must implement the Comparable interface.
 */
public class AVL<T extends Comparable<T>> {
    /**
     * Reference to root node.
     */
    private AVLNode<T> root;

    /**
     * Method to re-set root of tree.
     */
    private void findRoot() {
        AVLNode<T> temp = root;
        while(temp.getParent() != null) {
            temp = (AVLNode<T>) temp.getParent();
        }
        this.root = temp;
    }

    /**
     * Default constructor.
     */
    public AVL() {
        this.root = new AVLNode<T>(null);
    }

    /**
     * Root reference getter.
     *
     * @return reference to root.
     */
    public AVLNode<T> getRoot() {
        return root;
    }

    /**
     * Root reference setter.
     *
     * @param root Node object to overwrite root reference.
     */
    public void setRoot(AVLNode<T> root) {
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
     * Method to search AVL tree for a value.
     *
     * @param val value to search for
     * @return <code>Boolean</code> value, true if found, false else.
     */
    public boolean contains(T val) {
        return this.root.contains(val);
    }

    /**
     * Method to search AVL tree for node containing value.
     *
     * @param val value to search for
     * @return reference to <code>AVLNode</code> which contains value.
     */
    public AVLNode<T> search(T val) {
        return (AVLNode<T>) this.root.search(val);
    }

    /**
     * Method to insert value
     *
     * @param obj value to insert into AVL tree.
     */
    public boolean insert(T obj) {
        boolean res = this.root.insert(obj);
        if (res) findRoot();
        return res;
    }

    /**
     * Method to delete value
     *
     * @param obj value of node to remove from AVL Tree.
     */
    public boolean delete(T obj) {
        boolean res = this.root.delete(obj);
        if (res) findRoot();
        return res;
    }
}
