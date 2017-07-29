package com.necoutezpas.data;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "int", value = IntField.class),
    @JsonSubTypes.Type(name = "string", value = StringField.class)
})
@ApiModel(description = "Field value")
public interface Field {
    String getType();
}
