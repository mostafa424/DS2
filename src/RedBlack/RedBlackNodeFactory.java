package RedBlack;

import BST.BSTNode;
import BST.BSTNodeFactory;

/**
 * Factory method that creates Red-Black Tree Nodes.
 * Extends BST Node Factory.
 *
 * @param <T> type of data to be stored in created nodes, must implement Comparable interface.
 */
public class RedBlackNodeFactory<T extends Comparable<T>> extends BSTNodeFactory<T> {

    /**
     * Method to initialize a Red-Black node with only a value.
     *
     * @param val value to be stored in node.
     * @return RedBlackNode initialized with value.
     */
    @Override
    public RedBlackNode<T> getNode(T val) {
        return new RedBlackNode<T>(val);
    }


    /**
     * Method to initialize a Red-Black node with both a reference to a parent node and a value.
     *
     * @param parent reference to parent node.
     * @param val value to be stored in node.
     * @return RedBlackNode initialized with parent node reference and value.
     */
    @Override
    public RedBlackNode<T> getNode(BSTNode<T> parent, T val) {
        return new RedBlackNode<T>(parent, val);
    }
}
