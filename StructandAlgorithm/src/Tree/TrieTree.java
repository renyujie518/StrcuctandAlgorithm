package Tree;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TrieTree.java
 * @Description 前缀树  给定一个单词  构建前缀树 判断最大公共前缀
 * 注意  节点不是关键 可以看做是"占坑"的  关键是节点间的路代表了每个字母，节点上的path和end属性很有用
 * 比如 01(path=1)_a_02(path=1)  o2代表以a开头的字符串有一个
 * @createTime 2021年03月16日 13:39:00
 */
public class TrieTree {
    public static class TrieNode {
        public int path;  //走过该节点的次数（前缀可以通过这个path得出）
        public int end; //在该节点上有几次停留
        public TrieNode[] nexts;//Hashmap <char,Node> nexts;
        //next[0] ==null 没有走向'a'的路      next[0] !=null 有走向'a'的路


        //节点的构造函数
        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];   //最多26个字母
        }
    }

    public static class Trie{
        //前缀树的构造函数
        private TrieNode root;     //root先固定下来
        public Trie(){
            root = new TrieNode();
        }

        //建立前缀树的过程
        public void insert(String word){
            if (word == null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;  //root可以看做是一个虚节点
            node.path++;//一旦要开始在root上挂东西，延展路了，就先++，这样root上的path = 输入单词的字母个数
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index  = chs[i]- 'a'; //这里减去'a'的ASIC,这样a的index = 0,b的index = 1，以此类推 （把字符转换成对应走哪条路）
                if (node.nexts[index] == null){//循环的时候，如果路下面没有节点，就新建，如果有，就复用，直接移到next上即可
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];  //总结就是 有节点直接移动下一个（向下延伸），没有的话新建一个再移动
                node.path++;//每延伸一次，沿途节点属性上的path++
            }
            //for循环结束后在尾节点end++
            node.end++;
        }

        //word单词之前加入过几次(insert的逆过程)
        public int search(String word){
            if (word == null){
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';  //把字符a,b,c..转换成对应的0，1，2，3..
                if (node.nexts[index] == null){  //下一个点没有对应上（成null）了，就返回0
                    // 比如有个路是abc,你要查abcd,这个路没有（只要中间断了），返回0
                    return 0;
                }
                node = node.nexts[index];
            }
            //走到最后的end就是加入的次数
            return node.end;
        }

        //删除一条路  就是把沿途节点上的path--,到最后一个节点了，再end--
        public void delete(String word){
            if (search(word)!=0){ //先查一遍，有这条路才可能继续删除
                char[] chs = word.toCharArray();
                TrieNode node =root;
                node.path--; //先--
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if (--node.nexts[index].path == 0){
                        node.nexts[index] = null;   //在path先-后发现节点上的path值已经为0，那就把后面的都断开，都设置为null后return
                        return;
                    }
                    node = node.nexts[index];
                }
                //所有的都循环完，node到底了，要记得把end--
                node.end--;
            }
        }

        //所有加入的字符串中，有几个是以'pre'为前缀的
        public int preNums(String pre){
            if (pre == null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';  //把字符a,b,c..转换成对应的0，1，2，3..
                if (node.nexts[index] == null){  //下一个点没有对应上（成null）了，就返回0
                    // 比如有个路是abc,你要查前缀是abcd,这个路没有（只要中间断了），返回0
                    return 0;
                }
                node = node.nexts[index];
            }
            //走到最后的path就是以pre为前缀的路的个数
            return node.path;
        }

    }
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("zuo"));  //0
        trie.insert("zuo");
        System.out.println(trie.search("zuo"));//1
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));  //0
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo")); //1
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));//=
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa"));//0
        System.out.println(trie.preNums("zuo")); //3

    }






}
