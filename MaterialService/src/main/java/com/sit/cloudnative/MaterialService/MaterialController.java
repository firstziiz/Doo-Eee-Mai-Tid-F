package com.sit.cloudnative.MaterialService;

import com.sit.cloudnative.MaterialService.Exception.InvalidFileTypeException;
import com.sit.cloudnative.MaterialService.Exception.MinioErrorException;
import io.minio.errors.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @Autowired
    private MinioStorageService minioStorageService;

    @Value("${storage.file.name.key}")
    private String fileKey;

    @Value("${storage.file.name.initVector}")
    private String fileInitVector;


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/subjects/{subjectId}/materials"
    )
    public ResponseEntity<Material> addMaterial(@PathVariable("subjectId")int subjectId,@RequestParam("file") MultipartFile file,@RequestParam("isActive")boolean isActive) throws Exception {

        String fileType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String timestampWithFileName = generateTimestampWithFileName(fileName);
        String encryptTimestampWithFileName = encryptFileName(timestampWithFileName);
        
        if(checkValidFileType(fileType)){
            try{
                minioStorageService.uploadFile(timestampWithFileName,file);
                Material material = new Material();
                material.setId(encryptTimestampWithFileName);
                material.setSubjectId(subjectId);
                material.setFileName(timestampWithFileName);
                material.setActive(isActive);
                Material material_object = materialService.addMaterial(material);
                return new ResponseEntity<Material>(material_object,HttpStatus.CREATED);
            }catch (MinioException e){
                throw new MinioErrorException(e.getMessage());
            }
        }
        throw new InvalidFileTypeException();
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/materials"
    )
    public ResponseEntity<Material> deleteMaterial(@RequestParam("materialId")String materialId) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidEndpointException, InvalidPortException, NoResponseException, InternalException, InvalidArgumentException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException {
        try{
            Material material = materialService.getMaterialById(materialId);
            materialService.deleteMaterial(material);

            minioStorageService.deleteFile(material.getFileName());

            return new ResponseEntity<Material>(HttpStatus.NO_CONTENT);
        }catch(MinioException e){
            throw new MinioErrorException(e.getMessage());
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/materials"
    )
    public ResponseEntity<List<Material>> getAllMaterial() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException, InsufficientDataException, InvalidPortException, InvalidArgumentException, InvalidExpiresRangeException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InvalidEndpointException, InternalException {
        List<Material> materials = materialService.getAllMaterials();
        for (Material material:materials) {
            try{
                String url = minioStorageService.getDownloadLink(material.getFileName());
                material.setPath(url);
            }catch(MinioException e){
                throw new MinioErrorException(e.getMessage());
            }
        }
        return new ResponseEntity<List<Material>>(materials,HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/subjects/{subjectId}/materials"
    )
    public ResponseEntity<List<Material>> getMaterialBySubjectId(@PathVariable("subjectId")int subjectId) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        List<Material> materials = materialService.getMaterialsBySubjectId(subjectId);
        for (Material material:materials) {
            try{
                String url = minioStorageService.getDownloadLink(material.getFileName());
                material.setPath(url);
            }catch(MinioException e){
                throw new MinioErrorException(e.getMessage());
            }
        }
        return new ResponseEntity<List<Material>>(materials,HttpStatus.OK);
    }



    private boolean checkValidFileType(String fileType){
        String allowType[] = {"application/pdf","application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation"};
        return Arrays.asList(allowType).contains(fileType);
    }

    private String generateTimestampWithFileName(String originalFileName){
        Date now = new Date();
        SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hhmmss = new SimpleDateFormat("hhmmss");
        return yearMonthDay.format(now)+"-"+hhmmss.format(now)+"-"+originalFileName;
    }


    private String encryptFileName(String timestampWithFileName) {
        String key = this.fileInitVector;
        String initVector = this.fileInitVector;

        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(timestampWithFileName.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
