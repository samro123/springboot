package com.sam.hello_spring_boot.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // thong bao json, fill nao null se k kem vao json
public class ApiResponse<T> {
    int code=1000;
    String message;
    T result;

}
