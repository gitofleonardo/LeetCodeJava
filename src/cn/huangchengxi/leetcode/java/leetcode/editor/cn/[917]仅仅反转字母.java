//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 双指针 字符串 👍 94 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseOnlyLetters(String s) {
        int start = 0, end = s.length() - 1;
        char[] chs = new char[s.length()];
        for (int i = 0;i < s.length();++i){
            chs[i] = s.charAt(i);
        }
        while (start < end){
            while (start < end && !isAlpha(s.charAt(start))){
                ++start;
            }
            while (start < end && !isAlpha(s.charAt(end))){
                --end;
            }
            if (start < end){
                char tmp = chs[start];
                chs[start] = chs[end];
                chs[end] = tmp;
            }
            ++start;
            --end;
        }
        return toString(chs);
    }
    private boolean isAlpha(char ch){
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
    private String toString(char[] chs){
        StringBuilder sb = new StringBuilder();
        for (char i:chs){
            sb.append(i);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
