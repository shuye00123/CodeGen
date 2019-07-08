package cn.shuye00123.web.CodeGen.config;

import cn.shuye00123.web.CodeGen.factory.MysqlCodeMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public MysqlCodeMaker getCodeMarker(){
        return new MysqlCodeMaker();
    }
}
