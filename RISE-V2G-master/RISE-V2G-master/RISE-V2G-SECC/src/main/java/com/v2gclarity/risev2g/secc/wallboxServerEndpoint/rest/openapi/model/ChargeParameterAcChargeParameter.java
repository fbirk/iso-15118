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
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.PhysicalValueType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * ChargeParameterAcChargeParameter
 */
@JsonPropertyOrder({
  ChargeParameterAcChargeParameter.JSON_PROPERTY_NOMINAL_VOLTAGE,
  ChargeParameterAcChargeParameter.JSON_PROPERTY_MAX_CURRENT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-03-14T10:53:36.676157800+01:00[Europe/Berlin]")
public class ChargeParameterAcChargeParameter   {
  public static final String JSON_PROPERTY_NOMINAL_VOLTAGE = "nominal-voltage";
  @JsonProperty(JSON_PROPERTY_NOMINAL_VOLTAGE)
  private PhysicalValueType nominalVoltage;

  public static final String JSON_PROPERTY_MAX_CURRENT = "max-current";
  @JsonProperty(JSON_PROPERTY_MAX_CURRENT)
  private PhysicalValueType maxCurrent;

  public ChargeParameterAcChargeParameter nominalVoltage(PhysicalValueType nominalVoltage) {
    this.nominalVoltage = nominalVoltage;
    return this;
  }

  /**
   * Get nominalVoltage
   * @return nominalVoltage
   **/
  @JsonProperty("nominal-voltage")
  @ApiModelProperty(required = true, value = "")
  @NotNull @Valid 
  public PhysicalValueType getNominalVoltage() {
    return nominalVoltage;
  }

  public void setNominalVoltage(PhysicalValueType nominalVoltage) {
    this.nominalVoltage = nominalVoltage;
  }

  public ChargeParameterAcChargeParameter maxCurrent(PhysicalValueType maxCurrent) {
    this.maxCurrent = maxCurrent;
    return this;
  }

  /**
   * Get maxCurrent
   * @return maxCurrent
   **/
  @JsonProperty("max-current")
  @ApiModelProperty(required = true, value = "")
  @NotNull @Valid 
  public PhysicalValueType getMaxCurrent() {
    return maxCurrent;
  }

  public void setMaxCurrent(PhysicalValueType maxCurrent) {
    this.maxCurrent = maxCurrent;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargeParameterAcChargeParameter chargeParameterAcChargeParameter = (ChargeParameterAcChargeParameter) o;
    return Objects.equals(this.nominalVoltage, chargeParameterAcChargeParameter.nominalVoltage) &&
        Objects.equals(this.maxCurrent, chargeParameterAcChargeParameter.maxCurrent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nominalVoltage, maxCurrent);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChargeParameterAcChargeParameter {\n");
    
    sb.append("    nominalVoltage: ").append(toIndentedString(nominalVoltage)).append("\n");
    sb.append("    maxCurrent: ").append(toIndentedString(maxCurrent)).append("\n");
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

