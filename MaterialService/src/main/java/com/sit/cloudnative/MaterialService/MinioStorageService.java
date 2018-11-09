package com.sit.cloudnative.MaterialService;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinioStorageService {
    MinioClient minioClient;
    String url = "";
    String bucketName = "";
    String accessKey = "";
    String secretKey = "";

}
