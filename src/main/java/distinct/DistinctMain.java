package distinct;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class DistinctMain {
    public static void main(String[] args) {
        try {
            Job job = Job.getInstance(new Configuration());
            job.setJarByClass(DistinctMain.class);

            //指定任务的map和map的输出数据类型
            job.setMapperClass(DistinctMapper.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            //指定任务reduce和reduce输出类型
            job.setReducerClass(DistinctReduce.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            //执行任务
            job.waitForCompletion(true);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

