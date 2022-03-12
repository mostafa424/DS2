package BST;

/**
 * Factory method that creates BST Nodes.
 *
 * @param <T> type of data to be stored in created nodes, must implement Comparable interface.
 */
public class BSTNodeFactory<T extends Comparable<T>> {
    /**
     * Method to initialize a BST node with only a value.
     *
     * @param val value to be stored in node.
     * @return BSTNode initialized with value.
     */
    public BSTNode<T> getNode(T val) {
        return new BSTNode<T>(val);
    }

    /**
     * Method to initialize a BST node with both a reference to a parent node and a value.
     *
     * @param parent reference to parent node.
     * @param val value to be stored in node.
     * @return BSTNode initialized with parent node reference and value.
     */
    public BSTNode<T> getNode(BSTNode<T> parent, T val) {
        return new BSTNode<T>(parent, val);
    }
}
