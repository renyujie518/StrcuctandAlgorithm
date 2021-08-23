package facing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName jiaqi.java
 * @Description 腾讯面试题
 * 由于业绩优秀，公司给小Q放了 n 天的假，身为工作狂的小Q打算在在假期中工作、锻炼或者休息。
 * 他有个奇怪的习惯：不会连续两天工作或锻炼。只有当公司营业时，小Q才能去工作，只有当健身房营业时，小Q才能去健身，小Q一天只能干一件事。
 * 给出假期中公司，健身房的营业情况，求小Q最少需要休息几天。
 *
 * 输入:
 * 输入描述第ー行一个数表示放假天数
 * 第二行n个数句个数为0或者1,第i个数表示公司在第i天是否营业
 * 第三行n个数每个数为0或者1,第i个数表示健身房在第i天是否营业
 * （1为营业0为不营业)
 * 输出：
 * 表示小Q休息的最少天数
 *
 * 思路：
 * 使用动态规划进行求解，dp[i][0]、dp[i][1]、dp[i][2]分别表示第i天休息、健身、工作时，累积到今天的最少休息天数
 * 得到以下三种情况的状态转移方程：
 * 1.假如第i天健身房开门且选择健身，健身可以从休息和工作两个状态转移过来，因为今天选择了截健身，所以止到今天的累积休息天数并不会增加
 * dp[i][1] = min(dp[i - 1][0], dp[i - 1][2])
 * 2.假如第i天单位开门且选择工作，工作可以从休息和健身两个状态转移过来，因为今天选择了工作，所以截止到今天的累积休息天数并不会增加
 * dp[i][2] = min(d[i - 1][0], dp[i - 1][1])
 * 3.假如第i天休息，休息可以从休息、健身和工作三个状态转移过来，因此截止到今天的累积休息天数会增加一天
 * dp[i][0] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + 1
 * @createTime 2021年08月20日 11:51:00
 */
public class jiaqi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] work = new int[n];
            for (int i = 0; i < n; i++) {
                work[i] = sc.nextInt();
            }
            int[] gym = new int[n];
            for (int i = 0; i < n; i++) {
                gym[i] = sc.nextInt();
            }
            System.out.println(minDay(n, work, gym));
        }
    }


    public static int minDay(int n,int[] work, int[] gym) {
        // dp[i][0],dp[i][1],dp[i][2]分别表示第i天 休息/锻炼/工作 累计的最小休息天数
        int[][] dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                //初始化
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i = 1; i <= n; i++){
            // 今天健身房开门，锻炼从另外两种状态转移过来
            if(gym[i - 1] == 1)
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]);
            // 今天单位开门，工作从另外两种状态转移过来
            if(work[i - 1] == 1)
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]);
            // 休息可以从自身或另外两种状态转移而来
            dp[i][0] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + 1;
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

    //这里粘贴读取输入的另一种写法
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine().trim());
//        String[] strIsWorking = br.readLine().trim().split(" ");
//        String[] strIsGym = br.readLine().trim().split(" ");
//        int[] isWorking = new int[n];
//        int[] isGym = new int[n];
//        for(int i = 0; i < n; i++){
//            isWorking[i] = Integer.parseInt(strIsWorking[i]);
//            isGym[i] = Integer.parseInt(strIsGym[i]);
//        }
//        // dp[i][0],dp[i][1],dp[i][2]分别表示第i天 休息/锻炼/工作 累计的最小休息天数
//        int[][] dp = new int[n + 1][3];
//        for(int i = 1; i <= n; i++)
//            for(int j = 0; j < 3; j++) dp[i][j] = Integer.MAX_VALUE;
//        for(int i = 1; i <= n; i++){
//            // 今天健身房开门，锻炼从另外两种状态转移过来
//            if(isGym[i - 1] == 1)
//                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]);
//            // 今天单位开门，工作从另外两种状态转移过来
//            if(isWorking[i - 1] == 1)
//                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]);
//            // 休息可以从自身或另外两种状态转移而来
//            dp[i][0] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + 1;
//        }
//        System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
//    }


}
