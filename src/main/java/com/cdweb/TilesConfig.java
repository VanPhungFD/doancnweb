package com.cdweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfig {
	
	
	@Bean("viewResolver")
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}

	@Bean("tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions("/WEB-INF/web.xml","/WEB-INF/admin-web.xml");
		return configurer;
	}

}
