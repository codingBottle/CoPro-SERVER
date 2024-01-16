package com.example.copro.image.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(Long imageId){
        this("해당하는 이미지가 없습니다. ID: " + imageId);
    }
}
