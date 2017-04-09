package br.com.casadocodigo.loja;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.models.CarrinhoCompras;

@Configuration
@EnableCaching
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean(name = "carrinhoCompras")
	public CarrinhoCompras carrinhoCompras() {
		CarrinhoCompras carrinhoCompras = new CarrinhoCompras();
		return carrinhoCompras;
	}

	@Bean
	public FormattingConversionService mvConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}

	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5,
				TimeUnit.MINUTES);
		GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
		guavaCacheManager.setCacheBuilder(cacheBuilder);
		return guavaCacheManager;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("locale/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	// Change Locale of Application
	public LocaleResolver localeResolver() {
	    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
	    cookieLocaleResolver.setDefaultLocale(new Locale("pt", "BR"));
	    return cookieLocaleResolver;
	}
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }

}
