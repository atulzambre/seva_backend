package com.seva.api.repository;

import com.seva.api.entity.SevaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SevaUserRepository extends JpaRepository<SevaUser, Long> {

    @Query(value = "select * from seva_user where su_email=?1 and su_password=?2",nativeQuery = true)
    public SevaUser findByEmailAndPassword(String email,String password);
}
