package com.necoutezpas.resource;

import com.necoutezpas.data.Field;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api
@Path("/request")
public class RequestResource {
    @POST
    @Path("field")
    @ApiOperation(value = "Echo a field.",
            response = Field.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setField(Field field) {
        return Response.ok().entity(field).build();
    }

}
