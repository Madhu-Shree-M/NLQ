package com.acheron.nlq.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * @author Madhu Shree Mangi
 * @date : 17-08-2022
 * @project : nlq-solr-opennlp
 */
//Spring configuration of Solr persistence layer
//using @EnableSolrRepositories to scan the packages for repositories
@Configuration
@EnableSolrRepositories(basePackages = "com.acheron.nlq.repositories", namedQueriesLocation = "classpath:solr-named-queries.properties")
@ComponentScan
public class SolrConfig {
    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder("http://localhost:8983/solr").build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}
