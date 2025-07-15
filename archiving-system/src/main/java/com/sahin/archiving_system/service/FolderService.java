package com.sahin.archiving_system.service;

import com.sahin.archiving_system.dto.FolderContentDto;
import com.sahin.archiving_system.dto.FolderDto;
import com.sahin.archiving_system.model.Folder;
import com.sahin.archiving_system.model.User;

import java.util.List;
import java.util.Optional;

public interface FolderService {

    Folder createFolder(User user, String name, Long parentId);
    List<Folder> getSubfolders(Long parentId);

    Folder getFolderById(Long folderId);

    void deleteFolder(Long id);
    List<Folder> findByParentId(Long parentId);

    List<FolderDto> findByParentIsNull(User user);
    FolderContentDto findSubFoldersAndFiles(Long id);

    Optional<Folder> getFolderByFolderIdAndUser(Long folderId, User currentUser);

    Folder getInBoxFolderByUser(User user);

    Folder createInBoxFolder(User user);
}
