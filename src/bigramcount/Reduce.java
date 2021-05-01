package bigramcount;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, IntWritable, Text, LongWritable>{
	public void reduce(Text key, 
			Iterable<IntWritable> values,
			Context context) throws
	IOException, InterruptedException {
		long count = 0;
		for(IntWritable val: values) {
			count++;
		}
		
		Configuration conf = context.getConfiguration();
		Long minimumCount = conf.getLong("contMin", 0L);
		
		if (count > minimumCount)
			context.write(key, new LongWritable(count));
	}
}