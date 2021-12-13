//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
//请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
// Related Topics 数组 哈希表 👍 1280 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int small = -1;
    private int secondSmall = -1;
    private int smallest = -1;
    private int biggest = -1;

    public int firstMissingPositive(int[] nums) {
        if (nums.length <= 1){
            return nums[0] > 0? nums[0] + 1 : 1;
        }
        int num0 = nums[0], num1 = nums[1];
        if (num0 > num1){
            small = num1;
            secondSmall = num0;
        }else{
            small = num0;
            secondSmall = num1;
        }
        smallest = nums[0];
        biggest = nums[0];
        for (int i = 1;i < nums.length;++i){
            update(nums,i);
            smallest = Math.min(smallest,nums[i]);
            biggest = Math.max(biggest,nums[i]);
        }
        if (smallest > 1){
            return 1;
        }
        if (secondSmall - small > 1){
            return small + 1;
        }
        return biggest + 1;
    }

    private void update(int[] nums,int index){
        if (nums[index] < 0){
            return;
        }
        int num = nums[index];
        if (num < small){
            if (Math.abs(num - small) > 1){
                if (Math.abs(small - secondSmall) > 1){
                    secondSmall = small;
                }
                small = num;
            }
        }else if (num < secondSmall){
            if (Math.abs(num - small) > 1){
                secondSmall = num;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
