package AVL;

import BST.BSTNode;

public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {

    public AVLNode(T val) {
        super(val);
        this.factory = new AVLNodeFactory<T>();
    }

    public AVLNode(BSTNode<T> parent, T val) {
        super(parent, val);
        this.factory = new AVLNodeFactory<T>();
    }

    private static <T extends Comparable<T>> void rotateLeft(BSTNode<T> node) {
        if(node.getParent().getParent() != null) {
            if(node.getParent() == node.getParent().getParent().getLeft()) {
                node.getParent().getParent().setLeft(node);
            } else {
                node.getParent().getParent().setRight(node);
            }
        }
        node.getParent().setRight(node.getLeft());
        if(node.getLeft() != null) {
            node.getLeft().setParent(node.getParent());
        }
        node.setLeft(node.getParent());
        BSTNode<T> temp = node.getParent().getParent();
        node.getParent().setParent(node);
        node.setParent(temp);
    }

    private static <T extends Comparable<T>> void rotateRight(BSTNode<T> node) {
        if(node.getParent().getParent() != null) {
            if(node.getParent() == node.getParent().getParent().getLeft()) {
                node.getParent().getParent().setLeft(node);
            } else {
                node.getParent().getParent().setRight(node);
            }
        }
        node.getParent().setLeft(node.getRight());
        if(node.getRight() != null) {
            node.getRight().setParent(node.getParent());
        }
        node.setRight(node.getParent());
        BSTNode<T> temp = node.getParent().getParent();
        node.getParent().setParent(node);
        node.setParent(temp);
    }

    private static int calcBalance(BSTNode<?> node) {
        if(node.getLeft() != null) {
            if(node.getRight() != null) {
                return node.getLeft().getHeight() - node.getRight().getHeight();
            }
            return node.getLeft().getHeight() + 1;
        }
        if(node.getRight() != null) {
            return -1 - node.getRight().getHeight();
        }
        return 0;
    }

    @Override
    protected void postInsertHook() {
        int balance = calcBalance(this);
        if(balance > 1) {
            int leftBal = calcBalance(this.left);
            if(leftBal == 1 || leftBal == 0) {
                rotateRight(this.left);
                this.calcHeight();
                this.parent.calcHeight();
            } else if(leftBal == -1) {
                rotateLeft(this.left.getRight());
                rotateRight(this.left);
                this.calcHeight();
                this.parent.getLeft().calcHeight();
                this.parent.calcHeight();
            }
        } else if(balance < -1) {
            int rightBal = calcBalance(this.right);
            if(rightBal == -1 || rightBal == 0) {
                rotateLeft(this.right);
                this.calcHeight();
                this.parent.calcHeight();
            } else if(rightBal == 1) {
                rotateRight(this.right.getLeft());
                rotateLeft(this.right);
                this.calcHeight();
                this.parent.getRight().calcHeight();
                this.parent.calcHeight();
            }
        } else {
            this.calcHeight();
        }
    }

    @Override
    protected void postDeleteHook() {
        this.postInsertHook();
    }
}
