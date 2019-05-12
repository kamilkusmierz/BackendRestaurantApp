package com.RestServer.jwtauthentication.resteurant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.RestServer.jwtauthentication.message.request.CreateResteurantForm;
import com.RestServer.jwtauthentication.message.response.ResponseMessage;
import com.RestServer.jwtauthentication.model.Resteurant;
import com.RestServer.jwtauthentication.model.Tables;
import com.RestServer.jwtauthentication.repository.ResteurantRepository;
import com.RestServer.jwtauthentication.repository.TablesRepository;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class ResteurantControlerImp implements ResteurantControler {

    @Autowired
    ResteurantRepository resteurantRepository;
    @Autowired
    TablesRepository tablesRepository;
    public static final Resource PICTURES_DIR = new FileSystemResource("./pictures");

    @Override
    public ResponseMessage saveResteurant(CreateResteurantForm form) throws IOException {
        // TODO: zmienic wspólzedne stolików z stringa na obiekt punkt z X,Y;
        if (form.getFile().isEmpty() || !isImage(form.getFile())) {
            return new ResponseMessage("Fail -> File is not Image");
        }
        if (resteurantRepository.existsByName(form.getResteurantname())) {
            return new ResponseMessage("Fail -> Name is already taken!");
        }
        String fileExtention = getFileExtension(form.getFile().getOriginalFilename());
        Resteurant resteurant = new Resteurant(form.getResteurantname(),
                copyFileToPictures(form.getFile(), fileExtention).getFilename(), form.getStreetNumber(),
                form.getHouseNumber(), form.getCode(), form.getCityName());
        resteurantRepository.save(resteurant);
        System.out.print(form.getTables());
        if (form.getTables() != "") {
            String[] lista = form.getTables().split(";");
            int i = 0;
            for (String l : lista) {
                i++;
                String tmp[] = l.split(",");
                Tables tabletmp = new Tables(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), "stolik" + i,
                        resteurant);
                tablesRepository.save(tabletmp);
                // System.out.print("Dodano stolik");
            }
        }
        return new ResponseMessage("Add Tabele");
    }

    @Override
    public List<Resteurant> getResteurant() {
        List<Resteurant> resteurant = new ArrayList<>();
        resteurantRepository.findAll().forEach(resteurant::add);
        for (Resteurant r : resteurant) {

            for (Tables t : r.getTables()) {
                t.setOwner(null);
                t.setOrders(null);
            }
        }
        return resteurant;
    }

    @Override
    public Resource getFile(String filename) {
        Resteurant resteurant = resteurantRepository.findByName(filename).get(0);
        Resource file = loadFile(resteurant.getFilename());
        return file;
    }

    public Resource loadFile(String filename) {
        try {
            Path rootLocation = Paths.get("./pictures");
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                System.out.println(rootLocation + " " + filename);
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    private Resource copyFileToPictures(MultipartFile file, String fileExtention) throws IOException {

        File tempFile = File.createTempFile("pic", fileExtention, PICTURES_DIR.getFile());
        try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }

        return (Resource) new FileSystemResource(tempFile);
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

}