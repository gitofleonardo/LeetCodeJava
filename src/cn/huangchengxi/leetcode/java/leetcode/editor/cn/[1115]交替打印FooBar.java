//我们提供一个类： 
//
// 
//class FooBar {
//  public void foo() {
//    for (int i = 0; i < n; i++) {
//      print("foo");
//    }
//  }
//
//  public void bar() {
//    for (int i = 0; i < n; i++) {
//      print("bar");
//    }
//  }
//}
// 
//
// 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。 
//
// 请设计修改程序，以确保 "foobar" 被输出 n 次。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 1
//输出: "foobar"
//解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
// 
//
// 示例 2: 
//
// 
//输入: n = 2
//输出: "foobarfoobar"
//解释: "foobar" 将被输出两次。
// 
// Related Topics 多线程 👍 134 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class FooBar {
    private int n;
    private final Object mLock = new Object();
    private volatile boolean mPrintFoo = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while (!mPrintFoo){
                synchronized (mLock){
                    mLock.wait();
                }
            }
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            mPrintFoo = false;
            synchronized (mLock){
                mLock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while (mPrintFoo){
                synchronized (mLock){
                    mLock.wait();
                }
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
            mPrintFoo = true;
            synchronized (mLock){
                mLock.notify();
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
