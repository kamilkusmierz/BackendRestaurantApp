package com.RestServer.jwtauthentication.resteurant;

import java.io.IOException;
import java.util.List;

import com.RestServer.jwtauthentication.message.request.CreateResteurantForm;
import com.RestServer.jwtauthentication.model.Resteurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResteurantControlerREST {

    @Autowired
    ResteurantControler resteurantControler;

    @GetMapping("/files/{filename:.+}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = resteurantControler.getFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/resteurant")
    public List<Resteurant> getResteurant() {
        return resteurantControler.getResteurant();

    }

    @PostMapping("/saveresteurant")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveResteurant(@ModelAttribute CreateResteurantForm form) throws IOException {

        try {
            return new ResponseEntity<>(resteurantControler.saveResteurant(form), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
