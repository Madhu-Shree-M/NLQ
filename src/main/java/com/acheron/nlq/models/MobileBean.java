package com.acheron.nlq.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author Madhu Shree Mangi
 * @date : 24-08-2022
 * @project : nlq-solr-opennlp
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MobileBean {
    String id;
    String name;
    String brand;
    String color;
    String price;

    @Field("id")
    public void setId(String id) {
        this.id = id;
    }
    @Field("name")
    public void setName(String name) {
        this.name = name;
    }
    @Field("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }
    @Field("color")
    public void setColor(String color) {
        this.color = color;
    }
    @Field("price")
    public void setPrice(String price) {
        this.price = price;
    }
}
