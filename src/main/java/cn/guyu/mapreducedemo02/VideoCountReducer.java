package cn.guyu.mapreducedemo02;

import cn.guyu.utils.DateAdd;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

/**
 * @Des 聚合计算reducer
 * @Author guyu
 * @Date 2020/7/7 9:28
 * @Param 
 * @Return 
 */
public class VideoCountReducer extends Reducer<Text, Text,Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //这里的代码动就行，其他的基本上不用动
        //小明       <0:15:50, 1:15:50, 0:55:50> 处理的格式
        Iterator<Text> iterator = values.iterator();
        long minutes=0;
        while(iterator.hasNext()){
            try {
                minutes += DateAdd.dateAdd(iterator.next().toString(),"00:00:00","HH:ss:mm");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        context.write(key, new Text(String.valueOf(minutes)+"分钟"));

    }
}
