package com.finra.registration.demo.controller;

import com.finra.registration.demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
/**
 * Created by dattukothapalli on 1/27/18.
 */

@RestController
public final class FileRegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value="/v1/upload")
    public ResponseEntity<String> FileUpload(@RequestParam(value = "fileversion") String fileversion,
                                             @RequestParam(value = "filetype") String filetype,@RequestBody MultipartFile file) throws Exception
    {
        if (file.isEmpty()) {
            return new ResponseEntity("please upload a file!", HttpStatus.OK);
        }
        registrationService.storeFile(fileversion,filetype, Arrays.asList(file));
        return new ResponseEntity<String>("Uploaded Successfully- " + file.getOriginalFilename(), new HttpHeaders(),
                HttpStatus.OK);
    }

}

