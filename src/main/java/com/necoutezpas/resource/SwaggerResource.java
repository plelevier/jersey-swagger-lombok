package com.necoutezpas.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Swagger;
import io.swagger.util.Json;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/swagger.json")
@Singleton
public class SwaggerResource {

    private final String swaggerDefinition;

    public SwaggerResource() {
        String basePath = "/test";
        String packageName = "com.necoutezpas.resource";
        String version = "1.0.0";
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(version);
        beanConfig.setResourcePackage(packageName);
        beanConfig.setScan( true );
        beanConfig.setBasePath(basePath);
        beanConfig.setDescription( "Test API " + version);
        beanConfig.setTitle( "Test API " + version);
        // the swagger object can be changed to add security definition
        // or to alter the generated mapping
        Swagger swagger = beanConfig.getSwagger();
        swagger.getInfo().setTitle(beanConfig.getTitle());
        swagger.getInfo().setDescription(beanConfig.getDescription());
        swagger.getInfo().setVersion(beanConfig.getVersion());
        swagger.setBasePath(basePath);
        // serialization of the Swagger definition
        try {
            this.swaggerDefinition = Json.mapper().writeValueAsString(swagger);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() throws JsonProcessingException {
        return swaggerDefinition;
    }
}
