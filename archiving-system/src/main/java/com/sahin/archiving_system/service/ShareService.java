package com.sahin.archiving_system.service;

import com.sahin.archiving_system.dto.ShareFileRequest;

import java.io.FileNotFoundException;

public interface ShareService {
    void shareFile(ShareFileRequest shareFileRequest) throws FileNotFoundException;
}
