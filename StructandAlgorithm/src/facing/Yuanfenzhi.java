package facing;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Yuanfenzhi.java
 * @Description TODO
 * @createTime 2021年03月21日 16:01:00
 */
    /**
     * @author admin
     * @version 1.0.0
     * @ClassName code1.java
     * @Description
     * (携程笔试 3.19  通过率50%)
     * 为了寻找最佳拍档，我们定义两人名字的缘分值：两人名字左对齐后，对应位置字的拼音的缘分值之和。
     * 对于两个拼音s1、s2，通过剔除一些字符使得留下的子串一模一样，被剔除字符之和的最小值即为两个拼音的缘分值。
     * 求给定两人名字的缘分值。
     * 输入描述
     * 输入数据有2组，每行一个名字，名字各字间用空格分隔。2<=字的拼音长度<=10，2<=名字字数<=20
     *
     * 输出描述
     * 对于每个测试实例，要求输出缘分值 ; 每个测试实例的输出占一行
     *
     *
     * 样例输入
     * Zhang San
     * Zhan Ai
     * 样例输出
     * 563
     *
     * 提示
     * 字符之和是指对应的ASCII值之和。样例中，Zhang和Zhan去掉‘g’后，
     * 剩余子串相等，因此第一个字的缘分值为asc(‘g’)=103；而San和Ai则需全部删除San和Ai，
     * 其缘分值为asc(“San”)+asc(“Ai”)=290+170=460。因此Zhang San和Zhan Ai的缘分值为103+460=563。
     * 边界场景，一人名字较长者，譬如，Ali Ba Ba和Xie Cheng，那最后一个Ba需要全部剔除掉。
     * @createTime 2021年03月12日 17:07:00
     */
    class Solution {
        static int calcSimilarity(String name1, String name2,int sum) {
            String[] s1 = name1.split(" ");
            String[] s2 = name2.split(" ");
            int n1 = s1.length;
            int n2 = s2.length;
            //边界场景，一人名字较长者,最后一个需要全部剔除掉。留下较小长度
            int useMinLength = n1<n2 ? n1 :n2;
            //int useMaxLength = n1<n2 ? n2:n1;
            //较大长度的先拿出来算缘分值
            if (n1>n2){
                for (int i = n2+1; i <= n1; i++) {
                    sum += findOneAsc(s1[i]);
                }
            }else if (n2>n1){
                for (int i = n1+1; i <= n2; i++) {
                    sum += findOneAsc(s2[i]);
                }
            }
            //取出较小的长度的数组的长度，Ali Ba Ba和Xie Cheng，此时useMinLength = 2

            //按照这个较小的长度重新建立两个字符串数组
            String[] ss1 = new String[useMinLength];
            String[] ss2 = new String[useMinLength];
            for(int i = 0;i<useMinLength;i++){
                ss1[i] = s1[i];
                ss2[i] = s2[i];

            }
            //这时候得到的ss1和ss2里的元素都是取的个数小的为主，Ali Ba Ba和Xie Cheng ->Ali Ba和Xie Cheng
            //这时候的思路是写一个功能函数，顺序遍历，将两个字符串的相同部分放到一个新的数组，再求不同部分的累加缘分值,比如zhang zhan
            //转化为ASCII int c = (int)'a';  转回来 char a = (char)c;  注意区分大小写
            for (int i = 0; i < useMinLength; i++) {
                sum += findNosameAsc(s1[i], s2[i]);
            }
            return sum;
        }
        public static  int findNosameAsc(String s1,String s2){
            if (s1 == null ||s2==null ||s1.length()==0||s2.length()==0 || " ".equals(s1)||" ".equals(s2)){
                return 0;
            }
            int minLength = Math.min(s1.length(), s2.length());
            int maxLength = Math.max(s1.length(), s2.length());
            int sum = 0;

            //s.subString 左闭右开 （s.subString(i,j)删右边，s.subString(i+1,j+1)删左边）
            //这里的思路是分别计算两个字符串各自的ASCII值，相等返回0，不相等有两种情况
            //相差过大，返回两者的和，相差一个（int）'z',返回两者的差值
//        int s1Asc = findOneAsc(s1);
//        int s2ASc = findOneAsc(s2);
//        if (s1Asc == s2ASc){
//            return 0;
//        }else if (Math.abs((s2ASc-s1Asc))<(int)'z'){
//            return Math.abs((s2ASc-s1Asc));
//        }else {
//            return s1Asc+s2ASc;
//        }

            ArrayList<Character> list = new ArrayList<>();
            for (int i = 0; i < minLength; i++) {
                if (s1.charAt(i) != s2.charAt(i)){
                    list.add(s1.charAt(i));
                    list.add(s2.charAt(i));
                }
            }
            for (Character character : list) {
                sum +=findOneAsc(character.toString());
            }
            if (s1.length()>s2.length()){
                String substring = s1.substring(minLength);
                sum += findOneAsc(substring);
            }else if (s1.length()<s2.length()){
                String substring = s2.substring(minLength);
                sum += findOneAsc(substring);
            }
            return sum;

        }

        //计算单个字符串的缘分值
        public static  int findOneAsc(String s){
            int sum = 0;
            char[] chars = s.toCharArray();
            for (char subChar : chars) {
                sum+=(int)subChar;
            }
            return sum;
        }

        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            while (in.hasNextLine()) {
                String name1 = in.nextLine();
                String name2 = in.nextLine();

                int sum = calcSimilarity(name1, name2,0);
                System.out.println(sum);
            }
        }


    }


