package BST;

public class TreeNode {
    TreeNode parent;
    TreeNode leftNode;
    TreeNode RightNode;
    int key;

    public TreeNode(int key) {
        this.key = key;
        parent=null;
        leftNode=null;
        RightNode=null;
    }

    public TreeNode(TreeNode parent,int key) {
        this.key=key;
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return RightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        RightNode = rightNode;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
