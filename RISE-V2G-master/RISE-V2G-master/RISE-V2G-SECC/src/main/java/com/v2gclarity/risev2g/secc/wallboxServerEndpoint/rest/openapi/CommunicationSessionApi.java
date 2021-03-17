package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.*;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.CommunicationSessionApiService;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.factories.CommunicationSessionApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.ChargeParameter;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Error;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.InlineObject;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.InlineResponse200;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.SASchedule;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Status;

import java.util.Map;
import java.util.List;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/communication-session")


@io.swagger.annotations.Api(description = "the communication-session API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-03-16T21:26:11.753974200+01:00[Europe/Berlin]")
public class CommunicationSessionApi  {
   private final CommunicationSessionApiService delegate;

   public CommunicationSessionApi(@Context ServletConfig servletContext) {
      CommunicationSessionApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("CommunicationSessionApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (CommunicationSessionApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = CommunicationSessionApiServiceFactory.getCommunicationSessionApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Generate a new communication session", notes = "", response = String.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "petstore_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:pets", description = "read your pets")
        })
    }, tags={ "communication-session", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response addCommunicationSession(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addCommunicationSession(securityContext);
    }
    @GET
    @Path("/{session-id}/charge-parameter")
    
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "Get the currently set charge parameters", notes = "", response = ChargeParameter.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "petstore_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:pets", description = "read your pets")
        })
    }, tags={ "communication-session charge-parameter", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Expected response to a valid request", response = ChargeParameter.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response communicationSessionSessionIdChargeParameterGet(@ApiParam(value = "The id of the V2G communication session.", required = true) @PathParam("session-id") @NotNull  String sessionId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.communicationSessionSessionIdChargeParameterGet(sessionId, securityContext);
    }
    @POST
    @Path("/{session-id}/charge-parameter")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create new charge parameters for a specific V2G communication session", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "petstore_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:pets", description = "read your pets")
        })
    }, tags={ "communication-session charge-parameter", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response communicationSessionSessionIdChargeParameterPost(@ApiParam(value = "The id of the V2G communication session.", required = true) @PathParam("session-id") @NotNull  String sessionId,@ApiParam(value = "A set of charge parameters for AC or DC charging", required = true) @NotNull @Valid  ChargeParameter body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.communicationSessionSessionIdChargeParameterPost(sessionId, body, securityContext);
    }
    @PUT
    @Path("/{session-id}/charge-parameter")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update the charge parameters for a specific V2G communication session", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "petstore_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:pets", description = "read your pets")
        })
    }, tags={ "communication-session charge-parameter", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response communicationSessionSessionIdChargeParameterPut(@ApiParam(value = "The id of the V2G communication session.", required = true) @PathParam("session-id") @NotNull  String sessionId,@ApiParam(value = "A set of charge parameters for AC or DC charging", required = true) @NotNull @Valid  ChargeParameter body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.communicationSessionSessionIdChargeParameterPut(sessionId, body, securityContext);
    }
    @GET
    @Path("/{session-id}/schedule")
    
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "Get the currently set charge schedule", notes = "", response = SASchedule.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "petstore_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:pets", description = "read your pets")
        })
    }, tags={ "communication-session schedule", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Expected response to a valid request", response = SASchedule.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response communicationSessionSessionIdScheduleGet(@ApiParam(value = "The id of the V2G communication session.", required = true) @PathParam("session-id") @NotNull  String sessionId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.communicationSessionSessionIdScheduleGet(sessionId, securityContext);
    }
    @GET
    @Path("/{session-id}/status")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get the current status of the communication session.", notes = "", response = Status.class, tags={ "communication-session status", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Expected response to a valid request", response = Status.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response communicationSessionSessionIdStatusGet(@ApiParam(value = "The id of the V2G communication session.", required = true) @PathParam("session-id") @NotNull  String sessionId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.communicationSessionSessionIdStatusGet(sessionId, securityContext);
    }
    @PUT
    @Path("/{session-id}/status")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Change the status to 'paused' for a given time interval.", notes = "", response = Void.class, tags={ "communication-session status", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response communicationSessionSessionIdStatusPut(@ApiParam(value = "The id of the V2G communication session.", required = true) @PathParam("session-id") @NotNull  String sessionId,@ApiParam(value = "") @Valid  InlineObject inlineObject,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.communicationSessionSessionIdStatusPut(sessionId, inlineObject, securityContext);
    }
    @GET
    @Path("/findByStatus")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds Sessions by their status", notes = "Multiple status values can be provided with comma separated strings", response = InlineResponse200.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "petstore_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:pets", description = "read your pets")
        })
    }, tags={ "communication-session", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = InlineResponse200.class, responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response findCommunicationSessionByStatus(@ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues="Charging, Paused, Stoped") @QueryParam("status") @NotNull @Valid  Status status,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findCommunicationSessionByStatus(status, securityContext);
    }
    @GET
    @Path("/test")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Test the API connection", notes = "", response = String.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "petstore_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "read:pets", description = "read your pets")
        })
    }, tags={ "test", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "unexpected error", response = Error.class)
    })
    public Response getTest(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getTest(securityContext);
    }
}
