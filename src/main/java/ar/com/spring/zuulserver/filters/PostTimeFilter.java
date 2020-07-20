package ar.com.spring.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimeFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostTimeFilter.class);

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public Object run() throws ZuulException {	
		LOGGER.info("Entrando al filtro....");
		Long endTime = System.currentTimeMillis();
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		Long startTime = (Long)request.getAttribute("startime");
		Long totalTime = endTime - startTime;
		LOGGER.info(String.format("Total de tiempo transcurrido %s segundos", totalTime.doubleValue()/1000.00));
		return null;
	}

	@Override
	public String filterType() {		
		return "post";
	}

	@Override
	public int filterOrder() {		
		return 2;
	}

}
