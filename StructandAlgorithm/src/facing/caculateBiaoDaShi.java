package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName caculateBiaoDaShi.java
 * @Description
 * 简单四则运算
 * 问题描述：
 * 输入一个只包含个位数字的简单四则运算表达式字符串，计算该表达式的值
 * 注
 * 1、表达式只含 +, -, *, / 四则运算符，不含括号
 * 2、表达式数值只包含个位整数(0-9)，且不会出现0作为除数的情况
 * 3、要考虑加减乘除按通常四则运算规定的计算优先级
 * 4、除法用整数除法，即仅保留除法运算结果的整数部分。比如8/3=2。输入表达式保证无0作为除数情况发生
 * 5、输入字符串一定是符合题意合法的表达式，其中只包括数字字符和四则运算符字符，除此之外不含其它任何字符，不会出现计算溢出情况
 * 要求实现函数：
 * 【输出】  无
 * 【返回】  计算结果
 * 示例
 * 1）  输入： “1+4*5-8/3”
 * 函数返回：19
 * 2）  输入： “8/3*3”
 * 函数返回：6
 * @createTime 2021年08月20日 14:02:00
 */
public class caculateBiaoDaShi {

    public static boolean isSymbol (char s)
    {
        if(s=='+'||s=='-'||s=='*'||s=='/')
            return true;
        else
            return false;
    }

    public static int calculate(String str) {
        //数据存储
        char []temp=str.toCharArray();
        int len=temp.length;
        char []symbol=new char[len];
        int []value=new int[len];
        int i=0,j=0;
        for(int k=0;k<temp.length;k++)
        {
            if(isSymbol(temp[k]))//是符号
            {
                symbol[i++]=temp[k];
            }
            else
            {
                StringBuffer buff=new StringBuffer();
                while(k<temp.length&&!isSymbol(temp[k]))//不是符号
                {
                    buff.append(temp[k]);
                    k++;
                }
                value[j++]=Integer.parseInt(new String(buff));
                k--;
            }
        }
        //索引的边界
        i--;
        j--;

		/*  显示处理后的数值数组、符号数组
		for(int temp_i=0;temp_i<=i;temp_i++)
			System.out.print(symbol[temp_i]+" ");
		for(int temp_i=0;temp_i<=j;temp_i++)
			System.out.print(value[temp_i]+" ");
		System.out.println();
		*/

        //先进行* 和/的运算
        for(int s_i=0;s_i<=i;s_i++)//遍历符号表
        {
            if(symbol[s_i]=='*')
            {
                value[s_i+1]=value[s_i]*value[s_i+1];
                value[s_i]=Integer.MAX_VALUE;
                symbol[s_i]='#';
            }
            else if(symbol[s_i]=='/')
            {
                value[s_i+1]=value[s_i]/value[s_i+1];
                value[s_i]=Integer.MAX_VALUE;
                symbol[s_i]='#';
            }
        }

		/*   *和/的运算后的原数组
		for(int temp_i=0;temp_i<=i;temp_i++)
			System.out.print(symbol[temp_i]+" ");
		for(int temp_i=0;temp_i<=j;temp_i++)
			System.out.print(value[temp_i]+" ");
		System.out.println();
		*/

        int update_i = 0, update_j = 0;
        char[] update_symbol = new char[i + 1];
        int[] update_value = new int[j + 1];
        for (int p = 0; p <= i; p++) {

            if (symbol[p] != '#')
                update_symbol[update_i++] = symbol[p];
        }
        for(int p=0;p<=j;p++)
        {
            if(value[p]!=Integer.MAX_VALUE)
                update_value[update_j++]=value[p];
        }
        //索引边界
        update_i--;
        update_j--;

		/*  去除两个数组中的无效元素
		for(int temp_i=0;temp_i<=update_i;temp_i++)
			System.out.print(update_symbol[temp_i]+" ");
		for(int temp_i=0;temp_i<=update_j;temp_i++)
			System.out.print(update_value[temp_i]+" ");
		System.out.println();
		*/

        //后进行+和-的运算
        for(int s_i=0;s_i<=update_i;s_i++)//遍历符号表
        {
            if(update_symbol[s_i]=='+')
            {
                update_value[s_i+1]=update_value[s_i]+update_value[s_i+1];
                //value[s_i]=Integer.MAX_VALUE;
            }
            else if(update_symbol[s_i]=='-')
            {
                update_value[s_i+1]=update_value[s_i]-update_value[s_i+1];
                //value[s_i]=Integer.MAX_VALUE;
            }
        }
        return update_value[update_j];
    }




}
