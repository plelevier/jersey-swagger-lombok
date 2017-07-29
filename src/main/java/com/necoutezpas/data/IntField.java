package com.necoutezpas.data;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonPOJOBuilder(withPrefix = "")
@ApiModel(description = "Field Int value")
public class IntField implements Field {

    private final String type = "int";

    private final int data;
}
