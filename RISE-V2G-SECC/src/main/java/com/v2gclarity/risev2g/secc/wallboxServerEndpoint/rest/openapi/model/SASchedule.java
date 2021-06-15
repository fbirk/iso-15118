/*
 * HSRM Wallbox API
 * This REST-API serves as a interface to a ISO-15118 SECC Server for vehicle to grid communication between an electric vehicle and a charging station. Over this API new charging sessions can be created and controlled. The API propeses methods to control the charging parameters during a charging session.
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: fabian.birk@student.hs-rm.de
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * SASchedule
 */
@JsonPropertyOrder({
  SASchedule.JSON_PROPERTY_P_MAX_SCHEDULE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-04-13T20:22:41.945526600+02:00[Europe/Berlin]")
public class SASchedule   {
  public static final String JSON_PROPERTY_P_MAX_SCHEDULE = "p_max_schedule";
  @JsonProperty(JSON_PROPERTY_P_MAX_SCHEDULE)
  private List<Object> pMaxSchedule = new ArrayList<Object>();

  public SASchedule pMaxSchedule(List<Object> pMaxSchedule) {
    this.pMaxSchedule = pMaxSchedule;
    return this;
  }

  public SASchedule addPMaxScheduleItem(Object pMaxScheduleItem) {
    this.pMaxSchedule.add(pMaxScheduleItem);
    return this;
  }

  /**
   * Get pMaxSchedule
   * @return pMaxSchedule
   **/
  @JsonProperty("p_max_schedule")
  @ApiModelProperty(required = true, value = "")
  @NotNull 
  public List<Object> getpMaxSchedule() {
    return pMaxSchedule;
  }

  public void setpMaxSchedule(List<Object> pMaxSchedule) {
    this.pMaxSchedule = pMaxSchedule;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SASchedule saSchedule = (SASchedule) o;
    return Objects.equals(this.pMaxSchedule, saSchedule.pMaxSchedule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pMaxSchedule);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SASchedule {\n");
    
    sb.append("    pMaxSchedule: ").append(toIndentedString(pMaxSchedule)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
