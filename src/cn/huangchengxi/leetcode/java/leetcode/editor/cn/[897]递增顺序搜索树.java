//给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的取值范围是 [1, 100] 
// 0 <= Node.val <= 1000 
// 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 二叉树 👍 253 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import cn.huangchengxi.leetcode.java.leetcode.editor.cn.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;
        LinkedList<Integer> nodes = new LinkedList<>();
        inorder_traverse(root,nodes);
        TreeNode returnRoot = new TreeNode(nodes.get(0));
        if (nodes.size() < 2){
            return returnRoot;
        }
        TreeNode head = new TreeNode(nodes.get(1));
        returnRoot.right = head;
        for (int i = 2;i < nodes.size();++i){
            head.right = new TreeNode(nodes.get(i));
            head = head.right;
        }
        return returnRoot;
    }
    private void inorder_traverse(TreeNode root, LinkedList<Integer> nodes){
        if (root == null){
            return;
        }
        inorder_traverse(root.left, nodes);
        nodes.add(root.val);
        inorder_traverse(root.right,nodes);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
