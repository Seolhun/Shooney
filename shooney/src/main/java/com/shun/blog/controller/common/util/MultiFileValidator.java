package com.shun.blog.controller.common.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.shun.blog.model.file.FileBucket;
import com.shun.blog.model.file.MultiFileBucket;

@Component
public class MultiFileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MultiFileBucket.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		MultiFileBucket multiBuket = (MultiFileBucket) obj;

		int index = 0;
		for (FileBucket file : multiBuket.getFiles()) {
			if (file.getFile() != null) {
				if (file.getFile().getSize() == 0) {
					errors.reject("files[" + index + "].file", "Missing file");
				}
			}
			index++;
		}
	}
}
