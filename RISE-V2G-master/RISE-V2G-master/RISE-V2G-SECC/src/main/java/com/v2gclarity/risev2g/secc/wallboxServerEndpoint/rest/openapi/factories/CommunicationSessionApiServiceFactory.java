package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.factories;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.WallboxServerEndpoint;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.CommunicationSessionApiService;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-03-14T10:53:36.676157800+01:00[Europe/Berlin]")
public class CommunicationSessionApiServiceFactory {
    private static final CommunicationSessionApiService service = new WallboxServerEndpoint();

    public static CommunicationSessionApiService getCommunicationSessionApi() {
        return service;
    }
}
