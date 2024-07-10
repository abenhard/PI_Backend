package br.csi.PI_Backend.service.ordem_servico;

import br.csi.PI_Backend.BackEndUrl;
import br.csi.PI_Backend.infra.exceptions.FileNotFoundException;
import br.csi.PI_Backend.service.ordem_servico.OrdemDeServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final String storagePath = "upload/imagens/";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private OrdemDeServicoService ordemDeServicoService;

    public Resource getImage(String folder, String imageName) throws IOException, FileNotFoundException {
        String imagePath = Paths.get(storagePath, folder, imageName).toString();
        System.out.println("Image path: " + imagePath);

        Path path = Paths.get(imagePath);
        if (Files.exists(path) && Files.isReadable(path)) {
            System.out.println("Arquivo encontrado");
            return resourceLoader.getResource("file:" + path);
        } else {
            System.out.println("Arquivo NÃO encontrado");
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
        List<String> imageUrls = new ArrayList<>();
        String ordemPasta = ordemDeServicoService.findOrdemDeServicosById(orderId).getImagem_caminho();
        String directoryPath = "upload/imagens/" + ordemPasta;
        Path orderPath = Paths.get(directoryPath);
        System.out.println("Order Path getImagesUrls: " + orderPath);
        if (Files.exists(orderPath)) {
            try {
                Files.list(orderPath).forEach(imagePath -> {
                    String imageName = BackEndUrl.getInstance().getBackendUrl() + "imagens/" + ordemPasta + "/" + imagePath.getFileName().toString();
                    imageUrls.add(imageName);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Imagem URLS: " + imageUrls);
        return imageUrls;
    }

    private String generateUniqueFilename(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }

    public void deleteImage(String imageUrl) {
    }
}
