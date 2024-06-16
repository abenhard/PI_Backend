package br.csi.PI_Backend.service.ordem_servico;

import br.csi.PI_Backend.infra.exceptions.FileNotFoundException;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServico;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final String storagePath = "upload/imagens";

    @Autowired
    private ResourceLoader resourceLoader;
    private OrdemDeServicoService ordemDeServicoService;

    ImageService(OrdemDeServicoService ordemDeServicoService){
        this.ordemDeServicoService = ordemDeServicoService;
    }

    public Resource getImage(String folder, String imageName) throws IOException, FileNotFoundException {
        String imagePath = storagePath + "/" + folder + "/" + imageName;
        System.out.println("Image path: " + imagePath);

        Path path = Paths.get(imagePath);
        if (Files.exists(path) && Files.isReadable(path)) {
            Resource resource = resourceLoader.getResource("file:" + path.toString());
            return resource;
        } else {
            throw new FileNotFoundException("Image not found: " + imagePath);
        }
    }


    public void uploadImages(Long orderId, MultipartFile[] files) throws IOException {
        Path uploadPath = Paths.get(storagePath, orderId.toString());
        Files.createDirectories(uploadPath);

        for (MultipartFile file : files) {
            String filename = generateUniqueFilename(file.getOriginalFilename());
            Path path = uploadPath.resolve(filename);
            Files.write(path, file.getBytes());
        }
    }

    public List<String> getImageUrls(Long orderId) {
        OrdemDeServico ordemDeServico = ordemDeServicoService.findOrdemDeServicosById(orderId);
        if(ordemDeServico != null){
            return ordemDeServicoService.getImageUrls(ordemDeServico.getImagem_caminho());
        }
        return null;
    }

    public void deleteImage(String imageUrl) throws IOException {
        Path path = Paths.get(imageUrl);
        Files.deleteIfExists(path);
    }

    private String generateUniqueFilename(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }
}
