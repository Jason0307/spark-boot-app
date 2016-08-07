package org.zhubao.spark.bootapp.job;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function2;

import com.cloudera.livy.Job;
import com.cloudera.livy.JobContext;
import com.cloudera.livy.LivyClient;
import com.cloudera.livy.LivyClientBuilder;

public class CountJob implements Job<Long>, Function2<Long, Long, Long> {

	private static final long serialVersionUID = 2669198457790404654L;
	private final List<Long> elements; 

	public CountJob(List<Long> elements) {
		this.elements = elements;
	}
	public Long call(JobContext jc) throws Exception {
		System.err.printf("elements : " + elements);
		JavaRDD<Long> rdd = jc.sc().parallelize(elements);
		return rdd.reduce(this);
	}
	
	public static void main(String[] args) throws Exception {
		LivyClient client = new LivyClientBuilder(false).setURI(
				new URI("http://192.168.99.100:8998")).build();

		try {
			String jobJar = "target/spark-job-0.0.1-SNAPSHOT.jar";
			System.err.printf("Uploading %s to the Spark context...\n", jobJar);
			client.uploadJar(new File(jobJar)).get();
			System.err.println("Running CountJob");
			List<Long> elements = new ArrayList<Long>();
			elements.add(1L);
			elements.add(5L);
			elements.add(16L);
			long result = client.submit(new CountJob(elements)).get();
			System.out.println("Count : " + result);
		} finally {
			client.stop(true);
		}
	}
	public Long call(Long arg0, Long arg1) throws Exception {
		return arg0 + arg1;
	}

}
