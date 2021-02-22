package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.impl;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.*;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.*;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.ChargeParameter;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Error;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.InlineObject;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.OfferedServices;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.SASchedule;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Status;

import java.util.List;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-02-22T09:14:36.215937400+01:00[Europe/Berlin]")
public class CommunicationSessionApiServiceImpl extends CommunicationSessionApiService {
    @Override
    public Response addCommunicationSession(OfferedServices body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response communicationSessionSessionIdChargeParameterGet(String sessionId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response communicationSessionSessionIdChargeParameterPost(String sessionId, ChargeParameter body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response communicationSessionSessionIdChargeParameterPut(String sessionId, ChargeParameter body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response communicationSessionSessionIdScheduleGet(String sessionId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response communicationSessionSessionIdStatusGet(String sessionId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response communicationSessionSessionIdStatusPut(String sessionId, InlineObject inlineObject, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
