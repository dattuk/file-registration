package com.finra.registration.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;
/**
 * Created by dattukothapalli on 1/27/18.
 */

@SpringBootApplication
public class FileRegistrationApplication extends WebMvcConfigurerAdapter {
    public static void main( String[] args )
    {
        setJVMProperties();
        SpringApplication.run(FileRegistrationApplication.class, args);
    }

    // 1. set ALLOW_ENCODED_SLASH property to true to allow encoded slash in request mapping
    private static void setJVMProperties() {
        System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
        System.setProperty("https.protocols", "SSLv3,TLSv1");
    }

    // 2. extend WebMvcConfigurerAdapter to override configurePathMatch
    // 3. set UrlDecorder to false
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setUrlDecode(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
