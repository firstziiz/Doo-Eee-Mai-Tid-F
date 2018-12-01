package com.sit.cloudnative.MaterialService;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class MinioStorageService {

    MinioClient minioClient;

    @Value("${storage.minio.url}")
    private String url;
    @Value("${storage.minio.bucketName}")
    private String bucketName;
    @Value("${storage.minio.accessKey}")
    private String accessKey;
    @Value("${storage.minio.secretKey}")
    private String secretKey;

    public String getUrl() {
        return url;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void uploadFile(String timestampWithFileName, MultipartFile file) throws InvalidPortException, InvalidEndpointException, IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, InvalidArgumentException {
            minioClient = new MinioClient(url,accessKey,secretKey);
            InputStream inputStream =  new BufferedInputStream(file.getInputStream());
            minioClient.putObject(bucketName,timestampWithFileName,inputStream,file.getContentType());
    }
    public void deleteFile(String fileName) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidPortException, InvalidEndpointException, InsufficientDataException, NoResponseException, InternalException, InvalidArgumentException, ErrorResponseException, InvalidBucketNameException {
            minioClient = new MinioClient(url,accessKey,secretKey);
            minioClient.removeObject(bucketName,fileName);
    }
    public String getDownloadLink(String fileName) throws InvalidPortException, InvalidEndpointException, IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, InvalidExpiresRangeException {
            minioClient = new MinioClient(url,accessKey,secretKey);
            String url = minioClient.presignedGetObject(bucketName,fileName,60*30);
            return url;
    }
}
