package com.sahin.archiving_system.repository;

import com.sahin.archiving_system.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {
}
