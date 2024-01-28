package com.pratap;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.pratap")
@EnableWebMvc
public class AppServletConfig implements WebMvcConfigurer{

	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/jsps/", ".jsp");
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/WEB-INF/studentmessages");
		return ms;
	}
	
	
	
	// I18N support
	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		// The LocaleChangeInterceptor works with LocaleResolver object
		lci.setParamName("sitelanguage");
		registry.addInterceptor(lci);
		
		ThemeChangeInterceptor tci = new ThemeChangeInterceptor();
		// The ThemeChangeInterceptor works with ThemeResolver Object
		tci.setParamName("siteTheme");
		registry.addInterceptor(tci);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");	
	}
	
	
	
	// Theme support
	@Bean
	public CookieThemeResolver themeResolver() {
		CookieThemeResolver resolver = new CookieThemeResolver();
		// The ThemeResolver works with ThemeSource object
		resolver.setDefaultThemeName("green");
		return resolver;
	}
	
	@Bean
	public ResourceBundleThemeSource themeSource() {
		ResourceBundleThemeSource ts = new ResourceBundleThemeSource();
		ts.setBasenamePrefix("theme-");
		return ts;
	}
	
	

}
