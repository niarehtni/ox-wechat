package com.oxchains.common;

import java.io.Serializable;
import java.util.List;

public class PageBean<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pageIndex = 1;
	private Integer totalPage = 1;
	private Integer totalCount;
	private List<E> data;
	private Integer pageSize = 10;
    private Integer startIndex = 0;
    private String url;

    public PageBean(int curPage) {
        if (curPage > 0) {
            startIndex = (curPage - 1) * pageSize;
        }
        pageIndex = curPage;
    }

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
        if (totalCount > 0) {
		    totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
		    		: totalCount / pageSize + 1;
        }
		this.totalCount = totalCount;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public Integer getPageSize() {
		return pageSize;
	}

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
