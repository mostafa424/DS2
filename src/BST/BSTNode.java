package BST;

import java.lang.Math;

/**
 * BST Node implementation.
 *
 * @param <T> Type of data stored in node, must implement Comparable interface.
 */
public class BSTNode<T extends Comparable<T>> {
    /**
     * Reference to parent node.
     */
    protected BSTNode<T> parent = null;
    /**
     * Reference to left child node.
     */
    protected BSTNode<T> left = null;
    /**
     * Reference to right child node.
     */
    protected BSTNode<T> right = null;
    /**
     * Factory object to create new nodes for insertion.
     */
    protected BSTNodeFactory<T> factory;



    /**
     * Height of tree rooted at node.
     */
    protected int height = 0;
    /**
     * Value stored in node.
     */
    protected T val;




    /**
     * Default constructor, initializes node with value.
     *
     * @param val value to be stored in node.
     */
    public BSTNode(T val) {
        this.val = val;
    }

    /**
     * Constructor which initializes node with parent reference and value.
     *
     * @param parent Node parent reference.
     * @param val value to be stored in node.
     */
    public BSTNode(BSTNode<T> parent, T val) {
        this.val = val;
        this.parent = parent;
    }

    /**
     * Parent node reference getter.
     * @return reference to parent node.
     */
    public BSTNode<T> getParent() {
        return parent;
    }

    /**
     * Parent node reference setter.
     *
     * @param parent reference to overwrite parent node reference
     */
    public void setParent(BSTNode<T> parent) {
        this.parent = parent;
    }

    /**
     * Left child node reference getter.
     * @return reference to left child node.
     */
    public BSTNode<T> getLeft() {
        return left;
    }

    /**
     * Left child node reference setter.
     *
     * @param left reference to overwrite left child node reference
     */
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    /**
     * Right child node reference getter.
     * @return reference to right child node.
     */
    public BSTNode<T> getRight() {
        return right;
    }

    /**
     * Right child node reference setter.
     *
     * @param right reference to overwrite right child node reference
     */
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    /**
     * Height getter.
     * @return height of tree rooted at node.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Value getter.
     * @return value stored in node.
     */
    public T getVal() {
        return val;
    }

    /**
     * Value setter.
     * @param val value to overwrite node contents.
     */
    public void setVal(T val) {
        this.val = val;
    }

    /**
     * Method to calculate height of tree rooted at node.
     */
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

    /**
     * Method to search tree rooted at node for value.
     *
     * @param val value to search tree for.
     * @return <code>boolean</code> value, true if found, false else.
     */
    public boolean contains(T val) {
        if(this.val != null && this.val.compareTo(val) == 0) {
            return true;
        } else if (this.val != null && this.left != null && this.val.compareTo(val) > 0) {
            return this.left.contains(val);
        } else if (this.val != null && this.right != null && this.val.compareTo(val) < 0) {
            return this.right.contains(val);
        }
        return false;
    }

    /**
     * Method to search and obtain a reference to node containing a specified value.
     *
     * @param val value to search tree for.
     * @return <code>BSTNode</code> containing value, null if doesn't exist.
     */
    public BSTNode<T> search(T val) {
        if(this.val != null && this.val.compareTo(val) == 0) {
            return this;
        } else if (this.val != null && this.left != null && this.val.compareTo(val) > 0) {
            return this.left.search(val);
        } else if (this.val != null && this.right != null && this.val.compareTo(val) < 0) {
            return this.right.search(val);
        }
        return null;
    }

    /**
     * Method to insert value in tree rooted at node.
     * Template method with one hook defined immediately after insertion.
     *
     * @param obj value to be inserted in tree.
     */
    public boolean insert(T obj) {
        boolean res = false;
        if(this.val == null) {
            this.val = obj;
            this.postInsertHook();
            return true;
        }
        if(this.val.compareTo(obj) == 0) return false;
        if(obj.compareTo(this.val) < 0) {
            if(this.left != null) {
                res = this.left.insert(obj);
                this.postInsertHook();
                return res;
            }
            this.left = this.factory.getNode(this, obj);
            this.postInsertHook();
            return true;
        }
        if(this.right != null) {
            res = this.right.insert(obj);
            this.postInsertHook();
            return res;
        }
        this.right = this.factory.getNode(this, obj);
        this.postInsertHook();
        return true;
    }

    /**
     * Hook for insertion method, by default recalculates node's height.
     */
    protected void postInsertHook() {
        this.calcHeight();
    }

    /**
     * Method to delete value from tree rooted at node.
     * Template method with one hook defined immediately after deletion.
     *
     * @param obj value stored in node to delete from tree.
     */
    public boolean delete(T obj) {
        boolean res = false;
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
                res = true;
            } else if (this.left == null) {
                this.val = this.right.getVal();
                res = this.right.delete(this.val);
            } else if (this.right == null) {
                this.val = this.left.getVal();
                res = this.left.delete(this.val);
            } else {
                BSTNode<T> temp = this.left;
                while(temp.right != null) {
                    temp = temp.right;
                }
                this.val = temp.val;
                res = this.left.delete(this.val);
            }
        } else if (obj.compareTo(this.val) < 0) {
            if(this.left != null) res = this.left.delete(obj);
        } else {
            if(this.right != null) res = this.right.delete(obj);
        }
        if (res) this.postDeleteHook();
        return res;
    }

    /**
     * Hook for insertion method, by default recalculates node's height.
     */
    protected void postDeleteHook() {
        this.calcHeight();
    }


}
