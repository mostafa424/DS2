package RedBlack;


/**
 * Red-Black Tree implementation.
 *
 * @param <T> Type of elements stored in Red-Black tree, must implement the Comparable interface.
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
     * Method to search Red-Black tree for a value.
     *
     * @param val value to search for
     * @return <code>Boolean</code> value, true if found, false else.
     */
    public boolean contains(T val) {
        return this.root.contains(val);
    }

    /**
     * Method to search Red-Black tree for node containing value.
     *
     * @param val value to search for
     * @return reference to <code>RedBlackNode</code> which contains value.
     */
    public RedBlackNode<T> search(T val) {
        return (RedBlackNode<T>) this.root.search(val);
    }

    /**
     * Method to insert value
     *
     * @param obj value to insert into Red-Black tree.
     * @return <code>boolean</code> value true if insert successful, false if already present.
     */
    public boolean insert(T obj) {
        boolean res = this.root.insert(obj);
        if (res) findRoot();
        return res;
    }

    /**
     * Method to delete value
     *
     * @param obj value of node to remove from Red-Black Tree.
     * @return <code>boolean</code> value true if delete successful, false if not found.
     */
    public boolean delete(T obj) {
        boolean res = this.root.delete(obj);
        if (res) findRoot();
        return res;
    }

    public boolean isEmpty() {
        return this.root.getVal() == null;
    }

    public void clear() {
        this.root = new RedBlackNode<T>(null);
    }
}
