package com.sahin.archiving_system.service.impl;

import com.sahin.archiving_system.dto.FileDto;
import com.sahin.archiving_system.dto.FolderContentDto;
import com.sahin.archiving_system.dto.FolderDto;
import com.sahin.archiving_system.mapper.FolderMapper;
import com.sahin.archiving_system.model.File;
import com.sahin.archiving_system.model.Folder;
import com.sahin.archiving_system.model.User;
import com.sahin.archiving_system.repository.FileRepository;
import com.sahin.archiving_system.repository.FolderRepository;
import com.sahin.archiving_system.service.FileService;
import com.sahin.archiving_system.service.FolderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;
    private final FolderMapper folderMapper;
    public Folder createFolder(User user, String name, Long parentId){
        Folder parent=null;
        if (parentId != null){
            parent = folderRepository.findByUserAndId(user, parentId)
                    .orElseThrow(() -> new RuntimeException("Folder nor found with id:"+ parentId));
        }
        if (parent != null){
            folderRepository.findByNameAndUserAndParent(name, user, parent).ifPresent(folder -> {throw new RuntimeException("sdfasd");});
        }
        Folder folder = new Folder();
        folder.setName(name);
        folder.setUser(user);
        folder.setParent(parent);

        return folderRepository.save(folder);
    }
    public List<Folder> getSubfolders(Long parentId) {
        return folderRepository.findByParentId(parentId);
    }

    @Override
    public Folder getFolderById(Long folderId) {
        return folderRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Folder not found"));
    }

    @Override
    public void deleteFolder(Long id) {
        boolean hasChildFolders = folderRepository.existsByParentId(id);
        boolean hasFiles = fileRepository.existsByFolderId(id);
        if (hasChildFolders || hasFiles){
            throw new IllegalStateException("This folder cannot be deleted because it contains subfolders or files.");
        }
        folderRepository.deleteById(id);
    }
    @Override
    public List<Folder> findByParentId(Long parentId){
        return folderRepository.findByParentId(parentId);
    }

    @Override
    public List<FolderDto> findByParentIsNull(User user) {
        List<Folder> parents = folderRepository.findByUserAndParentIsNull(user);
        return parents.stream().map(folderMapper::folderToFolderDto).collect(Collectors.toList());
    }

    @Override
    public FolderContentDto findSubFoldersAndFiles(Long id) {
        List<Folder> childrenFolders = folderRepository.findByParentId(id);
        List<File> files = fileRepository.findByFolderId(id);

        List<FolderDto> foldersDTO = childrenFolders.stream().map(f -> new FolderDto(f.getId(),f.getName())).collect(Collectors.toList());
        List<FileDto> filesDTO = files.stream().map(f -> new FileDto(f.getId(),f.getName(),f.getType())).collect(Collectors.toList());

        return new FolderContentDto(foldersDTO, filesDTO);
    }

    @Override
    public Optional<Folder> getFolderByFolderIdAndUser(Long id, User currentUser) {
        return folderRepository.findByIdAndUser(id, currentUser);
    }

    public FolderServiceImpl(FolderRepository folderRepository, FileRepository fileRepository, FolderMapper folderMapper) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
        this.folderMapper = folderMapper;
    }
}
