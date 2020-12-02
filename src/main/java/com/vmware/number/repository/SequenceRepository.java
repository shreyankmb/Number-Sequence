package com.vmware.number.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vmware.number.domain.Sequence;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long>{
    
    @Query("SELECT v FROM vm_data v WHERE v.uuid = :id")
    Sequence findDataByUUID(@Param("id") String id);
    
    
    

}
