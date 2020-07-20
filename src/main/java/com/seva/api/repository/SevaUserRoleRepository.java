package com.seva.api.repository;

import com.seva.api.entity.SevaUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SevaUserRoleRepository extends JpaRepository<SevaUserRole, Long> {
}
