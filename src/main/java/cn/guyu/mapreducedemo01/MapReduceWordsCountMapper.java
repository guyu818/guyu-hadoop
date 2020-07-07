package cn.guyu.mapreducedemo01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;

/**
 * @Des 并行计算mapper
 * @Author guyu
 * @Date 2020/7/7 9:28
 * @Param 
 * @Return 
 */
public class MapReduceWordsCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {



    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //重写这个函数，这里面的代码你说了算，其他的基本上不用动
        //一个value就是一行
        String line=value.toString();
        //空格分开
        String[] words = line.split(" ");
        //转换类型，一个单词对应1
        for (String word : words) {
            Text wordKey =new Text(word);
            IntWritable wordVal = new IntWritable(1);
            context.write(wordKey,wordVal);
        }
    }
    public static void main(String[] args) {


    }
}
