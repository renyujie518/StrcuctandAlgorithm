package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName minBags.java
 * @Description 小虎去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装包装不可拆分。
 * 可是小虎现在只想购买恰好n个苹果，
 * 小虎想购买尽量少的袋数方便携带。如果不能购买恰好n个苹果，小虎将不会购买。
 * 输入一个整数n，表示小虎想购买的个苹果，返回最小使用多少袋子。如果无论如何都不能正好装下，返回-1
 *
 * 注意 所有的袋子必须装满 不能少装，都要刚刚好
 * 思路：首先 奇数个的苹果无法分开
 * 先用8袋子去装 剩余的看能不能用6装
 * 注意 一旦剩余的超过24个（6，8的最小公倍数）说明无论怎么分配都不行
 * @createTime 2021年03月30日 11:12:00
 */
public class minBags {
    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;  //一上来先用6类型袋子-1个
        int bag8 = apple / 8;  //先用8袋子去装
        int rest = apple - 8 * bag8;  //剩余的个数
        while (bag8 >= 0 && rest < 24) {  //一旦剩余的不超过24个，去尝试6类型的袋子,而且下面的代码有--bag8，所以bag8 >= 0
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;  //break的是while
            }
            //如果restUse6是-1，代表上面的if没中，这时候代表剩余的东西能不能被6整除，调整8袋子的个数，匀一些出来给6袋子，再去试while
            rest = apple - 8 * (--bag8);
        }

        //bag6初值是-1，如果跑完所有的while,if都没中，bag6没有被赋值改变，代表无论怎么分配都不行，返回-1
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    //如果剩余的东西能恰好被6整除 返回需要6袋子的个数  否则返回-1
    public static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }


    //这个是用打表法做出来的，其实就是找规律，运行注释的代码发现在apple数大于18之后呈现规律性
    //18:3
    //19:-1
    //20:3
    //21:-1
    //22:3
    //23:-1
    //24:3
    //25:-1
    //这种完全就是寻找规律出来的
    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) {
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int apple = 1; apple <100 ; apple++) {
            System.out.println(apple + ":" + minBags(apple));
        }
//
//        int max = Integer.MAX_VALUE;
//        int testTime = 100000000;
//        for (int test = 0; test < testTime; test++) {
//            int apple = (int) (Math.random() * max);
//            if (minBags(apple) != minBagAwesome(apple)) {
//                System.out.println("error");
//            }
//        }

    }
}
