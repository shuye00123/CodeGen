package cn.shuye00123.web.CodeGen.util;

import cn.shuye00123.web.CodeGen.componets.CodeMakerProperties;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;

/**
 * 通用FreeMarker替换模板方法
 * @author HJK
 *
 */
public class CommParseUtil {


    /**
     * 创建文档
     * @param objData 携带数据
     * @param templateName 文档模板名称
     * @param destPath  生成目标目录
     */
	public static void parseContent(CodeMakerProperties objData, String templateName, String destPath){
        try {
	        //创建配置实例 
	        Configuration configuration = new Configuration();
	        //设置编码
            configuration.setDefaultEncoding("utf-8");
            //加载模板
            configuration.setClassForTemplateLoading(CommParseUtil.class,"/templates/");
            //获取模板 
            Template template = configuration.getTemplate(templateName);
            //输出文件
            File outFile = new File(objData.getPath().getCodeOutPath()+destPath);
            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
            //将模板和数据模型合并生成文件 
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
            //生成文件
            template.process(objData, out,new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build());
            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
