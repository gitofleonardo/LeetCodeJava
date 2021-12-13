package cn.huangchengxi.leetcode.java.leetcode.editor.cn;//现有函数 printNumber 可以用一个整数参数调用，并输出该整数到控制台。
//
// 
// 例如，调用 printNumber(7) 将会输出 7 到控制台。 
// 
//
// 给你类 ZeroEvenOdd 的一个实例，该类中有三个函数：zero、even 和 odd 。ZeroEvenOdd 的相同实例将会传递给三个不同线程：
// 
//
// 
// 线程 A：调用 zero() ，只输出 0 
// 线程 B：调用 even() ，只输出偶数 
// 线程 C：调用 odd() ，只输出奇数 
// 
//
// 修改给出的类，以输出序列 "010203040506..." ，其中序列的长度必须为 2n 。 
//
// 实现 ZeroEvenOdd 类： 
//
// 
// ZeroEvenOdd(int n) 用数字 n 初始化对象，表示需要输出的数。 
// void zero(printNumber) 调用 printNumber 以输出一个 0 。 
// void even(printNumber) 调用printNumber 以输出偶数。 
// void odd(printNumber) 调用 printNumber 以输出奇数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出："0102"
//解释：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出："0102030405"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 
// Related Topics 多线程 👍 119 👎 0


import java.util.function.IntConsumer;

//leetcode submit region begin(Prohibit modification and deletion)
class ZeroEvenOdd {
    private int n;
    private final Object mEvenLock = new Object();
    private final Object mOddLock = new Object();
    private final Object mZeroLock = new Object();
    private volatile int mCurrInt = 0;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (mCurrInt < n){
            synchronized (mZeroLock){
                printNumber.accept(0);
                ++mCurrInt;
                if (mCurrInt % 2 == 0){
                    synchronized (mEvenLock){
                        mEvenLock.notify();
                    }
                }else{
                    synchronized (mOddLock){
                        mOddLock.notify();
                    }
                }
                mZeroLock.wait();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        final boolean isEven = n % 2 == 0;
        final int shouldStopN = isEven ? n : n - 1;
        while (mCurrInt <= shouldStopN){
            synchronized (mEvenLock){
                mEvenLock.wait();
                printNumber.accept(mCurrInt);
                synchronized (mZeroLock){
                    mZeroLock.notify();
                }
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        final boolean isOdd = n % 2 != 0;
        final int shouldStopN = isOdd ? n : n - 1;
        while (mCurrInt <= shouldStopN){
            synchronized (mOddLock){
                mOddLock.wait();
                printNumber.accept(mCurrInt);
                synchronized (mZeroLock){
                    mZeroLock.notify();
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
