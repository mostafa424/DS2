package RedBlack;

import BST.BSTNode;

/**
 * Red-Black Tree Node implementation, inherits from BST Node implementation.
 *
 * @param <T> type of data stored in node, must implement Comparable interface.
 */
public class RedBlackNode<T extends Comparable<T>> extends BSTNode<T> {
    /**
     * Colour state of node; 0->red, 1->black, 2->double black.
     */
    protected boolean black;

    /**
     * Default constructor, initializes node with value.
     *
     * @param val value to be stored in node.
     */
    public RedBlackNode(T val) {
        super(val);
        this.black = false;
        this.factory = new RedBlackNodeFactory<T>();
    }

    /**
     * Constructor which initializes node with parent reference and value.
     *
     * @param parent Node parent reference.
     * @param val    value to be stored in node.
     */
    public RedBlackNode(BSTNode<T> parent, T val) {
        super(parent, val);
        this.black = false;
        this.factory = new RedBlackNodeFactory<T>();
    }

    public RedBlackNode(RedBlackNode<T> parent, T val, boolean color) {
        super(parent, val);
        this.black = color;
        this.factory = new RedBlackNodeFactory<T>();
    }

    public boolean getBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    /**
     * Overridden insert.
     * Rebalances the tree rooted at node if black height is not proper.
     */
    @Override
    public boolean insert(T obj) {
        boolean res = false;
        if(this.val == null) {
            this.val = obj;
            this.fixColor();
            return true;
        }
        if(this.val.compareTo(obj) == 0) return false;
        if (this.val.compareTo(obj) > 0) {
            if(this.left == null) {
                this.left = new RedBlackNode<T>(this, obj);
                ((RedBlackNode<T>)this.left).fixColor();
                this.calcHeight();
                return true;
            }
            res = this.left.insert(obj);
            this.calcHeight();
            return res;
        } else if (this.val.compareTo(obj) < 0) {
            if(this.right == null) {
                this.right = new RedBlackNode<T>(this, obj);
                ((RedBlackNode<T>)this.right).fixColor();
                this.calcHeight();
                return true;
            }
            res = this.right.insert(obj);
            this.calcHeight();
            return res;
        }
        return false;
    }

