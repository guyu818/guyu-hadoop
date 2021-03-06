package cn.guyu.mapreducedemo01;

import cn.guyu.mapreducedemo01.MapReduceWordsCountMapper;
import cn.guyu.mapreducedemo01.MapReduceWordsCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Des mapreduce driver 作业入口
 * @Author guyu
 * @Date 2020/7/7 9:15
 * @Param 
 * @Return 
 */
public class MapReduceWordsCount {
     static void judgeFileExist(URI uri,Configuration configuration,Path path) throws IOException {
        FileSystem fileSystem = FileSystem.get(uri,configuration);
        if (fileSystem.exists(path)){
            System.out.println("文件夹存在");
            fileSystem.delete(path,true);
            System.out.println("删除成功");
        }else {
            System.out.println("文件不存在");
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Configuration configuration = new Configuration();
        Job job = new Job(configuration, "这里写作业名字");
        //定义启动器
        job.setJarByClass(MapReduceWordsCount.class);
        //定义mapper处理类
        job.setMapperClass(MapReduceWordsCountMapper.class);
        //定义reducer处理类
        job.setReducerClass(MapReduceWordsCountReducer.class);

        //设置输出的key-value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入输出路径
        //这里输入的路径如果给的是一个文件夹的话，那么他会将文件夹中的数据全部传入进去
        FileInputFormat.addInputPath(job, new Path("hdfs://192.168.244.201/data/words.dict"));
        URI uri = new URI("hdfs://192.168.244.201/");
        MapReduceWordsCount.judgeFileExist(uri,configuration,new Path("/out"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.244.201/out"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }


}
