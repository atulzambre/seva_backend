package com.seva.api.repository;

import com.seva.api.entity.SevaRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SevaRoleRepository extends JpaRepository<SevaRole,Integer> {
}
