package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ColorLeftRight.java
 * @Description
 * 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
 * 牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
 * 牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近
 * 牛牛想知道他最少需要涂染几个正方形。
 * 如样例所示:s=RGRGR我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
 *
 * 每一个R颜色的左边都没有G颜色  最终的结果是分界线左边都是R，右边都是G
 * RGRGR -> RRRGG
 * 本题的思路有两种
 * 用两层循环，用外循环来控制颜色改变后R和G的分界线，即之前都为R，之后都为G，
 * 然后内循环求将左右两边更换成符合要求的颜色之后，需要多少次更换，每次更换分界线，则更新需要更换的最小值。但这样复杂度是O(n^2)
 *
 * 优化方案：
 * GRRGRG
 * A[1,1,1,2,2,3]
 * B[3,3,2,1,1,0]
 * 设置两个辅助数组
 * A从左往右，统计0-i范围上统计几个G  想0-i上都是R,直接查表A查有几个G全染了
 *B从右往左，统计i-N-1范围上统计几个R  想i-N-1上都是G,直接查表B查有几个R全染了
 * @createTime 2021年03月30日 17:22:00
 */
public class ColorLeftRight {
    // RGRGR -> RRRGG
    public static int minPaint1(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int[] right = new int[chs.length];  //从右往左的数组B 统计i-N-1范围上统计几个R
        right[chs.length - 1] = chs[chs.length - 1] == 'R' ? 1 : 0;//设定数组B的初值
        for (int i = chs.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] + (chs[i] == 'R' ? 1 : 0);
        }
        int res = right[0];  //因为要染色覆盖，极端的情况就是都要把统计的R都染成G，最极端的个数就是B[0]
        int left = 0;
        for (int i = 0; i < chs.length - 1; i++) {
            left += chs[i] == 'G' ? 1 : 0;  //从左往右的数组A,统计0-i范围上统计几个G
            res = Math.min(res, left + right[i + 1]);
        }
        //两个数组构建好了，就不用再循环了，直接从数组中取数即可
        res = Math.min(res, left + (chs[chs.length - 1] == 'G' ? 1 : 0));
        return res;
    }

    public static int minPaint2(String s){
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<s.length();i++)
        {
            int r=0;
            for(int j=0;j<i;j++)
            {
                if(s.charAt(j)!='R')
                    r++;
            }
            for(int k=i;k<s.length();k++)
            {
                if(s.charAt(k)!='G')
                    r++;

            }
            ans=Math.min(ans, r);
        }
        return ans;
    }

    public static void main(String[] args) {
        String test = "RGRGR";
        System.out.println(minPaint1(test));
        System.out.println(minPaint2(test));

    }
}
