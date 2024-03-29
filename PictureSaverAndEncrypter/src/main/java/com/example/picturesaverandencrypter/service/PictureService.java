package java.com.example.picturesaverandencrypter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        // Itt végezheted el a kép méretezését és titkosítását
        pictureRepository.save(image);
    }

    public Optional<Picture> getImage(Long id) {
        return pictureRepository.findById(id);
    }
}
