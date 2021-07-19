package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName eatGrass.java
 * @Description 牛牛和羊羊都很喜欢青草。今天他们决定玩青草游戏。最初有一个装有n份青草的箱子,牛牛和羊羊依次进行,牛牛先开始。
 * 在每个回合中,每个玩家必须吃一些箱子中的青草,所吃的青草份数必须是4的x次幂,比如1,4,16,64等等。
 * 不能在箱子中吃到有效份数青草的玩家落败。假定牛牛和羊羊都是按照最佳方法进行游戏,请输出胜利者的名字
 * @createTime 2021年03月30日 13:56:00
 */
public class eatGrass {
    //n份青草放在一堆
    //先手和后手的决定都是聪明的  String返回"先手""后手"
    public static String winner1(int n){
        //首先 basecase很好判断 谁先遇到0草，对手赢
        // 0  1  2  3 4
        // 后 先 后 先 先
        if (n<5){
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        //n>5时
        int base = 1;  //先手决定吃的草
        while (base<=n){
            //当前一共n份草，先手吃掉base份，n-base留给后手
            //母过程里 先手，子过程里 后手
            if (winner1(n-base).equals("后手")){//当前是先手，先手先吃base份 ，递归,代表我要用子过程去判定谁赢
                // 如果子过程里显示是后手赢（equals）但这是子过程的后手。就代表母过程先手赢（子过程的后手和母过程的先手一个人）
                return "先手";
            }
            if (base>n / 4){
                break;//防止base*4后溢出，因为base不断*4，可能超过整数最大边界，发生不可预知的错误，所以在bade*4前判断，*4之后不要大于n
            }
            base *= 4;//如果在上面那个if没有的进入，那你就尝试吃4份，16份..去尝试赢
        }
        return "后手";//试了所有的分支，都不能return那只能是后手赢了
    }

    //观察winner1得出的结果
    public static void Winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            System.out.println("yang");
        } else {
            System.out.println("niu");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(i + ":" + winner1(50));
        }
    }


}
