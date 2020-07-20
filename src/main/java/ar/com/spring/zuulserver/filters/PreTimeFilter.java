package ar.com.spring.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTimeFilter extends ZuulFilter{

	private static final Logger LOGGER = LoggerFactory.getLogger(PreTimeFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		Long startTime = System.currentTimeMillis();
		request.setAttribute("startime", startTime);
		LOGGER.info(String.format("%s request enrutado a %s", request.getMethod(),request.getRequestURL()));
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		
		return 1;
	}

}
