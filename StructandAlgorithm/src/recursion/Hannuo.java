package recursion;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Hannuo.java
 * @Description 汉诺塔  只能小压大
 * @createTime 2021年03月18日 13:35:00
 */
public class Hannuo {
    //1-i的圆盘（i越大，圆盘越大） 目标时from ->to other是另外一个柱子   !!每个子问题都保证自己的底满足规则即可
    public static void func(int i,String start,String end,String other){
        if (i == 1){//base case   只剩下最小的盘 直接放就可以
            System.out.println("移动 1 号盘，从"+start+"到"+end);
        }else {
            func(i-1,start,other,end);//先把i-1（大盘子i的上一个）放到辅助柱子上
            System.out.println("移动"+i+" 号盘，从"+start+"到"+end);//第i-1的先搁置一边，这时候代表压在(i-1)下的大盘子i可以正确的放到目标柱子
            func(i - 1, other, end, start);//再把i-1从辅助柱子移到目标柱子。这时候满足规则，只能小压大

        }
    }

    public static void hannuo(int n){
        if (n>0){
            func(n,"左","右","中");
        }
    }

    public static void main(String[] args) {
        int n =3;
        hannuo(3);
    }

}
