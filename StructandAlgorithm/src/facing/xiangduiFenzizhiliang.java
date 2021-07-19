package facing;

import java.util.Scanner;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName code1.java
 * @Description  3.27 京东面试题
 * 单组输入。
 *
 * 输入一行，包含一个只包含字母C、H、O、N和数字的分子式，如果字母后面没有数字则表示该原子只出现1次。
 * 在一个分子式中，允许一个元素在不同位置出现多次。分子式的长度不超过100个字符。
 * char[] fenzishi = shuru.toCharArray();
 *         LinkedList<Character> list  = new LinkedList<>();  //用于存储用过的字母或数字
 *         for (char c : fenzishi) {
 *             list.add(c);
 *         }
 *         int len = list.size(); //输入字符的总长度
 *         HashMap<Character, Integer> map = new HashMap<>();  //保存原子的相对原子质量
 *        while (!list.isEmpty()){
 *            Character curr = list.poll();
 *            Character curr_next = list.poll();
 *            //Character curr_next_next = list.poll();
 *
 *            if (Character.isUpperCase(curr)){//如果遇到一个大写字母，说明发现一个新原子
 *                map.put(curr, 1);
 *                if (Character.isDigit(curr_next)){ //如果大写字母后跟着数字，说明找到了这个原子的个数
 *                    map.put(curr, curr_next-'0');
 * //                   if (Character.isDigit(curr_next_next )){//如果数字后跟着数字，说明找到了这个原子的个数要跟新
 * //                       map.put(curr, (Integer.valueOf(curr_next)*10)+Integer.valueOf(curr_next_next));
 * //                   }
 *                }
 *            }
 *
 *        }
 *
 *         for (char c : map.keySet()){
 *             System.out.print(c);
 *             System.out.println();
 *             System.out.print(map.get(c));
 *
 *         }
 *         //至此map构建完成
 *         Set<Character> maps = map.keySet();
 *         for (int i = 0; i < maps.size(); i++) {
 *             if (map.containsKey('C')){
 *                 sum += 12 * map.get('C');
 *             }
 *             if (map.containsKey('H')){
 *                 sum += 1 * map.get('H');
 *             }
 *             if (map.containsKey('O')){
 *                 sum += 16 * map.get('O');
 *             }
 *             if (map.containsKey('N')){
 *                 sum += 14 * map.get('N');
 *             }
 *
 *         }
 *
 *         System.out.print(sum);
 *
 *
 *
 *
 *
 *           String temp = "";  //设置一个空字符
 *         int sum = 1;
 *         for (int i = shuru.length()-1;i>0;i--){  //倒着来，如果是CH按HC考虑，如果是C2H2按2H2C考虑
 *             char curr = shuru.charAt(i);
 *             if (curr>='0' && curr<='9'){ //如果是数字
 *                 temp = temp + curr;
 *             } else {//不是数字就是字母
 *                 if (temp.equals("")){  //如果没有数字去改变这个temp,说明出现的是单独的 比如CH
 *                     sum = 1;
 *
 *                 }else { //如果有数字去改变这个temp,说明出现类似C2H2这样的
 *                     sum = Integer.parseInt(temp);
 *                 }
 *                 total = f(curr) * sum;
 *                 temp = "";//算完后清零
 *
 *             }
 *         }
 *         System.out.println(total);
 *     }
 *         public static int f(char no) {
 *             //H(1)，C(12)，N(14)，O(16)
 *             int i=0;
 *             switch(no){
 *                 case 'H':
 *                     i=1;
 *                     break;
 *                 case 'C':
 *                     i= 12;
 *                     break;
 *                 case 'N':
 *                     i=14;
 *                     break;
 *                 case 'O':
 *                     i= 16;
 *                     break;
 *                 default:
 *                     break;
 *             }
 *             return i;
 *         }
 */

public class xiangduiFenzizhiliang {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s= scanner.next();
        int i = 0;
        int c = 0, h = 0, o = 0, n = 0;  //各原子的个数
        while (i < s.length()) {
            char ch = s.charAt(i);
            int v = 0;
            if (i + 1 >= s.length() || (s.charAt(i + 1) < '0' || s.charAt(i + 1) > '9')) { //是字母的话
                i++;
                v = 1;
            } else {
                i++;
                while (i < s.length() && (s.charAt(i) <= '9' && s.charAt(i) >= '0')) {
                    v = v * 10 + s.charAt(i) - '0'; //每出现一个数子，比如C12 上面那个while到1了，i++,到2，1*10+2 - '0'
                    // 一旦不是数字就退出while
                    i++;
                }
            }
            if (ch == 'C') {
                c += v;
            }
            if (ch == 'H') {
                h += v;
            }
            if (ch == 'O') {
                o += v;
            }
            if (ch == 'N') {
                n += v;
            }
        }
        long ans = c * 12 + h + o * 16 + n * 14;
        System.out.println(ans);
    }
}
