package cn.guyu.mapreducedemo01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @Des 聚合计算reducer
 * @Author guyu
 * @Date 2020/7/7 9:28
 * @Param 
 * @Return 
 */
public class MapReduceWordsCountReducer extends Reducer<Text, IntWritable,Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //这里的代码动就行，其他的基本上不用动
        int count = 0;
        Iterator<IntWritable> iterator=values.iterator();
        while (iterator.hasNext()){
            count += iterator.next().get();
        }
        context.write(key,new IntWritable(count));
    }
}
