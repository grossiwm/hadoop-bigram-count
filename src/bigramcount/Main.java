package bigramcount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Main extends Configured implements Tool{
	
	@Override
	public int run(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		
		Long contagemMinima = Long.parseLong(args[0]);
		
		conf.setLong("contMin", contagemMinima);
		
		Job job = Job.getInstance(conf);
		job.setJobName("bigramcount");
		job.setJarByClass(Main.class);
		
		job.setMapOutputValueClass(IntWritable.class);
		job.setMapOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		job.setOutputKeyClass(Text.class);
		
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		
		Path inputFilePath = new Path(args[1]);
		Path outputFilePath = new Path(args[2]);
		
		FileInputFormat.addInputPath(job, inputFilePath);
		FileOutputFormat.setOutputPath(job, outputFilePath);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}	
	
	public static void main(String[] args) throws Exception,
	InterruptedException {
		int exitCode = ToolRunner.run(new Main(), args);
		System.exit(exitCode);
	}

}