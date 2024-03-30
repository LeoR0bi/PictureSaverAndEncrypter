package java.com.example.picturesaverandencrypter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.com.example.picturesaverandencrypter.domain.Picture;
import java.com.example.picturesaverandencrypter.service.PictureService;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @PostMapping("/files")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            pictureService.uploadImage(file);
            return ResponseEntity.status(HttpStatus.OK).body("Picture uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading picture.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Picture> optionalImage = pictureService.getImage(id);
        if (optionalImage.isPresent()) {
            Picture image = optionalImage.get();
            return ResponseEntity.ok().contentType(org.springframework.http.MediaType.IMAGE_JPEG).body(image.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
