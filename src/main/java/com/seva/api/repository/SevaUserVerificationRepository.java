package com.seva.api.repository;

import com.seva.api.entity.SevaUserVerification;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SevaUserVerificationRepository extends JpaRepository<SevaUserVerification, Integer> {

//    @Query(value = "select * from seva_user_verification where su_id=1 and suv_code=12677",nativeQuery = true)
    @Query(value = "select * from seva_user_verification where su_id=?1 and suv_code=?2",nativeQuery = true)
    public SevaUserVerification findSevaUserVerification(Long suID,Integer suvCode);


    @Query(value = "delete from seva_user_verification where su_id=?1",nativeQuery = true)
    @Transactional
    @Modifying(clearAutomatically = true)
    public void deleteAllBySevaUserSuId(Long suid);

}
