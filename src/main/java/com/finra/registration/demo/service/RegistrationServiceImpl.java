package com.finra.registration.demo.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dattukothapalli on 1/27/18.
 */
@Service
@Qualifier("RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private static final String FOLDER_PATH = "/Users/dattukothapalli/Documents";

    private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    public void storeFile(String fileversion,String filetype, List<MultipartFile> files) throws IOException

    {
        for (MultipartFile file : files) {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(FOLDER_PATH + file.getOriginalFilename());
            Files.write(path, bytes);

        }

        /***
         * Persisting the Meta data of file to the same file in the local file system.
         *
         */

        BufferedWriter bufferedWriter = null;
        FileWriter filewriter = null;

        try {

            filewriter = new FileWriter(FOLDER_PATH +"/FileRegistration.txt",true);
            bufferedWriter = new BufferedWriter(filewriter);
            bufferedWriter.newLine();
            bufferedWriter.write("****Writing meta data to the file****");
            bufferedWriter.newLine();
            bufferedWriter.write(fileversion);
            bufferedWriter.newLine();
            bufferedWriter.write(filetype);
            bufferedWriter.newLine();
            bufferedWriter.write("****Completed writing meta data to the file****");

        } catch (IOException ex) {

            logger.error("Exception occurred while writing to the file" + ex.getMessage());

        } finally {

            try {

                if (bufferedWriter != null)
                    bufferedWriter.close();

                if (filewriter != null)
                    filewriter.close();

            } catch (IOException ex) {

                logger.error("Exception occurred in finally block" + ex.getMessage());

            }
        }

    }
}
