package com.yasinenessisik.javaspringfileStorage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {


    private final StorageRepository repository;

    private final FileDataRepository fileDataRepository;
    private final StorageConfig storageConfig;

    public StorageService(StorageRepository repository, FileDataRepository fileDataRepository, StorageConfig storageConfig) {
        this.repository = repository;
        this.fileDataRepository = fileDataRepository;
        this.storageConfig = storageConfig;
    }


    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }



    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtil.decompressImage(dbImageData.get().getImageData());
        return images;
    }


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = storageConfig.folderPath() + File.separator + fileName;
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(fileName)
                .type(file.getContentType())
                .filePath(filePath).build());

        File directory = new File(storageConfig.folderPath());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
    public String updateImageInFileSystem(String fileName, MultipartFile newFile) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getFilePath();
            File oldFile = new File(filePath);
            if (oldFile.exists()) {
                oldFile.delete();
                newFile.transferTo(new File(filePath));
                return "file updated successfully : " + fileName;
            } else {
                return "file does not exist : " + fileName;
            }
        } else {
            return "file not found : " + fileName;
        }
    }
}
