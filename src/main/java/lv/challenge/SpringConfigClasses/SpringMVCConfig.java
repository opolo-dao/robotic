package lv.challenge.SpringConfigClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lv.challenge.servlets.mvc.interceptors.AddMemberDoublePostIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.util.List;
import java.util.Locale;

/**
 * Created by Daniil on 18.05.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "lv.challenge.servlets.mvc")
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    /*        @Bean
            public ViewResolver viewResolver() {
                InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
                viewResolver.setPrefix("/WEB-INF/jsp/");
                viewResolver.setSuffix(".jsp");
                return viewResolver;
            }*/
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        InternalResourceViewResolver internalViewResolver = new InternalResourceViewResolver();
        internalViewResolver.setPrefix("/WEB-INF/jsp/");
        internalViewResolver.setSuffix(".jsp");
        tilesViewResolver.setOrder(0);
        internalViewResolver.setOrder(1);
        registry.viewResolver(tilesViewResolver);
        registry.viewResolver(internalViewResolver);

    }

  @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getGsonConverter());

    }

    private GsonHttpMessageConverter getGsonConverter() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setGson(gson);

        return gsonConverter;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/html/**").addResourceLocations("/html/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/pictures/**").addResourceLocations("/pictures/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        registry.addResourceHandler("/DataTables/**").addResourceLocations("/DataTables/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        registry.addInterceptor(lci).addPathPatterns("/**");
        registry.addInterceptor(getAddMemberInterceptor()).addPathPatterns("/menu/addmember");
    }


    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("classpath:tiles.xml");
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;

    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        resolver.setCookieName("lang");
        resolver.setCookieMaxAge(4800);
        return resolver;
    }
    @Bean
    public AddMemberDoublePostIntercepter getAddMemberInterceptor(){
        return new AddMemberDoublePostIntercepter();
    }
}
