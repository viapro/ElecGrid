package com.viapro.elec.util;

import java.util.List;

@SuppressWarnings("rawtypes")
public class PageView {

    // 用户指定/配置
    private int currentPage;// 当前页
    private int pageSize;// 每页显示的记录数量

    // 从数据库中查询
    private int recordCount;// 总记录数
    private List recordList;// 本页的数据列表

    // 计算
    private int pageCount;// 总页数
    private int startPageIndex;// 页码列表的开始的索引
    private int endPageIndex;// 页码列表的结束的索引

    public PageView(int currentPage, int pageSize, int recordCount, List recordList) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.recordList = recordList;

        // 计算
        pageCount = (recordCount + pageSize - 1) / pageSize;// 总页数

        // a, 总页码不大于10页
        if (pageCount <= 10) {
            startPageIndex = 1;// 页码列表的开始的索引
            endPageIndex = pageCount;// 页码列表的结束的索引
        } else {
            // b, 总页码大于10页, 显示当前页附近的共10个页码
            startPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;

            // c, 前面不足4个时，显示前10个页码
            if (startPageIndex < 1) {
                startPageIndex = 1;
                endPageIndex = 10;
            }
            // d, 后面不足5个时，显示后10个页码
            else if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                startPageIndex = endPageIndex - 10 + 1;
            }
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List getRecordList() {
        return recordList;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

}
