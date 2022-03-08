package BST;
import java.lang.Math;
public class BST {
   private TreeNode root;
   private int height;
    public BST() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    public int get_height(){
        return (int) Math.ceil(Math.log(this.height)/Math.log(2));
    }
    public String search(TreeNode n,int k){
        if(n==null) return "NotFound";
        else if(n.getKey()==k) return "Found";
        else if(k>n.getKey()) return search(n.getRightNode(),k);
        else return search(n.getLeftNode(),k);
    }
    public void insert(TreeNode n,int k){
        if(this.root==null) this.root=new TreeNode(k);
        else if(n.getKey()==k) ;
        else if(n.getRightNode()==null && k>n.getKey()) {n.setRightNode(new TreeNode(n,k));height++;}
        else if(n.getLeftNode()==null && k<n.getKey())  {n.setLeftNode(new TreeNode(n,k));height++;}
        else if(k>n.getKey())  insert(n.getRightNode(),k);
        else  insert(n.getLeftNode(),k);
    }
    public void delete(TreeNode n,int k,char left_or_right){
        if(n==null) return;
        else if(n.getKey()==k) {
            TreeNode left=n.getLeftNode();
            TreeNode right=n.getRightNode();
            if(left_or_right=='l'){
                if(left!=null) {
                    left.setParent(n.getParent());
                    if (right != null) {
                        insert(left, right.getKey());
                    }
                }
                n.getParent().setLeftNode(left);
            }
            else {
                if(right!=null) {
                    right.setParent(n.getParent());
                    if (left != null) {
                        insert(right, left.getKey());
                    }
                }
                n.getParent().setRightNode(right);
            }
            deleteElement(n);
            height--;
        }
        else if(k>n.getKey()) delete(n.getRightNode(),k,left_or_right);
        else delete(n.getLeftNode(),k,left_or_right);
    }


    private void deleteElement(TreeNode n){
        n.setParent(null);
        n.setLeftNode(null);
        n.setRightNode(null);
    }
}
