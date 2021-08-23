package facing;

import java.util.*;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName FindWordWithTire.java
 * @Description
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * words = [“oath”,“pea”,“eat”,“rain”] and board =
 * [
 * [‘o’,‘a’,‘a’,‘n’],
 * [‘e’,‘t’,‘a’,‘e’],
 * [‘i’,‘h’,‘k’,‘r’],
 * [‘i’,‘f’,‘l’,‘v’]
 * ]
 *
 * 输出: [“eat”,“oath”]
 * @createTime 2021年08月20日 14:33:00
 */
public class FindWordWithTire {
    public List<String> findWords(char[][] board, String[] words) {
        WordTrie trie = new WordTrie();
        TreeNode root = trie.root;
        Set<String> result = new HashSet<>(); //使用set防止答案中有重复单词
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < words.length; i++){
            trie.insert(words[i]);
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                find(board,i,j,result,root,visited);
            }
        }
        //System.out.print(result);
        return new LinkedList<String>(result);
    }

    public void find(char[][] board, int m, int n, Set<String> result, TreeNode currentNode, boolean[][] visited) {
        if(m >= board.length || m < 0 || n >= board[0].length || n < 0 || visited[m][n]) {
            return;
        }

        currentNode = currentNode.child[board[m][n]-'a'];
        if( currentNode ==null){ //没有找到，回溯
            return;
        }

        if(currentNode.isEnd){ //找到了一个单词
            result.add(currentNode.value);
        }

        visited[m][n] = true;
        //继续寻找后续单词。比如找到了"ad"，可能还会有"advertisement"
        find(board,m+1, n, result, currentNode,visited);
        find(board,m-1, n, result, currentNode,visited);
        find(board,m, n+1, result, currentNode,visited);
        find(board,m, n-1, result, currentNode,visited);

        visited[m][n]=false; //最后回溯
    }

    public class WordTrie {
        public TreeNode root;
        private TreeNode current;

        /** Initialize your data structure here. */
        public WordTrie() {
            root = new TreeNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TreeNode current = root;
            for(int i = 0; i < word.length(); i++){
                if(current.child[word.charAt(i)-'a'] == null) {
                    current.child[word.charAt(i)-'a'] = new TreeNode();
                }
                current = current.child[word.charAt(i)-'a'];
            }
            current.isEnd = true;
            current.value = word;
        }
    }

    public class TreeNode {
        public String value;
        public TreeNode[] child;
        public boolean isEnd;

        public TreeNode() {
            this.child = new TreeNode[26];
            this.isEnd = false;
        }
    }
}
