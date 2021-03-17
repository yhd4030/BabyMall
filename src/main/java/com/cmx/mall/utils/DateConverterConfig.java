package com.cmx.mall.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DateConverterConfig implements Converter<String, Date> {

    private static final List<String> formarts = new ArrayList<>(4);

    private static final String YYYY_MM = "yyyy-MM";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    static{
        formarts.add(YYYY_MM);
        formarts.add(YYYY_MM_DD);
        formarts.add(YYYY_MM_DD_HH_MM);
        formarts.add(YYYY_MM_DD_HH_MM_SS);
    }

    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        if(source.matches("^\\d{4}-\\d{1,2}$")){
            return parseDate(source, formarts.get(0));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, formarts.get(1));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(2));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(3));
        }else {
            throw new IllegalArgumentException("Invalid false value '" + source + "'");
        }
    }

    /**
     * 格式化日期
     * @param dateStr String 字符型日期
     * @param format String 格式
     * @return Date 日期
     */
    private  Date parseDate(String dateStr, String format) {
        Date date;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
        return date;
    }



}
