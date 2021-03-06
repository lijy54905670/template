package com.xinyuan.ms.common.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hzx
 * @Description:分页
 * @date 2018/3/2910:14
 */
public class PageUtil<T> implements Page<T> {

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 每页数据
     */
    private Integer pageSize = 5;

    /**
     * 分页集合
     */
    private List<T> list = new ArrayList<T>();


    public PageUtil(List<T> list) {
        this.list = list;
    }

    public PageUtil(Integer pageNum, Integer pageSize, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
        this.size = this.list.size();
        this.totalPageCount = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
    }

    private int size;
    private int totalPageCount;


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public int getTotalPages() {
        return totalPageCount;
    }

    @Override
    public long getTotalElements() {
        return size;
    }


    @Override
    public int getNumberOfElements() {
        return getSize();
    }

    @Override
    public List<T> getContent() {
        List<T> mli = new ArrayList<T>();
        int tPageSize = 0;
        if (pageSize > size) {
            tPageSize = size;
        } else if (pageSize < 1) {
            tPageSize = 1;
        } else {
            tPageSize = pageSize;
        }
        int total = (getNumber() - 1) * tPageSize;
        int qjt = total + tPageSize > size ? size : total + tPageSize;
        for (int i = total; i < qjt; i++) {
            mli.add(list.get(i));
        }

        return mli;
    }

    @Override
    public int getNumber() {
        int pageNumber = totalPageCount;
        int index = pageNum;
        if (pageNum < 0) {
            index = 1;
        } else if (pageNum > pageNumber) {
            index = pageNumber;
        }
        return index;
    }

    @Override
    public int getSize() {
        if (size % pageSize == 0) {
            return pageSize;
        } else {
            if (isLast()) {
                return size % pageSize;
            } else {
                return pageSize;
            }
        }
    }

    @Override
    public boolean hasContent() {
        if (size != 0) {
            return true;
        }
        return false;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public boolean isFirst() {
        int pageNumber = totalPageCount;
        int index = pageNum;
        if (pageNum <= 1 || getNumber() == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isLast() {
        int pageNumber = totalPageCount;
        int index = pageNum;
        if (pageNum >= pageNumber) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasNext() {
        int pageNumber = totalPageCount;
        if (pageNum < pageNumber) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrevious() {
        if (pageNum > 1) {
            return true;
        }
        return false;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return null;
    }
}
