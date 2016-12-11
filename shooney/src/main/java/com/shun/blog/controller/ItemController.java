package com.shun.blog.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shun.blog.controller.common.CommonFn;
import com.shun.blog.model.portfolio.item.ItemData;
import com.shun.blog.model.portfolio.item.Monster;
import com.shun.blog.service.portfolio.item.ItemService;

@Controller
@RequestMapping("/")
public class ItemController {

	@Autowired
	ItemService itemService;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CommonFn commonFn;

	@RequestMapping(value = "/po/item/list", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String listItems(ModelMap model) throws IOException {
		ArrayList<Monster> items = (ArrayList<Monster>) itemService.findAllItems();
		//Json형식의 데이터 파싱하기.
		ObjectMapper mapper = commonFn.setJSONMapper();
		for (int i = 0; i < items.size(); i++) {
			String jsonData = items.get(i).getData();
			ItemData itemdata = mapper.readValue(jsonData, ItemData.class);
			items.get(i).setItemData(itemdata);
		}
		
//		Json에 데이터 넣기
//		ArrayList<Integer> itemKeys = (ArrayList<Integer>) itemdata.getBoss().getTreasure().get(0).getItem();
//		itemdata.getBoss().getTreasure().get(0).getItem().add(1211);
//		itemdata.getBoss().getTreasure().get(0).getItem().add(1212);
//		itemdata.getBoss().getTreasure().get(0).getItem().add(1214);
//		itemdata.getBoss().getTreasure().get(0).getItem().add(1215);
//		String dtoAsString = commonFn.getJSONData(itemdata);
//		model.addAttribute("dtoAsString", dtoAsString);
		
		model.addAttribute("items", items);
		return "item/itemlist";
	}

	@RequestMapping(value = "/po/item/newitem", method = RequestMethod.GET)
	public String newItem(ModelMap model, HttpServletResponse res) {
		Monster item= new Monster();
		model.addAttribute("item", item);
		model.addAttribute("edit", false);
		return "item/newitem";
	}
}