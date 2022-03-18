package com.company;

// Definition for a binary tree node.
public class Problem1{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int getHeight(TreeNode root){
            if(root==null){
                return 0;
            }
            else{
                return Math.max(getHeight(root.left),getHeight(root.right))+1;
            }
        }
        public boolean isBalanced(TreeNode root) {
            if(root == null) return true;
            int leftTree= getHeight(root.left);
            int rightTree= getHeight(root.right);
            if(Math.abs(leftTree-rightTree)>1) return false;
            return (isBalanced(root.left) && isBalanced(root.right));
        }
    }
}
