package com.acheron.nlq.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author Madhu Shree Mangi
 * @date : 16-08-2022
 * @project : nlq-solr-opennlp
 */

// In this class we are defining mobile document
    // @SolrDocument indicates that the mobile class is a solr document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SolrDocument(collection = "mobile")
public class Mobile {
    @Id
    @Indexed(name = "id", type = "string")
    private String id;
    @Indexed(name = "name", type="string")
    private String name;
    @Indexed(name = "brand", type="string")
    private String brand;
    @Indexed(name = "color", type="string")
    private String color;
    @Indexed(name = "price", type="string")
    private String price;

}
