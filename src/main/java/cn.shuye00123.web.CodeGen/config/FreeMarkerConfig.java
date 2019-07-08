package cn.shuye00123.web.CodeGen.config;

import cn.shuye00123.web.CodeGen.componets.ConvertLowCaseMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;

    @PostConstruct
    public void  setSharedVariable(){
        configuration.setSharedVariable("convertLowCase",new ConvertLowCaseMethod());
    }

}
