package com.sahin.archiving_system.repository;

import com.sahin.archiving_system.model.Folder;
import com.sahin.archiving_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findByParentId(Long parentId);

    boolean existsByParentId(Long parentId);

    Optional<Folder> findByIdAndUser(Long id, User currentUser);

    List<Folder> findByUserAndParentIsNull(User user);

    Optional<Folder> findByUserAndId(User user, Long parentId);

    Optional<Folder> findByNameAndUserAndParent(String name, User user, Folder parent);

    Optional<Folder> findByUserAndIsInBoxTrue(User user);
}
