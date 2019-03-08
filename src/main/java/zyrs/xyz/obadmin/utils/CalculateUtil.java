package zyrs.xyz.obadmin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2019/2/13.
 *
 * 计算相关工具
 *
 */
public class CalculateUtil{

    //出生日期 得到 年龄
    public static  int getAge(String birthStr){
        //把出生日期字符串转换为日期格式。
        Date birthDay = null;
        try {
            birthDay = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;//表示计算错误！
        }

        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            return -1;//表示计算错误！
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            }else{
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate(String format){
        String date_str="";
        Date dt = new Date();
        //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        date_str=sdf.format(dt);

        return date_str;
    }
}
