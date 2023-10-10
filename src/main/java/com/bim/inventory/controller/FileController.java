package com.bim.inventory.controller;


import com.bim.inventory.entity.FileEntity;
import com.bim.inventory.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.List;


@RestController
@RequestMapping("/api/fayl")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class.getName());


    private String ROOT_DIRECTORY = "files";

    private final FileService faylService;

    @Value("${system.root-directory}")
    private void setDirectory(String url) {
        this.ROOT_DIRECTORY = url;

    }


    public FileController(FileService faylService) {
        this.faylService = faylService;
    }


    @GetMapping()
    public ResponseEntity<List<FileEntity>> getAll(@RequestParam(name = "key", required = false) String key,
                                                                      HttpServletRequest req, HttpServletResponse res) {
        if (key == null) key = "";
        return ResponseEntity.ok(faylService.getAll(key));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(faylService.getById(id));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        FileEntity f = faylService.getById(id);

        java.io.File file = new java.io.File(ROOT_DIRECTORY + "/" + f.getId() + "_" + f.getName());
        if (file.exists()) {

            try {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");

                MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
                if(f.getType() != null){
                    mediaType = MediaType.parseMediaType(f.getType());
                }

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.length())
                        .contentType(mediaType)
                        .body(resource);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }


        return ResponseEntity.notFound().build();
    }


    @PostMapping("/upload")
    public ResponseEntity<FileEntity> upload(@RequestParam("file") MultipartFile file) {
        FileEntity f = new FileEntity();
        f.setName(file.getOriginalFilename());
        f.setType(file.getContentType());
        f = faylService.create(f);
        try {
            java.io.File saqlashFayl = new java.io.File(ROOT_DIRECTORY);
            if (!saqlashFayl.exists()) saqlashFayl.mkdirs();

            saqlashFayl = new File(ROOT_DIRECTORY + "/" + f.getId() + "_" + f.getName());

            saqlashFayl.createNewFile();

            FileOutputStream fos = new FileOutputStream(saqlashFayl);
            fos.write(file.getBytes());
            fos.close();
            return ResponseEntity.ok(f);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        faylService.delete(f);

        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        faylService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}