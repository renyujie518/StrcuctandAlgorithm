package facing;

import java.util.HashMap;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RandomPool.java
 * @Description
 * 设计RandomPool结构
 * 【题目】设计一种结构，在该结构中有如下三个功能:
 * insert(key):将某个key加入到该结构，做到不重复加入
 * delete(key):将原本在结构中的某个key移除
 * getRandom():等概率随机返回结构中的任何一个key。
 * 【要求】Insert、delete和getRandom方法的时间复杂度都是O(1)
 *
 * 利用hashmap
 * @createTime 2021年03月20日 20:37:00
 */
public class RandomPool {

    //利用两张hashMap  map1(str->index)  map2(index->str)  注意，这里的index连续,是size的累加 ，这是ramdomGet的重要条件
    public  static  class Pool<K>{
        private HashMap<K,Integer> keyIndexMap;  //用来看之前有没有加过这个key
        private HashMap<Integer,K> indexKeyMap;   //用来标记这个key出现的次数
        private int size;
        //构造函数
        public Pool(){
            this.keyIndexMap = new HashMap<K,Integer>();
            this.indexKeyMap = new HashMap<Integer, K>();
            this.size = 0;   //初始的时候size都为0
        }

        //插入的时候就是两个表都插数据，只不过这两个表key-value相互是倒着的
        public void insert(K key){
            if (!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size++, key);
            }

        }

        //根据size累加出来的index去匹配一个随机值，返回相应的key
        public K getRandom(){
            if (this.size ==0){
                return null;  //如果刚开始什么key都没加到indexKeyMap，返回空
            }
            int randomIndex = (int) (Math.random() * this.size);
            return this.indexKeyMap.get(randomIndex);
        }

        //删除的时候不能直接硬删，这样会导致indexKeyMap很多"洞"，那如果再找随机key可能会有很多null,所以必须保证index连续
        //解决的办法是用indexKeyMap的最后一条记录，也就是size-1的位置，去填这个"洞 "（update），然后删除最后一条记录
        public void delete(K key) {
            if (this.keyIndexMap.containsKey(key)) {
                int deleteIndex = this.keyIndexMap.get(key); //先取出最后一条和对应的index
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey, deleteIndex);//填洞（覆盖更新）
                this.indexKeyMap.put(deleteIndex, lastKey);
                this.keyIndexMap.remove(key);//删除最后一条
                this.indexKeyMap.remove(lastIndex);
            }
        }
    }


    public static void main(String[] args) {
        Pool<String> pool = new Pool<String>();
        pool.insert("111");
        pool.insert("222");
        pool.insert("333");
        pool.insert("444");
        pool.delete("333");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }

}
