package facing;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BingChaji.java
 * @Description 并查集
 * @createTime 2021年03月21日 14:31:00
 */
public class BingChaji {
    //首先设计一种结构，仅仅是把value包裹一层，可以想象画一个圈
    public static class Element<V>{
        public  V value;
        public  Element(V value){
            this.value = value;
        }
    }
     //并查集这个类
    public static class UnionFindSet<V>{
        public  HashMap<V, Element<V>> elementMap;// key 元素 value 包一层的元素
        public HashMap<Element<V>, Element<V>> fatherMap;// key 包一层的元素 value 包一层的元素的父
        public  HashMap<Element<V>, Integer> sizeMap;// key 顶节点 value 顶节点下总共有多少个节点（包括自己）

         //并查集的构造函数 在初始化的时候用户会把所有的样本给你
         public UnionFindSet(List<V> list){
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
             for (V value : list) {  //开始的时候每个元素都包一层，指向自己，都是顶节点(所以只有1个)
                 Element<V> element = new Element<>(value);
                 elementMap.put(value, element);
                 fatherMap.put(element, element);
                 sizeMap.put(element, 1);
             }
        }
         //并查集中一个很重要的步骤就是向上找顶节点(往上的不能再往上)的过程，这其中加入了"扁平化"的优化
         private Element<V> findHead(Element<V> element){
             Stack<Element<V>> path = new Stack<>();
             while (element!=fatherMap.get(element)){//直到找到自己=自己的父  这时候代表element就是顶节点
                 path.push(element);//每往上走一步，就放到栈里一次
                 element = fatherMap.get(element);
             }
             //到这，这个while结束了，此时element就是顶节点
             //这里有个"扁平化"的优化，所谓扁平化，就是把寻找顶节点的这条路径上（已被栈记录）的遍历过的节点，直接指到顶节点element
             //这样避免了并查集中的某条链路径过长，这样union或者issameset就不是O(1)级别，相当于只要遍历过寻head，这些节点就直接指向顶
             while (!path.isEmpty()){
                 fatherMap.put(path.pop(), element);
             }
             return element;
         }

         //是不是在同一并查集里,就是看这两个元素的顶节点是不是同一个
         public  boolean isSameSet(V a,V b){  //用户不管是不是包了层的element,直接输入a,b
             if (elementMap.containsKey(a)&& elementMap.containsKey(b)){
                 //这里就体现了为什么要包一层，实际上就可以想象为注册表，里面有我才考虑判断是不是在同一并查集里
                 return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
             }
             //否则，要么是没注册，要么是不是同一顶节点
             return false;
         }

         //合并  先看个数，少的顶挂在多的底下（少的父是多），然后整理其余的map
         public  void union(V a,V b){
             if (elementMap.containsKey(a) && elementMap.containsKey(b)) {  //注册过才考虑
                 Element<V> aHead = findHead(elementMap.get(a));
                 Element<V> bHead = findHead(elementMap.get(b));
                 if (aHead != bHead) {
                     //只有两个顶节点不一样才需要合并
                     Element<V> big = sizeMap.get(aHead) >= sizeMap.get(bHead) ? aHead : bHead;
                     Element<V> small = big == aHead ? bHead : aHead;
                     fatherMap.put(small, big);//少的父是多
                     sizeMap.put(big, sizeMap.get(aHead) + sizeMap.get(bHead));
                     //别忘了，小的挂在了多的底下，那在并查集里单独的小的就没了，怎么样是没了，其实就是sizeMap里不记录
                     sizeMap.remove(small);
                 }
             }
         }


     }


}
