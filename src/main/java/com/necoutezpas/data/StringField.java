package com.necoutezpas.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.collect.ImmutableList;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonPOJOBuilder(withPrefix = "")
@ApiModel(description = "Field String value")
public class StringField implements Field {

    private final String type = "string";

    private final String data;

    @Singular
    @NonNull
    @JsonProperty(required = true)
    private final ImmutableList<String> messages;
}