    /**
     * Method to fix coloring of nodes in tree to which a value was inserted.
     */
    private void fixColor() {
        if(this.parent == null) {
            this.black = true;
        } else {
            if(!((RedBlackNode<T>)this.parent).black) {
                if(this.parent == this.parent.getParent().getLeft()) {
                    if(this.parent.getParent().getRight() != null && !((RedBlackNode<T>)this.parent.getParent().getRight()).black) {
                        ((RedBlackNode<T>)this.parent).black = true;
                        ((RedBlackNode<T>)this.parent.getParent().getRight()).black = true;
                        ((RedBlackNode<T>)this.parent.getParent()).black = false;
                        ((RedBlackNode<T>)this.parent.getParent()).fixColor();
                        this.calcHeight();
                    } else {
                        if(this == this.parent.getLeft()) {
                            ((RedBlackNode<T>)this.parent).black = true;
                            ((RedBlackNode<T>)this.parent.getParent()).black = false;
                            this.parent.rotateRight();
                            this.calcHeight();
                            this.parent.getRight().calcHeight();
                            this.parent.calcHeight();
                        } else {
                            this.rotateLeft();
                            ((RedBlackNode<T>)this.left).fixColor();
                            this.calcHeight();
                        }
                    }
                } else {
                    if(this.parent.getParent().getLeft() != null && !((RedBlackNode<T>)this.parent.getParent().getLeft()).black) {
                        ((RedBlackNode<T>)this.parent).black = true;
                        ((RedBlackNode<T>)this.parent.getParent().getLeft()).black = true;
                        ((RedBlackNode<T>)this.parent.getParent()).black = false;
                        ((RedBlackNode<T>)this.parent.getParent()).fixColor();
                        this.calcHeight();
                    } else {
                        if(this == this.parent.getRight()) {
                            ((RedBlackNode<T>)this.parent).black = true;
                            ((RedBlackNode<T>)this.parent.getParent()).black = false;
                            this.parent.rotateLeft();
                            this.calcHeight();
                            this.parent.getLeft().calcHeight();
                            this.parent.calcHeight();
                        } else {
                            this.rotateRight();
                            ((RedBlackNode<T>)this.right).fixColor();
                            this.calcHeight();
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean delete(T obj){
        boolean res = false;
        if(obj.compareTo(this.val) == 0){
            if(this.left == null && this.right == null) {
                if(this.parent == null) {
                    this.val = null;
                } else {
                    if(this.black){
                        this.deleteFixup();
                    }
                    if (this.parent.getLeft() == this) {
                        this.parent.setLeft(null);
                    } else {
                        this.parent.setRight(null);
                    }
                }
                res = true;
            } else if (this.left== null) {
                this.val = this.right.getVal();
                res = this.right.delete(this.val);
            } else if (this.right == null) {
                this.val = this.left.getVal();
                res = this.left.delete(this.val);
            } else {
                BSTNode<T> temp = this.getLeft();
                while(temp.getRight() != null) {
                    temp = temp.getRight();
                }
                this.val = temp.getVal();
                res = this.left.delete(this.val);
            }
        } else if (obj.compareTo(this.val) < 0) {
            if(this.left != null) res = this.left.delete(obj);
        } else {
            if(this.right != null) res = this.right.delete(obj);
        }
        return res;
    }

    public void deleteFixup(){
        if(this.parent == null) {
            return;
        }
        if(!this.black) {
            this.black = true;
            return;
        }
        if(this.parent.getLeft() == this) {
            RedBlackNode<T> sibling = (RedBlackNode<T>) this.parent.getRight();
            RedBlackNode<T> siblingLeft = (RedBlackNode<T>) sibling.getLeft();
            RedBlackNode<T> siblingRight = (RedBlackNode<T>) sibling.getRight();
            if(sibling.black){
                if ((siblingLeft == null && siblingRight == null) || (siblingLeft != null && siblingLeft.black && siblingRight != null && siblingRight.black)) {
                    sibling.black = false;
                    ((RedBlackNode<T>) this.parent).deleteFixup();
                } else if (siblingLeft != null && !siblingLeft.black && (siblingRight == null || siblingRight.black)) {
                    swapColors(sibling, siblingLeft);
                    siblingLeft.rotateRight();
                    this.deleteFixup();
                } else {
                    sibling.black = ((RedBlackNode<T>)this.parent).black;
                    ((RedBlackNode<T>)this.parent).black = true;
                    if(siblingRight != null) {
                        siblingRight.black = true;
                    }
                    sibling.rotateLeft();
                }
            } else {
                sibling.black=true;
                ((RedBlackNode<T>) this.parent).black=false;
                sibling.rotateLeft();
                this.deleteFixup();
            }
        } else if(this.parent.getRight() == this) {
            RedBlackNode<T> sibling = (RedBlackNode<T>) this.parent.getLeft();
            RedBlackNode<T> siblingLeft = (RedBlackNode<T>) sibling.getLeft();
            RedBlackNode<T> siblingRight = (RedBlackNode<T>) sibling.getRight();
            if(sibling.black){
                if((siblingLeft == null && siblingRight == null) || ( siblingLeft != null && siblingLeft.black && siblingRight != null && siblingRight.black)) {
                    sibling.black = false;
                    ((RedBlackNode<T>) this.parent).deleteFixup();
                } else if(siblingRight != null && !siblingRight.black && (siblingLeft == null || siblingLeft.black)){
                    swapColors(sibling, siblingRight);
                    siblingRight.rotateLeft();
                    this.deleteFixup();
                } else {
                    sibling.black = ((RedBlackNode<T>)this.parent).black;
                    ((RedBlackNode<T>)this.parent).black = true;
                    if(siblingLeft != null) {
                        siblingLeft.black = true;
                    }
                    sibling.rotateRight();
                }
            } else {
                sibling.black=true;
                ((RedBlackNode<T>) this.parent).black=false;
                sibling.rotateRight();
                this.deleteFixup();
            }
        }
    }
    private void swapColors(RedBlackNode<T> node1,RedBlackNode<T> node2){
        boolean tmp = node2.black;
        node2.black = node1.black;
        node1.black = tmp;
    }
}

