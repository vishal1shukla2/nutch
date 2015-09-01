package org.apache.nutch.indexwriter.elastic;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;

/**
 * Created by vishalshukla on 29/08/15.
 */
public class ElasticTemplate {
  private Client client;
  private String index;
  public ElasticTemplate(Client client, String index){
    this.client = client;
    this.index = index;
  }

  boolean existsChecksum(String checksum){

    FilterBuilder filter = FilterBuilders.termFilter("checksum", checksum);
    SearchResponse response = client.prepareSearch(index).setPostFilter(filter).execute().actionGet();
    return response.getHits().totalHits()>0;

  }

}
