//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 2905 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trap(int[] height) {
        if (height.length <= 0) return 0;
        int[] lm = leftMax(height);
        int[] rm = rightMax(height);
        int res = 0;
        for (int i = 0;i < height.length;++i){
            res += Math.min(lm[i], rm[i]) - height[i];
        }
        return res;
    }
    private int[] leftMax(int[] height){
        int[] lm = new int[height.length];
        lm[0] = height[0];
        for (int i = 1;i < height.length;++i){
            lm[i] = Math.max(height[i],lm[i-1]);
        }
        return lm;
    }
    private int[] rightMax(int[] height){
        int[] rm = new int[height.length];
        rm[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2;i >= 0;--i){
            rm[i] = Math.max(rm[i+1],height[i]);
        }
        return rm;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
