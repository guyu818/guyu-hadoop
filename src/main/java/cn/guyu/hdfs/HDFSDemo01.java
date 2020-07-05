package cn.guyu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @Des HDFS 操作示例1
 * @Author guyu
 * @Date 2020/7/5 19:27
 * @Param 
 * @Return 
 */
public class HDFSDemo01 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        //获取hdfs的配置文件，如core-site.xml,等
        Configuration configuration = new Configuration();
        //可以直接这样设置块大小，因为配置文件里都是xml文件中key-value形式存储的
        //如果修改的地方多的话，可以直接整一个xml文件
        //这里设置了块大小为128
        configuration.set("dfs.blocksize","128m");

        URI uri = new URI("hdfs://192.168.244.201/");
        //单例模式获取实例，适合同步的时候使用
        FileSystem fileSystem = FileSystem.get(uri,configuration);
        //下面的不是单例模式，适合异步的时候使用
//        FileSystem fileSystem1 = FileSystem.newInstance();

        //下面就可以进行文件操作了
        //1.删除操作
        //判断有无这个文件夹
        Path path = new Path("/deletetest");
        if (fileSystem.exists(path)){
            System.out.println("文件存在");
            fileSystem.delete(path,true);
        }else {
            System.out.println("文件不存在");
        }
        //2.上传文件
        //本地文件路径
        Path src = new Path("C:\\Users\\asus\\Desktop\\1.txt");
        //远程文件夹
        Path dst = new Path("/guyudata");
        if (!fileSystem.exists(dst)) {
            fileSystem.mkdirs(dst);
        }else {
            fileSystem.copyFromLocalFile(src,dst);
            System.out.println("上传文件成功");
        }

        //3.下载文件
        //本地文件路径
        Path dst1 = new Path("d:/1.txt");
        //远程文件夹
        Path src1 = new Path("/guyudata/1.txt");
        if (!fileSystem.exists(src1)) {
            System.out.println("文件不存在");
        }else {
            //**************************提示：前后参数false 和 true 一定加上不然的话会报错。
            fileSystem.copyToLocalFile(false,src1,dst1,true);
            System.out.println("文件下载成功");
        }


        //最后别忘了关闭
        fileSystem.close();

    }

}
