package com.sahin.archiving_system.service.impl;

import com.sahin.archiving_system.dto.ShareFileRequest;
import com.sahin.archiving_system.repository.ShareRepository;
import com.sahin.archiving_system.service.FileService;
import com.sahin.archiving_system.service.FolderService;
import com.sahin.archiving_system.service.ShareService;
import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final FileService fileService;
    private final FolderService folderService;
    
    public ShareServiceImpl(ShareRepository shareRepository, FileService fileService, FolderService folderService) {
        this.shareRepository = shareRepository;
        this.fileService = fileService;
        this.folderService = folderService;
    }

    @Override
    public void shareFile(ShareFileRequest shareFileRequest) {

    }
}
