package com.shun.blog.model.common;

import java.util.List;
import lombok.Data;

@Data
public class AjaxResult {
	private String result;
	private String errorMsg;
	private List<Object> resultList;
}
