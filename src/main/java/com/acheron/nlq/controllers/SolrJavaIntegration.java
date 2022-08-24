package com.acheron.nlq.controllers;

import com.acheron.nlq.models.MobileBean;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * @author Madhu Shree Mangi
 * @date : 24-08-2022
 * @project : nlq-solr-opennlp
 */
public class SolrJavaIntegration {
    private HttpSolrClient solrClient;
//    String url="http://localhost:8983/solr/mobile";

    //to initiate solrj client by connecting to solr server
    public SolrJavaIntegration(String clientUrl) {

        solrClient = new HttpSolrClient.Builder(clientUrl).build();
        solrClient.setParser(new XMLResponseParser());
    }
// adding a bean to index
    public void addMobileBean(MobileBean pBean) throws IOException, SolrServerException {

        solrClient.addBean(pBean);
        solrClient.commit();
    }

    //defining the data to be indexed
    public void addSolrDocument(String documentId, String itemName, String itemPrice,String itemBrand,String itemColor) throws SolrServerException, IOException {

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", documentId);
        document.addField("name", itemName);
        document.addField("price", itemPrice);
        document.addField("brand",itemBrand);
        document.addField("color",itemColor);
        solrClient.add(document);
        solrClient.commit();
    }


    public void deleteSolrDocumentById(String documentId) throws SolrServerException, IOException {

        solrClient.deleteById(documentId);
        solrClient.commit();
    }

    public void deleteSolrDocumentByQuery(String query) throws SolrServerException, IOException {

        solrClient.deleteByQuery(query);
        solrClient.commit();
    }

    protected HttpSolrClient getSolrClient() {
        return solrClient;
    }

    protected void setSolrClient(HttpSolrClient solrClient) {
        this.solrClient = solrClient;
    }

    //    public void queryDocByFieldAndId(String id,String price){
//        SolrQuery query = new SolrQuery();
//        query.set("q", "price:599.99");
//        QueryResponse response = solr.query(query);
//
//        SolrDocumentList docList = response.getResults();
//        assertEquals(docList.getNumFound(), 1);
//
//        for (SolrDocument doc : docList) {
//            assertEquals((String) doc.getFieldValue("id"), "1");
//            assertEquals((Double) doc.getFieldValue("price"), (Double) 599.99);
//        }
//    }

//    public void queryById(String id){
//        SolrDocument doc = solr.getById(id);
//        assertEquals((String) doc.getFieldValue("name"), "Iphone 13");
//        assertEquals((Double) doc.getFieldValue("price"), (Double) 599.99);
//    }


}
