package com.RestServer.jwtauthentication.resteurant;

import java.io.IOException;
import java.util.List;

import com.RestServer.jwtauthentication.message.request.CreateResteurantForm;
import com.RestServer.jwtauthentication.message.response.ResponseMessage;
import com.RestServer.jwtauthentication.model.Resteurant;

import org.springframework.core.io.Resource;

public interface ResteurantControler {
    public ResponseMessage saveResteurant(CreateResteurantForm form) throws IOException;

    public List<Resteurant> getResteurant();

    public Resource getFile(String filename);
}