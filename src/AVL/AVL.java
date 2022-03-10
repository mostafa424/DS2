package AVL;

public class AVL<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVL() {
        this.root = null;
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    public boolean search(T val) {
        return this.root.search(val);
    }

    public void insert(T obj) {
        this.root.insert(obj);
    }

    public void delete(T obj) {
        if(!this.root.search(obj)){
            return;
        }
        this.root.delete(obj);
    }
}
