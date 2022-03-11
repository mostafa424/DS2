package BST;

public class BSTNodeFactory<T extends Comparable<T>> {
    public BSTNode<T> getNode(T val) {
        return new BSTNode<T>(val);
    }

    public BSTNode<T> getNode(BSTNode<T> parent, T val) {
        return new BSTNode<T>(parent, val);
    }
}
