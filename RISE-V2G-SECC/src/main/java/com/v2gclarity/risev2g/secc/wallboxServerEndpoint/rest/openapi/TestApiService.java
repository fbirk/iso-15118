package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.*;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.*;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Error;

import java.util.List;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-03-14T08:26:34.708650100+01:00[Europe/Berlin]")
public abstract class TestApiService {
    public abstract Response getTest(SecurityContext securityContext) throws NotFoundException;
}
