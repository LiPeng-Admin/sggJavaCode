package com.atguigu.java2;

import org.junit.Test;

import java.util.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MapTest.java
 * @Description Map框架结构
 * @createTime 2022年04月12日 12:33:00
 */
/*一：
* |---Map：双列数据，存储key-value对的数据
*   |---HashMap:作为Map的主要实现类；线程不安全的，效率高；可以存储null的key和value
*
*       |---LinkedHashMap:保证在遍历map元素时，可以按照添加的顺序实现遍历（原因：在原有的HashMap 底层结构基础上，
*                         添加了一对指针，指向前一个和后一个）
*                         对于频繁的遍历操作，此类执行效率高于HashMap
*
*   |---TreeMap：保证按照添加的key-value对进行排序，实现排序遍历，此时考虑key的自然排序或定制排序
*                底层使用红黑树
*
*   |---Hashtable：作为Map的古老实现类；线程安全的，效率低;不能存储null的key和value
*       |---Properties:常用来处理配置文件.key-value都是String 类型
*
*  HashMap的底层：数组+链表（jdk7及之前）
*               数组+链表+红黑树（jdk 8）
*
* 二：map结构的理解
* map的key:无序，不可重复的，使用Set存储所有的key--->key 所在的类要重写equals()和hashCode()(以HashMap为例)
* map中的value:无序，可重复的，使用Collection 存储所有的value--<value 所在的类要重写equals()
* 一个键值对：key-value 构成了一个Entry对象
* map的entry：无序，不可重复的，使用Set 存储所有的entry
*
* 三：HashMap的底层实现原理
    * HashMap map=new HashMap();
    * 在实例化以后，底层创建了长度是16 的一维数组Entry[] table
    * ...可能已经执行过多次put...
    *  map.put(key1,value1);
    * 首先，调用key1所在类的hashCode(),计算key1哈希值，此哈希值经过某种算法以后，得到在entry[]的存放位置
    * 如果此位置上的数据为空，则key1-value1 添加成功   ---情况1
    * 如果此位置上的数据不为空（此位置存在一个或多个数据（以链表的形式存在）），比较key1和已经存在的一个或多个数据
    * 的哈希值：
    *       如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1 添加成功  ---情况2
    *       如果key1的哈希值与已经存在的某一个数据（key2-value2）的哈希值相同，继续比较：调用key1 所在类的equals(key2)方法,比较
    *          如果equals()返回false,则此时key1-value1添加成功   ------情况3
    *          如果equals()返回true,使用value1替换value2
    *
    *   补充：关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储
    *
    * 在不断添加的过程中，会涉及到扩容问题，默认的扩容方式：扩容为原来的2倍，并将原有的数据复制过来
    *
   jdk8相较于jdk7在底层实现方面的不同
    * 1：new HashMap();底层没哟创建一个长度为16的数组
    * 2:JDK8底层的数组 是:Node[],而非Entry[]
    * 3:首次调用put()方法时，底层创建长度为16的数组
    * 4：jdk7底层结构只有：数组+链表，jdk8底层结构：数组+链表+红黑树
    *    当数组的某一个索引位置上的元素以链表形式存在的数据个数>8且当前数组长度>64,
    *    此时此索引位置上的所有数据改为使用红黑树存储
    *
    *
 4  HashMap底层典型属性的属性的说明：

        DEFAULT_INITIAL_CAPACITY : HashMap的默认容量，16
        DEFAULT_LOAD_FACTOR：HashMap的默认加载因子：0.75
        threshold：扩容的临界值，= 容量*填充因子：16 * 0.75 => 12
        TREEIFY_THRESHOLD：Bucket中链表长度大于该默认值，转化为红黑树:JDK 8.0引入
        MIN_TREEIFY_CAPACITY：桶中的Node被树化时最小的hash表容量:64
        *
四：LinkedHashMap的底层实现原理(了解)
    * LinkedHashMap底层使用的结构与HashMap相同，因为LinkedHashMap继承于HashMap.
    区别就在于：LinkedHashMap内部提供了Entry，替换HashMap中的Node.
    与Linkedhash Set类似，LinkedHashMap可以维护Map的迭代顺序：迭代顺序与Key-value对的插入顺序一致
    *
        HashMap中内部类Node源码：
        static class Node<K,V> implements Map.Entry<K,V>{
            final int hash;
            final K key;
            V value;
            Node<K,V> next;
        }
        *
        LinkedHashM中内部类Entry源码：
        static class Entry<K,V> extends HashMap.Node<K,V> {
            Entry<K,V> before, after;//能够记录添加的元素的先后顺序
            Entry(int hash, K key, V value, Node<K,V> next) {
                super(hash, key, value, next);
            }
        }
        *
五：map 中定义的方法
  ①添加、删除、修改操作：

    Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
    void putAll(Map m):将m中的所有key-value对存放到当前map中
    Object remove(Object key)：移除指定key的key-value对，并返回value
    void clear()：清空当前map中的所有数据
  ②元素查询的操作：
  *
  * Object get(Object key)：获取指定key对应的value
    boolean containsKey(Object key)：是否包含指定的key
    boolean containsValue(Object value)：是否包含指定的value
    int size()：返回map中key-value对的个数
    boolean isEmpty()：判断当前map是否为空
    boolean equals(Object obj)：判断当前map和参数对象obj是否相等
    *
  ③元视图操作的方法：
  *
    Set keySet()：返回所有key构成的Set集合
    Collection values()：返回所有value构成的Collection集合
    Set entrySet()：返回所有key-value对构成的Set集合
    *

总结：常用方法：

    添加：put(Object key,Object value)
    删除：remove(Object key)
    修改：put(Object key,Object value)
    查询：get(Object key)
    长度：size()
    遍历：keySet() / values() / entrySet()


* 面试题：
* 1：HashMap的底层实现原理？
* 2：HashMap与Hashtable的异同？
*
*
*
* */
public class MapTest {
    @Test
    public void test1() {
        HashMap map = new HashMap();
//        map.put(null,123);
        map.put(null, null);
        System.out.println(map);

    }

