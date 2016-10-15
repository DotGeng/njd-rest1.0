package com.njd.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.njd.common.util.JsonUtils;
import com.njd.rest.pojo.CatResult;
import com.njd.rest.service.itemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private itemCatService itemCatService;
	@RequestMapping(value="itemcat/all",produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		List list = new ArrayList<>();
		//因为前台不显示了，所以这里把list的长度截断了些
		for(int i = 0 ; i < 14 ; i ++) {
			list.add(catResult.getData().get(i));
		}
		catResult.setData(list);
		//将对象转化为json数据
		String json = JsonUtils.objectToJson(catResult);
		StringBuffer result  = new StringBuffer(callback + "");
		result.append("(");
		result.append(json);
		result.append(");");
		return result.toString();
		
	}
}
