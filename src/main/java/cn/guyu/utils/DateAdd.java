package cn.guyu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Des 时间相加工具类
 * @Author guyu
 * @Date 2020/7/7 14:47
 * @Param 
 * @Return 
 */
public class DateAdd {

    /**
     * 时间分钟数相差计算
     * @param endTime
     * @param dateformat
     * @return
     * @throws ParseException
     */
    public static long dateAdd(String endTime,String startTime,String dateformat) throws ParseException {
        //脏数据过滤，这里条件自己可以换成其他的。
        if (endTime == "无"){
            return 0;
        }
        //按照一定的格式解析字符串成date类型
        SimpleDateFormat format = new SimpleDateFormat(dateformat);//如"yyyy-MM-dd"
        Date date1 = format.parse(endTime);
        Date date2 = format.parse(startTime);
        //需要Java8 环境 报红的话alt+enter 选择第一个就行，自动修复
        long minutes = ChronoUnit.MINUTES.between(Instant.ofEpochMilli(date2.getTime()), Instant.ofEpochMilli(date1.getTime()));
        //返回分钟数
        return minutes;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(DateAdd.dateAdd("1:15:50","00:00:00", "HH:mm:ss"));
    }
}
