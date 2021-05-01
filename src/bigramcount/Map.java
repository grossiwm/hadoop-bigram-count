package bigramcount;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import utils.MapperUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Map extends Mapper<LongWritable, Text, Text, IntWritable>{
	public void map(LongWritable key, Text value, Context context) throws
	IOException, InterruptedException{
		
		String linha = value.toString();

		List<String> listaBigramas = MapperUtils.obtemBigramasDeLinha(linha);
		
	    for (String bigrama : listaBigramas) {
	    	context.write(new Text(bigrama), new IntWritable(1));
	    }
	}
} 
