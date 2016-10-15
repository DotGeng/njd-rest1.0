package com.njd.rest.service;

import java.util.List;

import com.njd.pojo.TbContent;

public interface ContentService {
	//根据分类id查询出分类内容列表
	public List<TbContent> getContentList(long categoryId) ;
}
