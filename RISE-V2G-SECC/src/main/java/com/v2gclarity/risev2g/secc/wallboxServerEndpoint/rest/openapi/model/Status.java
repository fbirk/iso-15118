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
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The current status of a charging session.
 */
public enum Status {
  
  CHARGING("Charging"),
  
  PAUSED("Paused"),
  
  STOPED("Stoped");

  private String value;

  Status(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Status fromValue(String value) {
    for (Status b : Status.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

