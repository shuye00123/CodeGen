package cn.shuye00123.web.CodeGen.entity;

import lombok.Data;

@Data
public class PathEntity {

    private String codeBasePath;

    private String modelPath;

    private String servicesPath;

    private String controllerPath;

    private String repositoryPath;

    private String codeOutPath;

}
