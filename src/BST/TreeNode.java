package BST;

import java.lang.Math;

public class TreeNode<T extends Comparable<T>> {
    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private int height = 0;
    private T val;

    public TreeNode(T val) {
        this.val = val;
        parent = null;
        left = null;
        right = null;
    }

    public TreeNode(TreeNode<T> parent, T val) {
        this.val = val;
        this.parent = parent;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return this.height;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    private void calcHeight() {
        if(this.left != null) {
            if(this.right != null) {
                this.height = Math.max(this.left.getHeight(), this.right.getHeight()) + 1;
                return;
            }
            this.height = this.left.getHeight() + 1;
            return;
        }
        if(this.right != null) {
            this.height = this.right.getHeight() + 1;
            return;
        }
        this.height = 0;
    }

    protected boolean search(T comp) {
        if(comp == null) {
            return true;
        }
        if(this.val.compareTo(comp) == 0) {
            return true;
        }
        if(this.left != null){
            if(this.right != null){
                return this.left.search(comp) || this.right.search(comp);
            }
            return this.left.search(comp);
        }
        if(this.right != null){
            return this.right.search(comp);
        }
        return false;
    }

    protected void insert(T obj) {
        if(this.val == null) {
            this.val = obj;
            this.calcHeight();
            this.postInsertHook();
            return;
        }
        if(obj.compareTo(this.val) <= 0) {
            if(this.left != null) {
                this.left.insert(obj);
                this.calcHeight();
                this.postInsertHook();
                return;
            }
            this.left = new TreeNode<T>(this, obj);
            this.calcHeight();
            this.postInsertHook();
            return;
        }
        if(this.right != null) {
            this.right.insert(obj);
            this.calcHeight();
            this.postInsertHook();
            return;
        }
        this.right = new TreeNode<T>(this, obj);
        this.calcHeight();
        this.postInsertHook();
    }

    protected void postInsertHook() {
        return;
    }

    protected void delete(T obj) {
        if(obj.compareTo(this.val) == 0){
            if(this.left == null && this.right == null) {
                if(this.parent == null) {
                    this.val = null;
                } else {
                    if (this.parent.left == this) {
                        this.parent.left = null;
                    } else {
                        this.parent.right = null;
                    }
                }
            } else if (this.left == null) {
                this.val = this.right.val;
                this.right.parent = null;
                this.right = null;
            } else if (this.right == null) {
                this.val = this.left.val;
                this.left.parent = null;
                this.left = null;
            } else {
                TreeNode<T> temp = this.left;
                while(temp.right != null) {
                    temp = temp.right;
                }
                this.val = temp.val;
                temp.delete(this.val);
            }
        } else if (obj.compareTo(this.val) < 0) {
            this.left.delete(obj);
        } else {
            this.right.delete(obj);
        }
    }
}
