package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-03-16T21:26:11.753974200+01:00[Europe/Berlin]")
public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
