package distinct;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import javax.xml.soap.Text;
import java.io.IOException;

public class DistinctReduce extends Reducer<Text, NullWritable,Text, NullWritable> {
    @Override
    protected void reduce(Text key3, Iterable<NullWritable> v3, Context context) throws IOException, InterruptedException {
        context.write(key3,NullWritable.get());
    }
}
