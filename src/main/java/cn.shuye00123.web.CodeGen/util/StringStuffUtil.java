package cn.shuye00123.web.CodeGen.util;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 封装了一些常用处理方法
 * Created by HJK on 2017/1/3.
 */
public class StringStuffUtil {

    /**
     * 将数据库类型转为java类型
     * @param dataType 数据库中数据类型
     * @return
     */
    public static String transDbtypeToJavatype(String dataType) {

        Map<String, String> dataMap =
                new ImmutableMap.Builder<String, String>().
                        put("int", "Integer").
                        put("float", "Float").
                        put("double", "Double").
                        put("number", "long").
                        put("decimal", "BigDecimal").
                        put("datetime", "LocalDateTime").
                        put("timestamp", "LocalDateTime").
                        put("date", "LocalDate").
                        put("time", "LocalTime").
                        put("blob", "Blob").
                        put("clob", "Clob").build();

        String javaType=dataMap.get(dataType);
        return Strings.isNullOrEmpty(javaType)?"String":javaType;
    }

    /**
     * 转化字符为小驼峰,去除下划线,特殊字符后一位默认大写
     * @param origin
     * @return
     */
    public static String convertLowCase(String origin){
        if(Strings.isNullOrEmpty(origin)){
            origin="";
        }
        StringBuilder returnValue = new StringBuilder();
        String temp =origin.trim();
        boolean isUpCase=true;
        for (char c : temp.toCharArray()) {
            if(isUpCase){
                c = Character.toUpperCase(c);
            }
            if (Character.isAlphabetic(c)|| Character.isDigit(c)) {
                isUpCase=false;
                returnValue.append(c);
            }else {
                isUpCase=true;
            }
        }
        return  returnValue.toString();
    }

    public static void main(String[] args) {
        System.out.println(StringStuffUtil.convertLowCase("Sd34cv_343+33ddSS"));
        System.out.println(StringStuffUtil.convertLowCase("user_addr"));
        System.out.println(StringStuffUtil.convertLowCase("subscribe_scene"));
    }
}
