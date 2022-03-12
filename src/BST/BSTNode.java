package BST;

import java.lang.Math;

public class BSTNode<T extends Comparable<T>> {
    protected BSTNode<T> parent = null;
    protected BSTNode<T> left = null;
    protected BSTNode<T> right = null;
    protected BSTNodeFactory<T> factory;
    protected int height = 0;
    protected T val;

    public BSTNode(T val) {
        this.val = val;
    }

    public BSTNode(BSTNode<T> parent, T val) {
        this.val = val;
        this.parent = parent;
    }

    public BSTNode<T> getParent() {
        return parent;
    }

    public void setParent(BSTNode<T> parent) {
        this.parent = parent;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
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

    public void calcHeight() {
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

    public boolean search(T val) {
        if(this.val.compareTo(val) == 0) {
            return true;
        } else if (this.left != null && this.val.compareTo(val) > 0) {
            return this.left.search(val);
        } else if (this.right != null && this.val.compareTo(val) < 0) {
            return this.right.search(val);
        }
        return false;
    }

    public void insert(T obj) {
        if(this.val == null) {
            this.val = obj;
            this.postInsertHook();
            return;
        }
        if(obj.compareTo(this.val) <= 0) {
            if(this.left != null) {
                this.left.insert(obj);
                this.postInsertHook();
                return;
            }
            this.left = this.factory.getNode(this, obj);
            this.postInsertHook();
            return;
        }
        if(this.right != null) {
            this.right.insert(obj);
            this.postInsertHook();
            return;
        }
        this.right = this.factory.getNode(this, obj);
        this.postInsertHook();
    }

    protected void postInsertHook() {
        this.calcHeight();
    }

    public void delete(T obj) {
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
                BSTNode<T> temp = this.left;
                while(temp.right != null) {
                    temp = temp.right;
                }
                this.val = temp.val;
                this.left.delete(this.val);
            }
        } else if (obj.compareTo(this.val) < 0) {
            this.left.delete(obj);
        } else {
            this.right.delete(obj);
        }
        this.postDeleteHook();
    }

    protected void postDeleteHook() {
        this.calcHeight();
    }
}
