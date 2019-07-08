package cn.shuye00123.web.CodeGen.componets;

import cn.shuye00123.web.CodeGen.util.StringStuffUtil;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * FreeMarker将字符串转为小驼峰
 */
public class ConvertLowCaseMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        if(list.size() != 1){
            throw new TemplateModelException("Wrong arguments");
        }
        StringModel sm= (StringModel) list.get(0);
        return StringStuffUtil.convertLowCase(sm.getAsString());
    }
}
