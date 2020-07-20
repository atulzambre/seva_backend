package com.seva.api.repository;

import com.seva.api.entity.SevaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SevaUserRepository extends JpaRepository<SevaUser,Long> {
}
