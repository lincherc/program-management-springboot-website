package com.springboot.programmanage.springbootwebapp;

import com.springboot.programmanage.springbootwebapp.api.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private LocationService locationService;

    @Autowired
    public WebMvcConfig(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有访问图片路径都映射到相应路径下
        String storeHouse=locationService.getStoreHouse();
        registry.addResourceHandler("/programstorehouse/image/**").addResourceLocations("file:"+storeHouse+"/image/");
    }
}
