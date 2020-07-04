package cn.guyu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * @Des 下载数据文件
 * @Author guyu
 * @Date 2020/7/4 22:02
 * @Param 
 * @Return 
 */
public class HDFSTest01 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        URI uri= new URI("hdfs://192.168.244.201");
        Configuration conf = new Configuration();
        conf.set("dfs.blocksize","128m");
        FileSystem hdfs= FileSystem.get(uri,conf);
        
        //判断文件文件夹是否存在
        Path dir=new Path("/data");
        if(hdfs.exists(dir)){
            System.out.println("data文件夹存在将进行删除");
            hdfs.delete(dir,true);
        }else{
            System.out.println("文件夹不存在");
        }

        //上传数据
        if(!hdfs.exists(new Path("/logs"))){
            hdfs.mkdirs(new Path("/logs"));
        }
        hdfs.copyFromLocalFile(new Path("C:\\Users\\30747\\Desktop\\test.txt"),new Path("/logs"));
        System.out.println("上传成功");

        //下载数据
        if(hdfs.exists(new Path("/logs/test.txt"))){
            hdfs.copyToLocalFile(false,new Path("/logs/test.txt"),new Path("download.txt"),true);
            System.out.println("下载成功!");
        }else{
            System.out.println("文件不存在，无法下载");
        }

        hdfs.close();
    }
}
