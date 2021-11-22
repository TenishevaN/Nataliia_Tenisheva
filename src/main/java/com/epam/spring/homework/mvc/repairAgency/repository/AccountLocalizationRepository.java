package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.AccountLocalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountLocalizationRepository extends JpaRepository<AccountLocalization, Long> {

    AccountLocalization save(AccountLocalization accountLocalization);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query("update AccountLocalization al set al.name = :name where al.accountId = :accountId and al.languageId = :laguageId")
    int updateByAccountIdAndLanuageId(@Param("name") String name, @Param("accountId") Long accountId, @Param("laguageId") Long laguageId);

    @Query("select al from AccountLocalization al where al.accountId = :accountId and al.languageId = :laguageId")
    AccountLocalization findByAccountIdAndLanuageId(@Param("accountId") Long accountId, @Param("laguageId") Long laguageId);

    List<AccountLocalization> findAll();

    List<AccountLocalization> findByAccountId(Long accountId);

}
