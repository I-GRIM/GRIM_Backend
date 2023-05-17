package com.example.bookgrim.common.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {
    private static final String S3_BUCKET_DIRECTORY_NAME = "character";

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(byte[] buffer, String name) throws IOException {
        // 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(buffer.length);

        // 실제 S3 bucket 디렉토리명 설정
        // 파일명 중복을 방지하기 위한 UUID 추가
        String fileName = S3_BUCKET_DIRECTORY_NAME + "/" + UUID.randomUUID() + "." + name;
        InputStream inputStream = new ByteArrayInputStream(buffer);

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//        } catch (IOException e) {
//            log.error("S3 파일 업로드에 실패했습니다. {}", e.getMessage());
//            throw new IllegalStateException("S3 파일 업로드에 실패했습니다.");
//        }
        inputStream.close();

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }


}
