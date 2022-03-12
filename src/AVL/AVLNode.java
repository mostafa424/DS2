package AVL;

import BST.BSTNode;

/**
 * AVL Tree Node implementation, inherits from BST Node implementation.
 *
 * @param <T> type of data stored in node, must implement Comparable interface.
 */
public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {

    /**
     * Default constructor, initializes node with value.
     *
     * @param val value to be stored in node.
     */
    public AVLNode(T val) {
        super(val);
        this.factory = new AVLNodeFactory<T>();
    }

    /**
     * Constructor which initializes node with parent reference and value.
     *
     * @param parent Node parent reference.
     * @param val value to be stored in node.
     */
    public AVLNode(BSTNode<T> parent, T val) {
        super(parent, val);
        this.factory = new AVLNodeFactory<T>();
    }

    /**
     * Method to rotate node left, performing all necessary reference swaps.
     *
     * @param node Node to be rotated left
     * @param <T> type of data stored in node.
     */
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

    /**
     * Method to rotate node right, performing all necessary reference swaps.
     *
     * @param node Node to be rotated right
     * @param <T> type of data stored in node.
     */
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

    /**
     * Method to calculate balance factor of a node based on children's heights.
     *
     * @param node Node to calculate balance factor for.
     * @return calculated balance factor.
     */
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

    /**
     * Overridden post-insert hook.
     * Rebalances the tree rooted at node if balance factor is not proper.
     */
    @Override
    protected void postInsertHook() {
        int balance = calcBalance(this);
        if(balance > 1) {
            int leftBal = calcBalance(this.left);
            //Left-left insertion
            if(leftBal == 1 || leftBal == 0) {
                rotateRight(this.left);
                this.calcHeight();
                this.parent.calcHeight();
            //Left-right insertion
            } else if(leftBal == -1) {
                rotateLeft(this.left.getRight());
                rotateRight(this.left);
                this.calcHeight();
                this.parent.getLeft().calcHeight();
                this.parent.calcHeight();
            }
        } else if(balance < -1) {
            int rightBal = calcBalance(this.right);
            //Right-right insertion
            if(rightBal == -1 || rightBal == 0) {
                rotateLeft(this.right);
                this.calcHeight();
                this.parent.calcHeight();
            //Right-left insertion
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

    /**
     * Overridden post-insert hook.
     * Rebalances the tree rooted at node if balance factor is not proper.
     */
    @Override
    protected void postDeleteHook() {
        this.postInsertHook();
    }
}
