package cn.guyu.mapreducedemo02;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Des 并行计算mapper
 * @Author guyu
 * @Date 2020/7/7 9:28
 * @Param 
 * @Return 
 */
public class VideoCountMapper extends Mapper<LongWritable, Text,Text,Text> {

    private final static int CELL_NUM=8;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] row = line.split("\t");
        if(row.length == CELL_NUM){
            //老师	北工大综合实践	小明 17080111	无	无	0:15:31	0:0:19	0:15:50
            String name = row[2]; //小明
            String time = row[7]; //0:15:50

            context.write(new Text(name), new Text(time));
        }


    }
    public static void main(String[] args) {


    }
}
