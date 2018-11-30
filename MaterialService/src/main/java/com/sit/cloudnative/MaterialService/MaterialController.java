package com.sit.cloudnative.MaterialService;

import com.sit.cloudnative.MaterialService.Exception.InvalidFileTypeException;

import com.sit.cloudnative.MaterialService.Logger.AuditLogger;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class MaterialController {

    AuditLogger logger = new AuditLogger(this.getClass().getName());

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MinioStorageService minioStorageService;

    @Value("${storage.file.name.key}")
    private String fileKey;

    @Value("${storage.file.name.initVector}")
    private String fileInitVector;

    @RequestMapping(method = RequestMethod.POST, value = "/subjects/{subjectId}/materials")
    public ResponseEntity<Material> addMaterial(@PathVariable("subjectId") int subjectId,
            @RequestParam(name = "file", required = true) MultipartFile file,
            @RequestParam(name = "isActive", required = true) boolean isActive,
            @RequestAttribute("userId") String userId,
            HttpServletRequest request)
            throws NoResponseException, InvalidPortException, InvalidEndpointException, InsufficientDataException, ErrorResponseException, InvalidBucketNameException, InvalidArgumentException, InternalException, GeneralSecurityException, IOException, XmlPullParserException  {
        String fileType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String timestampWithFileName = generateTimestampWithFileName(fileName);

        if(checkValidFileType(fileType)){
            try{
                minioStorageService.uploadFile(timestampWithFileName,file);
            } catch (NoResponseException e) {
                logger.error(request, "no response exception in addMaterial");
                throw new NoResponseException();
            } catch (InvalidPortException e) {
                logger.error(request, "invalid port exception in addMaterial");
                throw new InvalidPortException(0 , e.getMessage());
            } catch (InvalidEndpointException e) {
                logger.error(request, "invalid endpoint exception in addMaterial");
                throw new InvalidEndpointException(minioStorageService.getUrl(), e.getMessage());
            } catch (InsufficientDataException e) {
                logger.error(request, userId + " insufficient data exception in addMaterial");
                throw new InsufficientDataException(e.getMessage());
            } catch (InvalidBucketNameException e) {
                logger.error(request, "invalid bucket name exception in addMaterial");
                throw new InvalidBucketNameException(minioStorageService.getBucketName(), e.getMessage());
            } catch (InternalException e) {
                logger.error(request, "internal exception in addMaterial");
                throw new InternalException(e.getMessage());
            } catch (InvalidArgumentException e) {
                logger.error(request, "internal argument exception in addMaterial");
                throw new InvalidArgumentException(e.getMessage());
            } catch (GeneralSecurityException e) {
                logger.error(request, "general security exception in addMaterial");
                throw new GeneralSecurityException();
            } catch (IOException e) {
                logger.error(request, "io exception in addMaterial");
                throw new IOException();
            } catch (XmlPullParserException e) {
                logger.error(request, "xml pull parser exception in addMaterial");
                throw new XmlPullParserException(e.getMessage());
            }

            Material material = new Material();
            material.setSubjectId(subjectId);
            material.setFileName(timestampWithFileName);
            material.setActive(isActive);
            material.setUploadedBy(Integer.parseInt(userId));
            Material material_object = materialService.addMaterial(material);
            return new ResponseEntity<Material>(material_object, HttpStatus.CREATED);
        }
        throw new InvalidFileTypeException();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/material/{fileName}")
    public ResponseEntity<Material> deleteMaterial(
            @RequestParam("fileName") String fileName,
            HttpServletRequest request)
            throws NoResponseException, InvalidPortException, InvalidEndpointException, InsufficientDataException, ErrorResponseException, InvalidBucketNameException, InvalidArgumentException, InternalException, GeneralSecurityException, IOException, XmlPullParserException {
        Material material = materialService.getMaterialByFileName(fileName);
        materialService.deleteMaterial(material);
        try {
            minioStorageService.deleteFile(material.getFileName());
        } catch (NoResponseException e) {
            logger.error(request, "no response exception in deleteMaterial");
            throw new NoResponseException();
        } catch (InvalidPortException e) {
            logger.error(request, "invalid port exception in deleteMaterial");
            throw new InvalidPortException(0 , e.getMessage());
        } catch (InvalidEndpointException e) {
            logger.error(request, "invalid endpoint exception in deleteMaterial");
            throw new InvalidEndpointException(minioStorageService.getUrl(), e.getMessage());
        } catch (InsufficientDataException e) {
            logger.error(request, request.getAttribute("userId") + " insufficient data exception in deleteMaterial");
            throw new InsufficientDataException(e.getMessage());
        } catch (InvalidBucketNameException e) {
            logger.error(request, "invalid bucket name exception in deleteMaterial");
            throw new InvalidBucketNameException(minioStorageService.getBucketName(), e.getMessage());
        } catch (InternalException e) {
            logger.error(request, "internal exception in deleteMaterial");
            throw new InternalException(e.getMessage());
        } catch (InvalidArgumentException e) {
            logger.error(request, "internal argument exception in deleteMaterial");
            throw new InvalidArgumentException(e.getMessage());
        } catch (GeneralSecurityException e) {
            logger.error(request, "general security exception in deleteMaterial");
            throw new GeneralSecurityException();
        } catch (IOException e) {
            logger.error(request, "io exception in deleteMaterial");
            throw new IOException();
        } catch (XmlPullParserException e) {
            logger.error(request, "xml pull parser exception in deleteMaterial");
            throw new XmlPullParserException(e.getMessage());
        }
        return new ResponseEntity<Material>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/materials"
    )
    public ResponseEntity<List<Material>> getAllMaterial(HttpServletRequest request) throws NoResponseException, InvalidPortException, InvalidEndpointException, InsufficientDataException, ErrorResponseException, InvalidBucketNameException, InvalidArgumentException, InternalException, GeneralSecurityException, IOException, XmlPullParserException, InvalidExpiresRangeException {
        List<Material> materials = materialService.getAllMaterials();
        for (Material material:materials) {
            try {
                String url = minioStorageService.getDownloadLink(material.getFileName());
                material.setPath(url);
            } catch (NoResponseException e) {
                logger.error(request, "no response exception in deleteMaterial");
                throw new NoResponseException();
            } catch (InvalidPortException e) {
                logger.error(request, "invalid port exception in deleteMaterial");
                throw new InvalidPortException(0 , e.getMessage());
            } catch (InvalidEndpointException e) {
                logger.error(request, "invalid endpoint exception in deleteMaterial");
                throw new InvalidEndpointException(minioStorageService.getUrl(), e.getMessage());
            } catch (InsufficientDataException e) {
                logger.error(request, request.getAttribute("userId") + " insufficient data exception in deleteMaterial");
                throw new InsufficientDataException(e.getMessage());
            } catch (InvalidBucketNameException e) {
                logger.error(request, "invalid bucket name exception in deleteMaterial");
                throw new InvalidBucketNameException(minioStorageService.getBucketName(), e.getMessage());
            } catch (InternalException e) {
                logger.error(request, "internal exception in deleteMaterial");
                throw new InternalException(e.getMessage());
            } catch (InvalidArgumentException e) {
                logger.error(request, "internal argument exception in deleteMaterial");
                throw new InvalidArgumentException(e.getMessage());
            } catch (GeneralSecurityException e) {
                logger.error(request, "general security exception in deleteMaterial");
                throw new GeneralSecurityException();
            } catch (IOException e) {
                logger.error(request, "io exception in deleteMaterial");
                throw new IOException();
            } catch (XmlPullParserException e) {
                logger.error(request, "xml pull parser exception in deleteMaterial");
                throw new XmlPullParserException(e.getMessage());
            }
        }
        return new ResponseEntity<List<Material>>(materials, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subject/{subjectId}/materials")
    public ResponseEntity<List<Material>> getMaterialBySubjectId(@PathVariable("subjectId") int subjectId, HttpServletRequest request) throws MinioException, IOException, InvalidKeyException, NoSuchAlgorithmException, XmlPullParserException, GeneralSecurityException {
        List<Material> materials = materialService.getMaterialsBySubjectId(subjectId);
        for (Material material : materials) {
            try {
                String url = minioStorageService.getDownloadLink(material.getFileName());
                material.setPath(url);
            } catch (NoResponseException e) {
                logger.error(request, "no response exception in deleteMaterial");
                throw new NoResponseException();
            } catch (InvalidPortException e) {
                logger.error(request, "invalid port exception in deleteMaterial");
                throw new InvalidPortException(0 , e.getMessage());
            } catch (InvalidEndpointException e) {
                logger.error(request, "invalid endpoint exception in deleteMaterial");
                throw new InvalidEndpointException(minioStorageService.getUrl(), e.getMessage());
            } catch (InsufficientDataException e) {
                logger.error(request, request.getAttribute("userId") + " insufficient data exception in deleteMaterial");
                throw new InsufficientDataException(e.getMessage());
            } catch (InvalidBucketNameException e) {
                logger.error(request, "invalid bucket name exception in deleteMaterial");
                throw new InvalidBucketNameException(minioStorageService.getBucketName(), e.getMessage());
            } catch (InternalException e) {
                logger.error(request, "internal exception in deleteMaterial");
                throw new InternalException(e.getMessage());
            } catch (InvalidArgumentException e) {
                logger.error(request, "internal argument exception in deleteMaterial");
                throw new InvalidArgumentException(e.getMessage());
            } catch (GeneralSecurityException e) {
                logger.error(request, "general security exception in deleteMaterial");
                throw new GeneralSecurityException();
            } catch (IOException e) {
                logger.error(request, "io exception in deleteMaterial");
                throw new IOException();
            } catch (XmlPullParserException e) {
                logger.error(request, "xml pull parser exception in deleteMaterial");
                throw new XmlPullParserException(e.getMessage());
            }
        }
        return new ResponseEntity<List<Material>>(materials, HttpStatus.OK);
    }

    private boolean checkValidFileType(String fileType) {
        String allowType[] = { "application/pdf", "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/vnd.ms-powerpoint",
                "application/vnd.openxmlformats-officedocument.presentationml.presentation" };
        return Arrays.asList(allowType).contains(fileType);
    }

    private String generateTimestampWithFileName(String originalFileName) {
        Date now = new Date();
        SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hhmmss = new SimpleDateFormat("hhmmss");
        return yearMonthDay.format(now) + "-" + hhmmss.format(now) + "-" + originalFileName;
    }


}
