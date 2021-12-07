//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1 + 1"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：s = " 2-1 + 2 "
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 10⁵ 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// 
// Related Topics 栈 递归 数学 字符串 👍 680 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    private enum Type{
        Number,
        Plus,
        Minus,
        Left,
        Right
    }

    private class Token{
        Type type;
        int value;

        Token(Type type){
            this.type = type;
        }
        Token(Type type,int value){
            this.type = type;
            this.value = value;
        }
    }

    private class State{
        private String src;
        private int index = 0;
        private Token prevToken = null;

        State(String s){
            src = s;
        }

        Token nextToken(){
            trim();
            if (index >= src.length()){
                return null;
            }
            char ch = src.charAt(index++);
            Token tk;
            switch (ch){
                case '+':
                    tk = new Token(Type.Plus);
                    break;
                case '-':
                    if (prevToken == null || prevToken.type == Type.Left){
                        tk = nextNumber(ch);
                        break;
                    }
                    tk = new Token(Type.Minus);
                    break;
                case '(':
                    tk = new Token(Type.Left);
                    break;
                case ')':
                    tk = new Token(Type.Right);
                    break;
                default:
                    tk = nextNumber(ch);
                    break;
            }
            prevToken = tk;
            return tk;
        }

        private Token nextNumber(char first){
            StringBuilder sb = new StringBuilder();
            sb.append(first);
            while (index < src.length() && isDigit(first = src.charAt(index))){
                sb.append(first);
                ++index;
            }
            if (sb.toString().equals("-")){
                return new Token(Type.Minus);
            }
            return new Token(Type.Number,Integer.parseInt(sb.toString()));
        }

        private boolean isDigit(char ch){
            return ch >= '0' && ch <= '9';
        }

        private void trim(){
            while (index < src.length() && src.charAt(index) == ' ') ++index;
        }
    }

    public int calculate(String s) {
        Stack<Integer> stk = new Stack<>();
        Stack<Token> tokens = new Stack<>();
        State st = new State(s);
        Token tk = st.nextToken();
        while (tk != null){
            switch (tk.type){
                case Number:
                    stk.push(tk.value);
                    break;
                case Plus:
                case Minus:
                    if (tokens.empty() || priorThan(tk, tokens.peek())) {
                        tokens.push(tk);
                    }else{
                        Token t = tokens.pop();
                        int left = stk.pop();
                        int right = stk.empty()? 0 : stk.pop();
                        int res = t.type == Type.Plus ? left + right : right - left;
                        stk.push(res);
                        tokens.push(tk);
                    }
                    break;
                case Left:
                    tokens.push(tk);
                    break;
                case Right:
                    if (tokens.peek().type == Type.Left){
                        tokens.pop();
                    }else{
                        Token t = tokens.pop();
                        while (t.type != Type.Left){
                            int left = stk.pop();
                            int right = stk.empty()? 0: stk.pop();
                            int res = t.type == Type.Plus? left + right : right - left;
                            stk.push(res);
                            t = tokens.pop();
                        }
                    }
                    break;
            }
            tk = st.nextToken();
        }
        while (!tokens.empty()){
            Token token = tokens.pop();
            int left = stk.pop();
            int right = stk.empty()? 0 : stk.pop();
            int res = token.type == Type.Plus? left + right : right - left;
            stk.push(res);
        }
        return stk.pop();
    }

    private boolean priorThan(Token t1,Token t2){
        if (t2.type == Type.Left){
            return true;
        }
        return false;
/*        switch (t1.type){
            case Plus:
                return t2.type == Type.Plus;
            case Minus:
                return t2.type == Type.Minus;
            default:
                return false;
        }*/
    }
}
//leetcode submit region end(Prohibit modification and deletion)
