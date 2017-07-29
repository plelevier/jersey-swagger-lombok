package com.necoutezpas.data;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonPOJOBuilder(withPrefix = "")
public class Request {
    private final String id;

    private final long count;

    @Singular
    private final ImmutableList<String> values;

    @Singular
    private final ImmutableSet<String> messageIds;
}
