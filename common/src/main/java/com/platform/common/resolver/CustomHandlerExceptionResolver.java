package com.platform.common.resolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.platform.common.exception.UniqueException;

import lombok.extern.slf4j.Slf4j;

/**
 * Globally handle custom exceptions
 * @author wangying Created on 2019/10/11.
 */
@Slf4j
public class CustomHandlerExceptionResolver extends DefaultHandlerExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			//Globally handle UniqueException
			if (ex instanceof UniqueException) {
				Map<String, Object> modelMap = new HashMap<>(2);
				modelMap.put("success", false);
				modelMap.put("message", ((UniqueException) ex).getErrorMessage());
				return new ModelAndView().addAllObjects(modelMap);
			} else {
				return super.doResolveException(request, response, handler, ex);
			}
		} catch (Exception var6) {
			log.warn("Failure while trying to resolve exception [" + ex.getClass().getName() + "]", var6);
		}
		return null;
	}
}
