package com.connect.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Setter(lombok.AccessLevel.NONE)
    private final int id;
    private static int count = 0;

    @NotNull
    @Size(min=1, message = "First name must be at lease 1 character")
    private String firstName;

    @NotNull
    @Size(min=1, message = "Last name must be at lease 1 character")
    private String lastName;

    @NotNull
    @Email(message = "Email is not valid")
    private String email;

    public User() {
        this.id = ++count;
    }
}
