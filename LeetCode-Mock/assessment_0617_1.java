
/*
669. Trim a Binary Search Tree
* Given the root of a binary search tree and the lowest and highest boundaries as low and high,
trim the tree so that all its elements lies in [low, high].
Trimming the tree should not change the relative structure of the elements
that will remain in the tree (i.e., any node's descendant should remain a descendant).
It can be proven that there is a unique answer.

Return the root of the trimmed binary search tree.
Note that the root may change depending on the given bounds.
* */

public class assessment_0617_1 {
    public static void main(String[] args) {

    }

    public static class TreeNode {
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

    public static TreeNode trimBST(TreeNode root, int low, int high) {

        if(root == null) return root;

        // low보다 작은 경우, 왼쪽 서브트리도 모두 작을테니까 오른쪽 서브트리만 살려서 return
        if(root.val < low) return trimBST(root.right, low, high);

        // 마찬가지로 high보다 큰 경우, 오른쪽도 모두 클테니 왼쪽 서브트리만 살려서 return
        if(root.val > high) return trimBST(root.left, low, high);

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }
}
