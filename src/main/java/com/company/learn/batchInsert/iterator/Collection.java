package com.company.learn.batchInsert.iterator;

/**
 * 聚集对象集合接口
 */
public interface Collection {
    /**
     * 获取迭代器对象
     * @return
     */
    public Iterator getIterator();

    /**
     * 获取聚集对象的长度
     * @return
     */
    public int size();

    /**
     * 获取指定位置的元素
     * @param i
     * @return
     */
    public Object get(int i);

    /**
     * 增加元素
     * @param value
     * @return
     */
    public void add(Object value);
}
