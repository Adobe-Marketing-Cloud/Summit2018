package com.adobe.acs.summit2018.lab730.backend.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class MdcFilter implements Filter {
	
	private static final String REQUEST_ID = "requestId";
	
	private static final Logger LOG = LoggerFactory.getLogger(MdcFilter.class);
	

	@Override
	public void destroy() {
		// do nothing
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String id = request.getHeader(REQUEST_ID);
		if (! StringUtils.isEmpty(id)) {
			MDC.put(REQUEST_ID, id);
			if (id.length() > 8) {
				LOG.warn("length of header field {} exceeds 8 characters, logging is incomplete (requestId = '{}')",REQUEST_ID,id);
			}
		}
		chain.doFilter(request, response);
		MDC.clear();
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// do nothing
		
	}

}
