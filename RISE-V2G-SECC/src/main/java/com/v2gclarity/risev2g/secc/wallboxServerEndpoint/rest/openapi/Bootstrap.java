package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import io.swagger.models.auth.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("OpenAPI Server")
      .description("This REST-API serves as a interface to a ISO-15118 SECC Server for vehicle to grid communication between an electric vehicle and a charging station. Over this API new charging sessions can be created and controlled. The API propeses methods to control the charging parameters during a charging session.")
      .termsOfService("")
      .contact(new Contact()
        .email("fabian.birk@student.hs-rm.de"))
      .license(new License()
        .name("Apache 2.0")
        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

    ServletContext context = config.getServletContext();
    Swagger swagger = new Swagger().info(info);

    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}
