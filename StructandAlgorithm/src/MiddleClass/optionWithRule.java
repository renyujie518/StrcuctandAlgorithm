package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName optionWithRule.java
 * @Description 假设s和m初始化， s = " a" ; m = s;
 * 再定义两种操作
 * 第一种操作： m = s; s = s + s;
 * 第二种操作： s = s + m;
 * 求最小的操作步骤数， 可以将s拼接到长度等于n（比如给个4  结果应该是aaaa）
 * 初始的时候 s = a ;m =a
 * 注意：  目标是s
 * 这是一道纯业务的题  就是考临时分析的能力
 * <p>
 * 结论：
 * 当n为质数的时候  单独完成操作2所需要的步数就是最小步数（只需要调n-1次  因为开始有个a了）
 * 这其实也很好理解  操作1有个2倍，一旦有操作1的参入（第一步除外），后续再有操作1会导致永远不会形成质数
 * <p>
 * n不为质数，假设n由若干个质数因子构成 ，a*b*c....  而且这个顺序是最优的
 * 举个例子 21 = 7*3 = 3*7  到底是先完成7个a在搞出3份  还是反之  不知道哪个最优
 * 但最后一个一定是质数  所以最后一步需要的步数应该是c-1
 * 同理  层层递推
 * <p>
 * 所以问题转化为  寻找n的质数因子 最终结论=sum(寻找到的质数)-n   每找到一个就要-1.所以-n
 * @createTime 2021年07月25日 16:13:00
 */
public class optionWithRule {

    //判断一个数是不是质数
    public static Boolean isZhiShu(int n) {
        if (n < 2) {
            return false;
        }
        //为了节省空间 实际上不用全部遍历
        int max = (int) Math.sqrt((double) n);
        for (int i = 2; i <= max; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //首先n不是质数  返回0号位：所有因子的和但不包括1   返回2号位：所有因子的个数但不包括1
    public static int[] sumAndCount(int n) {
        int sum = 0;
        int count = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                sum += i;
                count++;
                n = n / i;
            }
        }
        return new int[]{sum, count};
    }

    public static int optionWithRule(int n) {
        if (n < 2) {
            return 0;
        }
        if (isZhiShu(n)) {
            return n - 1;
        }
        int[] sumAndCount = sumAndCount(n);
        return sumAndCount[0] - sumAndCount[1];
    }

}
