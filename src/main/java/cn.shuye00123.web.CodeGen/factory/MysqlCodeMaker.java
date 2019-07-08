package cn.shuye00123.web.CodeGen.factory;

import cn.shuye00123.web.CodeGen.componets.CodeMakerProperties;
import cn.shuye00123.web.CodeGen.entity.ColumnData;
import cn.shuye00123.web.CodeGen.util.CommParseUtil;
import cn.shuye00123.web.CodeGen.util.StringStuffUtil;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MysqlCodeMaker {

    private static List<ColumnData> columnDataList=null;

    @Autowired
    private CodeMakerProperties codeMakerProperties;

    @Autowired
    private DSLContext context;


    public void make(){
        System.out.println("CodeMaker started!");

        System.out.println(codeMakerProperties.toString());
        if(codeMakerProperties.getTable().getTableNameList()==null||codeMakerProperties.getTable().getTableNameList().size()<1){
            System.out.println("CodeMaker error: table name is null,please set it!");
            return;
        }

        for (int i=0;i<codeMakerProperties.getTable().getTableNameList().size();i++){

             columnDataList = context.resultQuery(
                    "select column_name                         colName,\n" +
                    "       data_type                           jdbcType,\n" +
                    "       character_maximum_length            maxLength,\n" +
                    "       column_comment                      colComment,\n" +
                    "       is_nullable                         nullable,\n" +
                    "       column_default                      colDefault,\n"+
                    "       if(column_key = 'PRI', true, false) pkflag\n" +
                    "from information_schema.columns\n" +
                    "where table_name = '"+codeMakerProperties.getTable().getTableNameList().get(i).trim()+"'").fetchInto(ColumnData.class);

            if(columnDataList!=null&&columnDataList.size()>0){
                for (ColumnData columnData: columnDataList){
                    columnData.setJavaType(StringStuffUtil.transDbtypeToJavatype(columnData.getJdbcType()));
                }

                //region 生成MVC

                codeMakerProperties.setColumnDataList(columnDataList);
                codeMakerProperties.setIndex(i);

                String entityName="";
                if(codeMakerProperties.getTable().getEntityNameList()!=null&&codeMakerProperties.getTable().getEntityNameList().size()>0){
                    entityName=codeMakerProperties.getTable().getEntityNameList().get(i);
                    if(entityName.equalsIgnoreCase("_default")){
                        entityName=StringStuffUtil.convertLowCase(codeMakerProperties.getTable().getTableNameList().get(i));
                    }
                }else {
                    entityName=StringStuffUtil.convertLowCase(codeMakerProperties.getTable().getTableNameList().get(i));
                }

                if(Strings.isNullOrEmpty(entityName)){
                    System.out.println("CodeMaker error: entityName create fail,please check entityNameList!");
                    return;
                }
                codeMakerProperties.setCurEntityName(entityName);

                //实体类
                CommParseUtil.parseContent(codeMakerProperties,"EntityTemplateForJOOQ.ftl",
                        codeMakerProperties.getArtifact()+"\\"+codeMakerProperties.getPath().getModelPath()+"\\"+entityName+"Form.java");
                //JOOQ CURD类
                CommParseUtil.parseContent(codeMakerProperties,"MapperTemplateForJOOQ.ftl",
                        codeMakerProperties.getArtifact()+"\\"+codeMakerProperties.getPath().getRepositoryPath()+"\\"+entityName+"Repository.java");
                //5.生成Service层(service接口/实现类)
                if(codeMakerProperties.getStrategy().isServiceInterface()){
                    CommParseUtil.parseContent(codeMakerProperties,"ServiceTemplateForJOOQ.ftl",
                            codeMakerProperties.getArtifact()+"\\"+codeMakerProperties.getPath().getServicesPath()+"\\"+entityName+"Service.java");
                }
                CommParseUtil.parseContent(codeMakerProperties,"ServiceImplTemplateForJOOQ.ftl",
                        codeMakerProperties.getArtifact()+"\\"+codeMakerProperties.getPath().getServicesPath()+"\\impl\\"+entityName
                                +(codeMakerProperties.getStrategy().isServiceInterface()?"ServiceImpl.java":"Service.java"));
                //生成Controller for jooq
                if(codeMakerProperties.getStrategy().isRestcontoller()) {
                    CommParseUtil.parseContent(codeMakerProperties, "RestControllerTemplateForJOOQ.ftl",
                            codeMakerProperties.getArtifact() + "\\" + codeMakerProperties.getPath().getControllerPath() + "\\" + entityName + "Controller.java");
                }
                //endregion

            }else {
                System.out.println("CodeMaker error: Can't find any tables,please check table name!");
            }

        }


        System.out.println("CodeMaker done!");
    }
}
