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
     * Method to rotate node left, performing all necessary reference swaps.
     *
     * @param node Node to be rotated left
     * @param <T>  type of data stored in node.
     */
    private static <T extends Comparable<T>> void rotateLeft(BSTNode<T> node) {
        if (node.getParent().getParent() != null) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                node.getParent().getParent().setLeft(node);
            } else {
                node.getParent().getParent().setRight(node);
            }
        }
        node.getParent().setRight(node.getLeft());
        if (node.getLeft() != null) {
            node.getLeft().setParent(node.getParent());
        }
        node.setLeft(node.getParent());
        BSTNode<T> temp = node.getParent().getParent();
        node.getParent().setParent(node);
        node.setParent(temp);
    }

    /**
     * Method to rotate node right, performing all necessary reference swaps.
     *
     * @param node Node to be rotated right
     * @param <T>  type of data stored in node.
     */
    private static <T extends Comparable<T>> void rotateRight(BSTNode<T> node) {
        if (node.getParent().getParent() != null) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                node.getParent().getParent().setLeft(node);
            } else {
                node.getParent().getParent().setRight(node);
            }
        }
        node.getParent().setLeft(node.getRight());
        if (node.getRight() != null) {
            node.getRight().setParent(node.getParent());
        }
        node.setRight(node.getParent());
        BSTNode<T> temp = node.getParent().getParent();
        node.getParent().setParent(node);
        node.setParent(temp);
    }

    /**
     * Overridden insert.
     * Rebalances the tree rooted at node if black height is not proper.
     */
    @Override
    public void insert(T obj) {
        if(this.val == null) {
            this.val = obj;
            this.fixColor();
            this.calcHeight();
            return;
        }
        if (this.val.compareTo(obj) > 0) {
            if(this.left == null) {
                this.left = new RedBlackNode<T>(this, obj);
                ((RedBlackNode<T>)this.left).fixColor();
                return;
            }
            this.left.insert(obj);
        } else if (this.val.compareTo(obj) < 0) {
            if(this.right == null) {
                this.right = new RedBlackNode<T>(this, obj);
                ((RedBlackNode<T>)this.right).fixColor();
                return;
            }
            this.right.insert(obj);
        }
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
                    } else {
                        if(this == this.parent.getLeft()) {
                            ((RedBlackNode<T>)this.parent).black = true;
                            ((RedBlackNode<T>)this.parent.getParent()).black = false;
                            rotateRight(this.parent);
                        } else {
                            rotateLeft(this);
                            ((RedBlackNode<T>)this.left).fixColor();
                        }
                    }
                } else {
                    if(this.parent.getParent().getLeft() != null && !((RedBlackNode<T>)this.parent.getParent().getLeft()).black) {
                        ((RedBlackNode<T>)this.parent).black = true;
                        ((RedBlackNode<T>)this.parent.getParent().getLeft()).black = true;
                        ((RedBlackNode<T>)this.parent.getParent()).black = false;
                        ((RedBlackNode<T>)this.parent.getParent()).fixColor();
                    } else {
                        if(this == this.parent.getRight()) {
                            ((RedBlackNode<T>)this.parent).black = true;
                            ((RedBlackNode<T>)this.parent.getParent()).black = false;
                            rotateLeft(this.parent);
                        } else {
                            rotateRight(this);
                            ((RedBlackNode<T>)this.right).fixColor();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void delete(T obj){
        boolean color=false;
        if(obj.compareTo(this.val) == 0){
            if(this.left == null && this.right == null) {
                if(this.parent == null) {
                    this.val = null;
                } else {
                    if (this.parent.getLeft() == this) {
                        if(this.black){
                            deleteFixup();
                        }
                        this.parent.setLeft(null);

                    } else {
                        if(this.black){
                            deleteFixup();
                        }
                        this.parent.setRight(null);
                    }
                }
            } else if (this.getLeft() == null) {
                this.val = this.right.getVal();
                if(((RedBlackNode<T>) this.right).black){
                    ((RedBlackNode<T>) this.right).deleteFixup();
                }
                this.right.setParent(null);
                this.right = null;
            } else if (this.right == null) {
                this.val = this.left.getVal();
                if(((RedBlackNode<T>) this.left).black){
                    ((RedBlackNode<T>) this.left).deleteFixup();
                }
                this.left.setParent(null);
                this.left = null;
            }

            else {
                BSTNode<T> temp = this.getLeft();
                while(temp.getRight() != null) {
                    temp = temp.getRight();
                }
                this.val = temp.getVal();
                /*if(((RedBlackNode<T>)temp).black){
                    ((RedBlackNode<T>)temp).deleteFixup();
                }*/
                this.left.delete(this.val);

            }

        } else if (obj.compareTo(this.val) < 0) {
            this.left.delete(obj);
        } else {
            this.right.delete(obj);
        }
    }

    public void deleteFixup(){
        if(this.parent==null){
            return;
        }
        else if(this.parent.getLeft() == this) {
            RedBlackNode<T> sibling = (RedBlackNode<T>) this.parent.getRight();
            RedBlackNode<T> siblingLeft = null;
            RedBlackNode<T> siblingRight = null;
            if (sibling != null) {
                siblingLeft = (RedBlackNode<T>) sibling.getLeft();
                siblingRight = (RedBlackNode<T>) sibling.getRight();
            }
            /*else{
                return;
            }*/
            if(sibling!=null && sibling.black){
            if (((siblingLeft == null && siblingRight == null) || (siblingLeft != null && siblingLeft.black && siblingRight != null && siblingRight.black))) {
                sibling.black = false;
                if (((RedBlackNode<T>) this.parent).black) {
                    ((RedBlackNode<T>) this.parent).deleteFixup();
                } else {
                    ((RedBlackNode<T>) this.parent).black = true;
                }
            } else if ((siblingLeft != null && !siblingLeft.black && (sibling.getRight() == null || siblingRight.black))) {
                swapColors(sibling, siblingLeft);
                if(siblingRight!=null){rotateRight(siblingRight);}
                deleteFixup();

            } else if ((siblingRight != null && !siblingRight.black && (siblingLeft == null || siblingLeft.black))) {
               swapColors((RedBlackNode<T>) this.parent, sibling);
                siblingRight.black = true;
                rotateLeft(sibling);
            }
            else if(siblingRight!=null && siblingLeft!=null) {
                    sibling.black = ((RedBlackNode<T>) this.parent).black;
                    siblingRight.black=true;
                    rotateLeft(siblingRight);
                if(((RedBlackNode<T>)(this.parent).getParent()).black){
                    ((RedBlackNode<T>)(this.parent).getParent()).deleteFixup();
                }
                else{
                    ((RedBlackNode<T>)(this.parent).getParent()).black=true;
                }
            }
        }
            else if(sibling!=null){
                sibling.black=true;
                ((RedBlackNode<T>) this.parent).black=false;
                rotateLeft(sibling);
                (this).deleteFixup();
            }
        }
        else{
            RedBlackNode<T> sibling = (RedBlackNode<T>) this.parent.getLeft();
            RedBlackNode<T> siblingLeft = null;
            RedBlackNode<T> siblingRight = null;
            if (sibling != null) {
                siblingLeft = (RedBlackNode<T>) sibling.getLeft();
                siblingRight = (RedBlackNode<T>) sibling.getRight();
            }
            if(sibling!=null && sibling.black){
            if(((sibling.getLeft()==null && sibling.getRight()==null)|| (siblingLeft!=null && siblingLeft.black && siblingRight!=null&& siblingRight.black))) {
                if (((RedBlackNode<T>) this.parent).black) {
                    sibling.black = false;
                    ((RedBlackNode<T>) this.parent).deleteFixup();
                } else {
                    ((RedBlackNode<T>) this.parent).black = true;
                    sibling.black = false;
                }
            }
            else if((siblingRight!=null && !siblingRight.black && (sibling.getLeft()==null|| siblingLeft.black))){
                swapColors(siblingRight,sibling);
                if(siblingLeft!=null){rotateLeft(siblingLeft);}
                deleteFixup();
            }
            else if((siblingLeft!=null && !siblingLeft.black && (sibling.getRight()==null|| siblingRight.black))){
                swapColors((RedBlackNode<T>) this.parent,sibling);
                siblingLeft.black=true;
                rotateRight(sibling);
            }
            else if(siblingRight!=null && siblingLeft!=null) {
                sibling.black = ((RedBlackNode<T>) this.parent).black;
                siblingLeft.black=true;
                rotateRight(siblingLeft);
                if(((RedBlackNode<T>)(this.parent).getParent()).black){
                    ((RedBlackNode<T>)(this.parent).getParent()).deleteFixup();
                }
                else{
                    ((RedBlackNode<T>)(this.parent).getParent()).black=true;
                }
            }
            }
            else if(sibling!=null){
                sibling.black=true;
                ((RedBlackNode<T>) this.parent).black=false;
                rotateRight(sibling);
                this.deleteFixup();
            }
        }
    }
    private void swapColors(RedBlackNode<T> node1,RedBlackNode<T> node2){
        boolean tmp = ((RedBlackNode<T>) node2).black;
        ((RedBlackNode<T>) node2).black = node1.black;
        node1.black=tmp;
    }
}

