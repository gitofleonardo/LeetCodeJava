//如果数组是单调递增或单调递减的，那么它是单调的。 
//
// 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是
//单调递减的。 
//
// 当给定的数组 A 是单调数组时返回 true，否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：[1,2,2,3]
//输出：true
// 
//
// 示例 2： 
//
// 输入：[6,5,4,4]
//输出：true
// 
//
// 示例 3： 
//
// 输入：[1,3,2]
//输出：false
// 
//
// 示例 4： 
//
// 输入：[1,2,4,5]
//输出：true
// 
//
// 示例 5： 
//
// 输入：[1,1,1]
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 50000 
// -100000 <= A[i] <= 100000 
// 
// Related Topics 数组 👍 151 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isMonotonic(int[] nums) {
        if (nums.length <= 2){
            return true;
        }
        boolean settleInc = false;
        boolean increase = false;
        int last = nums[0];
        for (int i=1;i<nums.length;++i){
            if (!settleInc){
                if (nums[i] == last){
                    last = nums[i];
                    continue;
                }
                increase = nums[i] > last;
                settleInc = true;
            }
            if (increase && nums[i] < last) return false;
            if (!increase && nums[i] > last) return false;
            last = nums[i];
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
