
package com.platform.common.config;

import com.platform.common.resolver.CustomHandlerExceptionResolver;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.platform.common.interceptor.AuthenticationInterceptor;

/**
 * @author wangying
 */
@Configuration
@EnableWebMvc
public class WebConfig extends AbstractDispatcherServletInitializer implements WebMvcConfigurer {
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new HiddenHttpMethodFilter(), new CharacterEncodingFilter("utf-8", true) };
	}

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/**" };
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * adds XML and Jackson JSON converters with a customized ObjectMapper
	 *
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss am"))
				.modulesToInstall(new ParameterNamesModule());
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.enableContentNegotiation(new MappingJackson2JsonView());
		registry.jsp();
	}
	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(new CustomHandlerExceptionResolver());
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/public", "classpath:/static/")
				.setCachePeriod(31556926);
	}
}
