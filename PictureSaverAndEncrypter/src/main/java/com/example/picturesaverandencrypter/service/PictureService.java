package java.com.example.picturesaverandencrypter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.com.example.picturesaverandencrypter.domain.Picture;
import java.com.example.picturesaverandencrypter.repository.PictureRepository;
import java.io.IOException;
import java.util.Optional;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public void uploadImage(MultipartFile file) throws IOException {
        Picture image = new Picture();
        image.setFilename(file.getOriginalFilename());
        image.setData(file.getBytes());
        saveAndEncryptImage(file);
        pictureRepository.save(image);
    }

    public Optional<Picture> getImage(Long id) {
        return pictureRepository.findById(id);
    }

    public void saveAndEncryptImage(MultipartFile file) throws IOException {

        if (!isValidImageFormat(file)) {
            throw new IllegalArgumentException("Only PNG or JPG files are accepted!");
        }

        if (!isValidImageSize(file)) {
            throw new IllegalArgumentException("Maximum 5000x5000 pixels!");
        }

    }

    private boolean isValidImageFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
    }

    private boolean isValidImageSize(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        int width = image.getWidth();
        int height = image.getHeight();
        return width <= 5000 && height <= 5000;
    }
}
