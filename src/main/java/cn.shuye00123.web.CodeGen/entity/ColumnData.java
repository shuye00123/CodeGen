package cn.shuye00123.web.CodeGen.entity;

import lombok.Data;

@Data
public class ColumnData {

    private String colName; //列名
    private String jdbcType; //数据库字段类型
    private String javaType; //对应java类型
    private String colComment; //列名注释
    private String maxLength; //最大长度
    private String colDefault;//默认值
    private String nullable;  //是否为空
    private boolean pkflag;//是否主键

}