    @Test
    public void test2() {
        Map map = new LinkedHashMap();
        map.put(123, "AA");
        map.put(456, "BB");
        map.put(789, "CC");
        System.out.println(map);
    }


    /*
    ①添加、删除、修改操作：

    Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
    void putAll(Map m):将m中的所有key-value对存放到当前map中
    Object remove(Object key)：移除指定key的key-value对，并返回value
    void clear()：清空当前map中的所有数据
     */

    @Test
    public void test3() {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 456);
        map.put("CC", 789);
        map.put("AA", 12);
        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("DD", "father");
        map1.put("EE", "mother");

        map.putAll(map1);
        System.out.println(map);

        //remove(Object key)
        Object dd = map.remove("DD");
        System.out.println(dd);
        System.out.println(map);

        //clear():不同于map=null
        map.clear();
        System.out.println(map.size());

    }

    @Test
    public void test4() {
//        ②元素查询的操作：
//  *
//  *     Object get(Object key)：获取指定key对应的value
//        boolean containsKey(Object key)：是否包含指定的key
//        boolean containsValue(Object value)：是否包含指定的value
//        int size()：返回map中key-value对的个数
//        boolean isEmpty()：判断当前map是否为空
//        boolean equals(Object obj)：判断当前map和参数对象obj是否相等

        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 456);
        map.put("CC", 789);
        map.put("DD", 123);

        //Object get(Object key)：获取指定key对应的value
        System.out.println(map.get("DD"));

        //boolean containsKey(Object key)：是否包含指定的key
        boolean bb = map.containsKey("BB");
        System.out.println(bb);//true

        boolean b = map.containsValue(123);
        System.out.println(b);//true

        map.clear();
        System.out.println(map.isEmpty());//true

    }

    /*
    *
     ③元视图操作的方法：
  *
    Set keySet()：返回所有key构成的Set集合
    Collection values()：返回所有value构成的Collection集合
    Set entrySet()：返回所有key-value对构成的Set集合
    *
    * */
    @Test
    public void test5() {
        Map map = new HashMap();
        map.put("AA",123);
        map.put("BB",456);
        map.put("CC",789);
        map.put(45,"DD");
        map.put(null,"EE");

   //Set keySet()：返回所有key构成的Set集合
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("*******************");

        //Collection values()：返回所有value构成的Collection集合
        Collection values = map.values();

        for (Object o:values) {
            System.out.println(o);
        }
        System.out.println("******************");

        // Set entrySet()：返回所有key-value对构成的Set集合
        Set set1 = map.entrySet();
        Iterator iterator2 = set1.iterator();
        while (iterator2.hasNext()){
            Object obj = iterator2.next();
            Map.Entry entry=(Map.Entry)obj;
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }


    }


}
