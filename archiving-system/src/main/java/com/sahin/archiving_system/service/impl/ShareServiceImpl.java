package com.sahin.archiving_system.service.impl;

import com.sahin.archiving_system.dto.ShareFileRequest;
import com.sahin.archiving_system.exception.ResourceNotFoundException;
import com.sahin.archiving_system.model.File;
import com.sahin.archiving_system.model.Folder;
import com.sahin.archiving_system.model.User;
import com.sahin.archiving_system.repository.FileRepository;
import com.sahin.archiving_system.repository.ShareRepository;
import com.sahin.archiving_system.repository.UserRepository;
import com.sahin.archiving_system.service.FileService;
import com.sahin.archiving_system.service.FolderService;
import com.sahin.archiving_system.service.ShareService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final FileService fileService;
    private final FolderService folderService;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    public ShareServiceImpl(ShareRepository shareRepository, FileService fileService, FolderService folderService, UserRepository userRepository, FileRepository fileRepository) {
        this.shareRepository = shareRepository;
        this.fileService = fileService;
        this.folderService = folderService;
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public void shareFile(ShareFileRequest shareFileRequest) throws FileNotFoundException {
        File originalFile = fileService.getFileById(shareFileRequest.getFileId());
        User receiverUser = userRepository.findByEmail(shareFileRequest.getReceiverEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+shareFileRequest.getReceiverEmail()));
        Folder inBoxFolder = folderService.getInBoxFolderByUser(receiverUser);

        File copiedFile = new File();
        copiedFile.setName(originalFile.getName());
        copiedFile.setType(originalFile.getType());
        copiedFile.setData(originalFile.getData());
        copiedFile.setFolder(inBoxFolder);
        copiedFile.setUser(receiverUser);
        fileRepository.save(copiedFile);


    }
}
