package com.njd.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njd.mapper.TbContentMapper;
import com.njd.rest.service.ContentService;
import com.njd.pojo.TbContent;
import com.njd.pojo.TbContentExample;
import com.njd.pojo.TbContentExample.Criteria;
@Service
public class ContentServiceImpl implements ContentService{
	@Autowired
	private TbContentMapper contentMapper;
	@Override
	public List<TbContent> getContentList(long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
	}

}
