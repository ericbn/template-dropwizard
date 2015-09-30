package com.avenuecode.template.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class TemplateController {

    @GET
    public String get() {
        return "Hello World";
    }
}
