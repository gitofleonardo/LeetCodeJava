package cn.huangchengxi.leetcode.java.leetcode.editor.cn;

public class Main {
    public static void main(String[] args){
        Solution s = new Solution();
        TreeNode root = new TreeNode(
                1,null,
                new TreeNode(
                        2,null,
                        new TreeNode(
                                3,null,
                                new TreeNode(
                                        4,null,
                                        new TreeNode(
                                                5,null,null
                                        )
                                )
                        )
                )
        );
        int res = s.pathSum(root,3);
        System.out.println(res);
    }
}
