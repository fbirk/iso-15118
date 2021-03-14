package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.*;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.*;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.ChargeParameter;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Error;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.InlineObject;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.InlineResponse200;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.SASchedule;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Status;

import java.util.List;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-03-14T16:14:58.460335900+01:00[Europe/Berlin]")
public abstract class CommunicationSessionApiService {
    public abstract Response addCommunicationSession(SecurityContext securityContext) throws NotFoundException;
    public abstract Response communicationSessionSessionIdChargeParameterGet(String sessionId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response communicationSessionSessionIdChargeParameterPost(String sessionId,ChargeParameter body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response communicationSessionSessionIdChargeParameterPut(String sessionId,ChargeParameter body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response communicationSessionSessionIdScheduleGet(String sessionId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response communicationSessionSessionIdStatusGet(String sessionId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response communicationSessionSessionIdStatusPut(String sessionId,InlineObject inlineObject,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findCommunicationSessionByStatus( @NotNull Status status,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getTest(SecurityContext securityContext) throws NotFoundException;
}
