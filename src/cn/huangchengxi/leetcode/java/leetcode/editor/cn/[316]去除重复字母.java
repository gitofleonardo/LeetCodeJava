//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-
//distinct-characters 相同 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 
// Related Topics 栈 贪心 字符串 单调栈 👍 629 👎 0


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String removeDuplicateLetters(String s) {
        Map<Character,Integer> countMap = new HashMap<>();
        for (int i = 0;i < s.length();++i){
            char ch = s.charAt(i);
            countMap.compute(ch, (character, integer) -> {
                if (integer == null){
                    return 1;
                }
                return integer + 1;
            });
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i < s.length();++i){
            char ch = s.charAt(i);
            while (!(stack.empty() || ch > stack.peek() || countMap.get(stack.peek()) <= 1)){
                stack.pop();
            }
            stack.push(ch);
            countMap.compute(ch,(character, integer) -> integer == null? 0 : integer - 1);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()){
            sb.insert(0,stack.pop());
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
