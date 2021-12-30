package com.jettech.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class UserController {

	@PostMapping("selectAll")
	@ResponseBody
	public int selectAll(@RequestBody Map<String, Object> map) {
		int number = (int) map.get("number");
		int count = number+2345;
		System.out.println("数据库中的数据总条数为:" + count);
		return count;
	}

	/**
	 * 查询数据总数
	 * @return
	 */
	@RequestMapping(value = "totalCount")
	public String selectTotalCount() {
		int totalCount = 123456;
		System.out.println("总条数为：" + totalCount);
		return "success";
	}

}
