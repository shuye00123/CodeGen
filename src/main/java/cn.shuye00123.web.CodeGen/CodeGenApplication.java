package cn.shuye00123.web.CodeGen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CodeGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGenApplication.class, args);
    }

}
