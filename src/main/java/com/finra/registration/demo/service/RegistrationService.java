package com.finra.registration.demo.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dattukothapalli on 1/27/18.
 */

public interface RegistrationService {

     void storeFile(String fileversion,String filetype, List<MultipartFile> files) throws IOException;
}
