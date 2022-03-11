package BST;
import BST.BSTNode;
import java.lang.Math;
public class BST<T extends Comparable<T>> {
   private BSTNode<T> root;
   private int height;
    public BST() {
        this.root = new BSTNode<T>(null);
    }

    public BSTNode<T> getRoot() {
        return root;
    }

    public void setRoot(BSTNode<T> root) {
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
    }

    public void delete(T obj) {
        if(!this.root.search(obj)){
            return;
        }
        this.root.delete(obj);
    }

}
