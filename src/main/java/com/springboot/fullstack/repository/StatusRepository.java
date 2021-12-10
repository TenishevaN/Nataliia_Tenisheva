package com.springboot.fullstack.repository;

import com.springboot.fullstack.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long > {

    @Query(nativeQuery = true, value ="SELECT sl.name, sl.status_id, sl.language_id, l.code as locale, sl.id FROM status_localization sl left join language l on sl.language_id = l.id WHERE l.code = :locale and sl.status_id = :idStatus")
    Status get(@Param("idStatus") Long idStatus, @Param("locale") String locale);


    @Query(nativeQuery = true, value ="SELECT sl.name, sl.status_id, sl.language_id, l.code as locale, sl.id FROM status_localization sl left join language l on sl.language_id = l.id WHERE l.code = :locale order by sl.status_id")
    List<Status> getAll(@Param("locale")String locale);
}
