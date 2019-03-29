package findFriend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ShareFriendsDriver {
    public static void main(String[] args) throws Exception {

        args = new String[]{"F:\\date\\A\\friends.txt","F:\\date\\A\\friends","F:\\date\\A\\CA"};
        // 1 获取job对象
        Configuration conf1 = new Configuration();
        Configuration conf2 = new Configuration();

        Job job1 = Job.getInstance(conf1);
        Job job2 = Job.getInstance(conf2);

        // 2 指定jar包运行的路径
        job1.setJarByClass(ShareFriendsDriver.class);

        // 3 指定map/reduce使用的类
        job1.setMapperClass(OneShareFriendsMapper.class);
        job1.setReducerClass(OneShareFriendsReducer.class);

        job2.setMapperClass(TwoShareFriendsMapper.class);
        job2.setReducerClass(TwoShareFriendsReducer.class);

        // 4 指定map输出的数据类型
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(Text.class);

        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(Text.class);

        // 5 指定最终输出的数据类型
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);

        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);

        // 6 指定job的输入原始所在目录
        FileInputFormat.setInputPaths(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));

        FileInputFormat.setInputPaths(job2, new Path(args[1]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));

        JobControl andy = new JobControl("Andy");
        ControlledJob ajob = new ControlledJob(job1.getConfiguration());
        ControlledJob bjob = new ControlledJob(job2.getConfiguration());

        //设置作业依赖关系
        bjob.addDependingJob(ajob);

        andy.addJob(ajob);
        andy.addJob(bjob);

        Thread thread = new Thread(andy);
        thread.start();

        while (!andy.allFinished()) {
            thread.sleep(100);
        }
        System.out.println(0000);

    }
}
