package distinct;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DistinctMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    @Override
    protected void map(LongWritable key1, Text value1, Context context) throws IOException, InterruptedException {
        String data = value1.toString();
        String[] words = data.split(",");

        context.write(new Text(words[2]),NullWritable.get());


    }

}
