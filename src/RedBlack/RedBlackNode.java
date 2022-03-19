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
    protected int black;

    /**
     * Default constructor, initializes node with value.
     *
     * @param val value to be stored in node.
     */
    public RedBlackNode(T val) {
        super(val);
        this.black = 0;
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
        this.black = 0;
        this.factory = new RedBlackNodeFactory<T>();
    }

    public RedBlackNode(RedBlackNode<T> parent, T val, int color) {
        super(parent, val);
        this.black = color;
        this.factory = new RedBlackNodeFactory<T>();
    }

    public int getBlack() {
        return black;
    }

    public void setBlack(int black) {
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
            this.black = 1;
        } else {
            if(((RedBlackNode<T>)this.parent).black == 0) {
                if(this.parent == this.parent.getParent().getLeft()) {
                    if(this.parent.getParent().getRight() != null && ((RedBlackNode<T>)this.parent.getParent().getRight()).black == 0) {
                        ((RedBlackNode<T>)this.parent).black = 1;
                        ((RedBlackNode<T>)this.parent.getParent().getRight()).black = 1;
                        ((RedBlackNode<T>)this.parent.getParent()).black = 0;
                        ((RedBlackNode<T>)this.parent.getParent()).fixColor();
                    } else {
                        if(this == this.parent.getLeft()) {
                            ((RedBlackNode<T>)this.parent).black = 1;
                            ((RedBlackNode<T>)this.parent.getParent()).black = 0;
                            rotateRight(this.parent);
                        } else {
                            rotateLeft(this);
                            ((RedBlackNode<T>)this.left).fixColor();
                        }
                    }
                } else {
                    if(this.parent.getParent().getLeft() != null && ((RedBlackNode<T>)this.parent.getParent().getLeft()).black == 0) {
                        ((RedBlackNode<T>)this.parent).black = 1;
                        ((RedBlackNode<T>)this.parent.getParent().getLeft()).black = 1;
                        ((RedBlackNode<T>)this.parent.getParent()).black = 0;
                        ((RedBlackNode<T>)this.parent.getParent()).fixColor();
                    } else {
                        if(this == this.parent.getRight()) {
                            ((RedBlackNode<T>)this.parent).black = 1;
                            ((RedBlackNode<T>)this.parent.getParent()).black = 0;
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
        int color=0;
        if(obj.compareTo(this.val) == 0){
            if(this.left == null && this.right == null) {
                if(this.parent == null) {
                    this.val = null;
                } else {
                    if (this.parent.getLeft() == this) {
                        if(this.black==1){
                            deleteFixup();
                        }
                        this.parent.setLeft(null);

                    } else {
                        if(this.black==1){
                            deleteFixup();
                        }
                        this.parent.setRight(null);
                    }
                }
            } else if (this.getLeft() == null) {
                this.val = this.right.getVal();
                if(this.right instanceof RedBlackNode<T> ){
                    color = ((RedBlackNode<T>) this.right).black;
                }
                this.right.setParent(null);
                this.right = null;
            } else if (this.right == null) {
                this.val = this.left.getVal();
                if(this.left instanceof RedBlackNode<T> ){
                    color = ((RedBlackNode<T>) this.left).black;
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
                if(temp instanceof RedBlackNode<T> ){
                    color = ((RedBlackNode<T>) temp).black;
                }
                this.left.delete(this.val);

            }
            if(color == 1){
                this.deleteFixup();
            }
        } else if (obj.compareTo(this.val) < 0) {
            this.left.delete(obj);
        } else {
            this.right.delete(obj);
        }
    }

    public void deleteFixup(){
        if(this.parent==null){
            this.black=1;
            return;
        }
        else if(this.parent.getLeft() == this){
            RedBlackNode<T> sibling = (RedBlackNode<T>) this.parent.getRight();
            if(sibling==null){
                return;
            }
            RedBlackNode<T> siblingLeft= (RedBlackNode<T>) sibling.getLeft();
            RedBlackNode<T> siblingRight= (RedBlackNode<T>) sibling.getRight();

            if(sibling.black==1 && ((siblingLeft==null && siblingRight==null)|| (siblingLeft.black==1 && siblingRight.black==1))) {
                if (((RedBlackNode<T>) this.parent).black == 1) {
                    sibling.black = 0;
                    ((RedBlackNode<T>) this.parent).deleteFixup();
                } else {
                    ((RedBlackNode<T>) this.parent).black = 1;
                    sibling.black = 0;
                }
            }
            else if(sibling.black==1 && (siblingLeft.black==0 && (sibling.getRight()==null|| siblingRight.black==1))){
                int tmp2 = siblingLeft.black;
                siblingLeft.black= sibling.black;
                sibling.black=tmp2;
                rotateRight(siblingLeft);
                int tmp = ((RedBlackNode<T>) this.parent).black;
                ((RedBlackNode<T>) this.parent).black = siblingLeft.black;
                siblingLeft.black=tmp;
                sibling.black=1;
                rotateLeft(sibling);
            }
            else if(sibling.black==1 && (siblingRight.black==0 && (sibling.getLeft()==null|| siblingLeft.black==1))){
                int tmp = ((RedBlackNode<T>) this.parent).black;
                ((RedBlackNode<T>) this.parent).black = siblingLeft.black;
                siblingLeft.black=tmp;
                sibling.black=1;
                rotateLeft(sibling);
            }
            else if(sibling.black==0){
                sibling.black=1;
                ((RedBlackNode<T>) this.parent).black=0;
                rotateLeft(sibling);
                (this).deleteFixup();
            }
        }
        else{
            RedBlackNode<T> sibling = (RedBlackNode<T>) this.parent.getLeft();
            if(sibling==null){
                return;
            }
            RedBlackNode<T> siblingLeft= (RedBlackNode<T>) sibling.getLeft();
            RedBlackNode<T> siblingRight= (RedBlackNode<T>) sibling.getRight();
            if(sibling.black==1 && ((sibling.getLeft()==null && sibling.getRight()==null)|| (siblingLeft.black==1 && siblingRight.black==1))) {
                if (((RedBlackNode<T>) this.parent).black == 1) {
                    sibling.black = 0;
                    ((RedBlackNode<T>) this.parent).deleteFixup();
                } else {
                    ((RedBlackNode<T>) this.parent).black = 1;
                    sibling.black = 0;
                }
            }
            else if(sibling.black==1 && (siblingRight.black==0 && (sibling.getLeft()==null|| siblingLeft.black==1))){
                int tmp2= siblingRight.black;
                siblingRight.black= sibling.black;
                sibling.black= tmp2;
                rotateLeft(siblingRight);
                int tmp = ((RedBlackNode<T>) this.parent).black;
                ((RedBlackNode<T>) this.parent).black = siblingRight.black;
                siblingRight.black=tmp;
                sibling.black=1;
                rotateRight(sibling);
            }
            else if(sibling.black==1 && (siblingLeft.black==0 && (sibling.getRight()==null|| siblingRight.black==1))){
                int tmp = ((RedBlackNode<T>) this.parent).black;
                ((RedBlackNode<T>) this.parent).black = sibling.black;
                sibling.black=tmp;
                siblingLeft.black=1;
                rotateRight(sibling);
            }
            else if(sibling.black==0){
                sibling.black=1;
                ((RedBlackNode<T>) this.parent).black=0;
                rotateRight(sibling);
                this.deleteFixup();
            }
        }
    }
}

