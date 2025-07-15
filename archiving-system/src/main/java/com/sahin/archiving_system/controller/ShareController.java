package com.sahin.archiving_system.controller;

import com.sahin.archiving_system.dto.ShareFileRequest;
import com.sahin.archiving_system.service.ShareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/share")
public class ShareController {

    private final ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;

    }

    @PostMapping
    public ResponseEntity<String> shareFile(@RequestBody ShareFileRequest shareFileRequest) throws FileNotFoundException {
        shareService.shareFile(shareFileRequest);
        return new ResponseEntity<>("File sent successfully.", HttpStatus.OK);
    }
}
