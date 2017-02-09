package com.company.learn.batchInsert.iterator;

/**
 * 迭代器接口
 */
public interface Iterator {
    /**
     * 获取前一个对象
     * @return
     */
    public Object previous();

    /**
     * 获取后一个对象
     * @return
     */
    public Object next();

    /**
     * 是否存在下一个对象
     * @return
     */
    public boolean hasNext();

    /**
     * 获取第一个对象
     * @return
     */
    public Object first();

    /**
     * 判断聚集集合是否为null
     * @return
     */
    public void isNull();
}
