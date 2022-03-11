package AVL;

public class AVL<T extends Comparable<T>> {
    private AVLNode<T> root;

    private void findRoot() {
        AVLNode<T> temp = root;
        while(temp.getParent() != null) {
            temp = (AVLNode<T>) temp.getParent();
        }
        this.root = temp;
    }

    public AVL() {
        this.root = new AVLNode<T>(null);
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    public int getHeight() {
        return this.root.getHeight();
    }

    public boolean search(T val) {
        return this.root.search(val);
    }

    public void insert(T obj) {
        this.root.insert(obj);
        findRoot();
    }

    public void delete(T obj) {
        if(!this.root.search(obj)){
            return;
        }
        this.root.delete(obj);
        findRoot();
    }
}
