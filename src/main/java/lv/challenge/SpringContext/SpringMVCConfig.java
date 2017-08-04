package lv.challenge.SpringContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lv.challenge.application.ApplicationService;
import lv.challenge.servlets.mvc.interceptors.AddMemberDoublePostIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * Created by Daniil on 18.05.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "lv.challenge.servlets.mvc")
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

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

    @Autowired
    ApplicationService appService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/html/**").addResourceLocations("file:" + appService.SAVE_PATH + File.separator + "html" + File.separator);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/pictures/**").addResourceLocations("file:" + appService.SAVE_PATH + File.separator + "pictures" + File.separator);
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        registry.addResourceHandler("/DataTables/**").addResourceLocations("/DataTables/");
        registry.addResourceHandler("/ckeditor/**").addResourceLocations("/ckeditor/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        registry.addInterceptor(lci).addPathPatterns("/**");
        registry.addInterceptor(getAddMemberInterceptor()).addPathPatterns("/menu/addmember");
    }

    @Bean
    public TilesConfigurer tilesConfigurer() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Embedded tomcat purpose only
        /*if (JspFactory.getDefaultFactory() == null) {
            JspFactory.setDefaultFactory((JspFactory) Class.forName("org.apache.jasper.runtime.JspFactoryImpl").newInstance());
        }*/
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("classpath:tiles.xml");
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;

    }

    @Bean
    public StandardServletMultipartResolver resolver() {
        return new StandardServletMultipartResolver();
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
    public AddMemberDoublePostIntercepter getAddMemberInterceptor() {
        return new AddMemberDoublePostIntercepter();
    }

}
