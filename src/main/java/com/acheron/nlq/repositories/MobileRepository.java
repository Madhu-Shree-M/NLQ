package com.acheron.nlq.repositories;

import com.acheron.nlq.models.Mobile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//it provides sophisticated CRUD functionality for the entity class that is being managed
@Repository
public interface MobileRepository extends SolrCrudRepository<Mobile, Integer> {

    List<Mobile> findByName(String mobileName);

    @Query("id:*?0* OR name:*?0* OR brand:*?0* OR color:*?0* OR price:*?0*")
    public Page<Mobile> findByCustomQuery(String searchTerm, Pageable pageable);

    @Query(name = "Mobile.findByNamedQuery")
    public Page<Mobile> findByNamedQuery(String searchTerm, Pageable pageable);
}
