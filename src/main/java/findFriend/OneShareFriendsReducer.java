package findFriend;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class OneShareFriendsReducer extends Reducer<Text, Text, Text, Text>{

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        StringBuffer sb = new StringBuffer();
        //1 拼接
        for(Text person: values){
            sb.append(person).append(",");
        }

        //2 写出
        context.write(key, new Text(sb.toString()));
    }
}
