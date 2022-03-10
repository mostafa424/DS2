package BST;

public class BST<T extends Comparable<T>> {
    private TreeNode<T> root;

    public BST() {
        this.root = null;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
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
