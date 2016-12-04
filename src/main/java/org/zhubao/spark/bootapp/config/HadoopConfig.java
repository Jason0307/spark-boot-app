package org.zhubao.spark.bootapp.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHadoop
public class HadoopConfig extends SpringHadoopConfigurerAdapter {

	@Override
	  public void configure(HadoopConfigConfigurer config) throws Exception {
	    config
	      .fileSystemUri("hdfs://127.0.0.1:9000");
	  }
}
