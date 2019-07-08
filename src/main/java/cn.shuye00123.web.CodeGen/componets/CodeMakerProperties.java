package cn.shuye00123.web.CodeGen.componets;

import cn.shuye00123.web.CodeGen.entity.Info;
import cn.shuye00123.web.CodeGen.entity.PathEntity;
import cn.shuye00123.web.CodeGen.entity.StrategyEntity;
import cn.shuye00123.web.CodeGen.entity.TableEntity;
import cn.shuye00123.web.CodeGen.entity.ColumnData;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ToString
@ConfigurationProperties(prefix = "codemaker")
public class CodeMakerProperties {

    private String group;

    private String artifact;

    private PathEntity path;

    private TableEntity table;

    private StrategyEntity strategy;

    private Info info;


    /*私有字段*/
    private List<ColumnData> columnDataList;

    private Integer index;

    private String curEntityName;
}
