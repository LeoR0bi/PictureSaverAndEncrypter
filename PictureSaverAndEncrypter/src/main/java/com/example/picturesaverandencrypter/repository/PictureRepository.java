package java.com.example.picturesaverandencrypter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.com.example.picturesaverandencrypter.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
