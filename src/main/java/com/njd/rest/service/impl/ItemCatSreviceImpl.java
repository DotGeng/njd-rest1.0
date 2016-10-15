package com.njd.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njd.mapper.TbItemCatMapper;
import com.njd.rest.pojo.CatNode;
import com.njd.rest.pojo.CatResult;
import com.njd.rest.service.itemCatService;
import com.njd.pojo.TbItemCat;
import com.njd.pojo.TbItemCatExample;
import com.njd.pojo.TbItemCatExample.Criteria;

@Service
public class ItemCatSreviceImpl implements itemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		//查询商品列表
		catResult.setData(getCatList(0));
		return catResult;
	}
	public List<?> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//初始化一个容器，用来存放data数据
		//这个list后边的泛型为啥不能加？
		List catResult = new ArrayList<>();
 		List<TbItemCat> list = itemCatMapper.selectByExample(example);
 		for (TbItemCat tbItemCat : list) {
			//如果是父节点
 			if(tbItemCat.getIsParent()) {
 				CatNode catNode = new CatNode();
 				if(tbItemCat.getParentId() == 0) {
 					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
 				}else {
 					catNode.setName(tbItemCat.getName());
 				}
 				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
 				catNode.setItem(getCatList(tbItemCat.getId()));
 				catResult.add(catNode);
 				//如果是叶子节点
 			}else {
 				catResult.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName()+"");
 			}
		}
		return catResult;
	}
}
