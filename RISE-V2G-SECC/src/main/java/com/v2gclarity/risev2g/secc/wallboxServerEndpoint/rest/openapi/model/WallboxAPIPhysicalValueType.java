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
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * WallboxAPIPhysicalValueType
 */
@JsonPropertyOrder({
  WallboxAPIPhysicalValueType.JSON_PROPERTY_MULTIPLIER,
  WallboxAPIPhysicalValueType.JSON_PROPERTY_VALUE,
  WallboxAPIPhysicalValueType.JSON_PROPERTY_UNIT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2021-04-13T20:22:41.945526600+02:00[Europe/Berlin]")
public class WallboxAPIPhysicalValueType   {
  public static final String JSON_PROPERTY_MULTIPLIER = "multiplier";
  @JsonProperty(JSON_PROPERTY_MULTIPLIER)
  private Integer multiplier;

  public static final String JSON_PROPERTY_VALUE = "value";
  @JsonProperty(JSON_PROPERTY_VALUE)
  private Integer value;

  /**
   * Gets or Sets unit
   */
  public enum UnitEnum {
    V("V"),
    
    A("A"),
    
    W("W"),
    
    WH("Wh");

    private String value;

    UnitEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UnitEnum fromValue(String value) {
      for (UnitEnum b : UnitEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_UNIT = "unit";
  @JsonProperty(JSON_PROPERTY_UNIT)
  private UnitEnum unit;

  public WallboxAPIPhysicalValueType multiplier(Integer multiplier) {
    this.multiplier = multiplier;
    return this;
  }

  /**
   * Get multiplier
   * @return multiplier
   **/
  @JsonProperty("multiplier")
  @ApiModelProperty(value = "")
  
  public Integer getMultiplier() {
    return multiplier;
  }

  public void setMultiplier(Integer multiplier) {
    this.multiplier = multiplier;
  }

  public WallboxAPIPhysicalValueType value(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   **/
  @JsonProperty("value")
  @ApiModelProperty(value = "")
  
  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public WallboxAPIPhysicalValueType unit(UnitEnum unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Get unit
   * @return unit
   **/
  @JsonProperty("unit")
  @ApiModelProperty(value = "")
  
  public UnitEnum getUnit() {
    return unit;
  }

  public void setUnit(UnitEnum unit) {
    this.unit = unit;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WallboxAPIPhysicalValueType wallboxAPIPhysicalValueType = (WallboxAPIPhysicalValueType) o;
    return Objects.equals(this.multiplier, wallboxAPIPhysicalValueType.multiplier) &&
        Objects.equals(this.value, wallboxAPIPhysicalValueType.value) &&
        Objects.equals(this.unit, wallboxAPIPhysicalValueType.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(multiplier, value, unit);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WallboxAPIPhysicalValueType {\n");
    
    sb.append("    multiplier: ").append(toIndentedString(multiplier)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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

