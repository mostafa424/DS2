package AVL;

import BST.BSTNode;
import BST.BSTNodeFactory;

public class AVLNodeFactory<T extends Comparable<T>> extends BSTNodeFactory<T> {
    @Override
    public AVLNode<T> getNode(T val) {
        return new AVLNode<T>(val);
    }

    @Override
    public BSTNode<T> getNode(BSTNode<T> parent, T val) {
        return new AVLNode<T>(parent, val);
    }
}
