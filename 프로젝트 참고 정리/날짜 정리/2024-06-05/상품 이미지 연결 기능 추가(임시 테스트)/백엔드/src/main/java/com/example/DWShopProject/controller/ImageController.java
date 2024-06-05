package com.example.DWShopProject.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final Path imagesLocation = Paths.get("C:/dw_shop_app/images"); // 외부 디렉토리 경로 설정

    @GetMapping("/products/{category}/{subcategory}/{filename:.+}")
    public ResponseEntity<Resource> serveProductImage(@PathVariable String category, @PathVariable String subcategory, @PathVariable String filename) {
        return serveFile(imagesLocation.resolve("products").resolve(category).resolve(subcategory).resolve(filename));
    }

    private ResponseEntity<Resource> serveFile(Path file) {
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                String contentType = "image/jpeg"; // 기본 MIME 타입
                // 파일 확장자를 확인하여 MIME 타입을 설정
                if (file.toString().endsWith(".png")) {
                    contentType = "image/png";
                } else if (file.toString().endsWith(".gif")) {
                    contentType = "image/gif";
                } // 필요에 따라 다른 MIME 타입 추가 가능

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read file: " + file);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not read file: " + file, e);
        }
    }
}
