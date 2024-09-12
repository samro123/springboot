package com.sam.hello_spring_boot.dto.request;

import com.sam.hello_spring_boot.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;
    @DobConstraint(min = 2,message = "INVALID_DOB")
    LocalDate dob;
    List<String> roles;
}
