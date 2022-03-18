package RedBlack;

/**
 * AVL Tree implementation.
 *
 * @param <T> Type of elements stored in AVL tree, must implement the Comparable interface.
 */
public class RedBlackTree<T extends Comparable<T>> {
    /**
     * Reference to root node.
     */
    private RedBlackNode<T> root;

    /**
     * Method to re-set root of tree.
     */
    private void findRoot() {
        RedBlackNode<T> temp = root;
        while(temp.getParent() != null) {
            temp = (RedBlackNode<T>) temp.getParent();
        }
        this.root = temp;
    }

    /**
     * Default constructor.
     */
    public RedBlackTree() {
        this.root = new RedBlackNode<T>(null);
    }

    /**
     * Root reference getter.
     *
     * @return reference to root.
     */
    public RedBlackNode<T> getRoot() {
        return root;
    }
    /**
     * Root reference setter.
     *
     * @param root Node object to overwrite root reference.
     */
    public void setRoot(RedBlackNode<T> root) {
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
    public boolean search(T val) {
        return this.root.search(val);
    }

    /**
     * Method to insert value
     *
     * @param obj value to insert into AVL tree.
     */
    public void insert(T obj) {
        this.root.insert(obj);
        findRoot();
    }

    /**
     * Method to delete value
     *
     * @param obj value of node to remove from AVL Tree.
     */
    public void delete(T obj) {
        if(!this.root.search(obj)){
            return;
        }
        this.root.delete(obj);
        findRoot();
    }

    public boolean isEmpty() {
        return root==null;
    }

    public void clear() {
        root=null;
    }
}
