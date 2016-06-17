package com.anhouse.analyse.util;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapred.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class WholeFileInputFormat extends InputFormat<NullWritable, BytesWritable> {

	protected boolean isSplitable(JobContext context, Path file) {
		return false;
	}

	public RecordReader<NullWritable, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context)
			throws IOException, InterruptedException {
		RecordReader<NullWritable, BytesWritable> reader = new WholeFileRecordReader();
		((WholeFileRecordReader) reader).initialize(split, context);
		return reader;
	}


	@Override
	public List<InputSplit> getSplits(org.apache.hadoop.mapreduce.JobContext arg0)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}


}
