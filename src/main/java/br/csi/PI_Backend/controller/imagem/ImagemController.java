package br.csi.PI_Backend.controller.imagem;

import br.csi.PI_Backend.infra.exceptions.FileNotFoundException;
import br.csi.PI_Backend.service.ordem_servico.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
@RestController
@RequestMapping("/imagens")
public class ImagemController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/{orderId}/images")
    public ResponseEntity<Void> uploadImages(@PathVariable Long orderId, @RequestParam("files") MultipartFile[] files) throws IOException {
        imageService.uploadImages(orderId, files);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{folder}/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String folder, @PathVariable String imageName, @RequestHeader("Authorization") String token) throws IOException, FileNotFoundException {
        System.out.println("Getting Image: " + folder + "/" + imageName);

        Resource resource = imageService.getImage(folder, imageName);
        String contentType = Files.probeContentType(resource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @GetMapping("/{orderId}/images")
    public ResponseEntity<List<String>> getOrderImagesUrl(@PathVariable Long orderId) {
        List<String> imageUrls = imageService.getImageUrls(orderId);
        return ResponseEntity.ok(imageUrls);
    }

    @DeleteMapping("/images")
    public ResponseEntity<Void> deleteImage(@RequestParam String imageUrl, @RequestHeader("Authorization") String token) throws IOException {
        imageService.deleteImage(imageUrl);
        return ResponseEntity.ok().build();
    }
}