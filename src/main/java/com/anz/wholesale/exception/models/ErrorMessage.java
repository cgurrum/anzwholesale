package com.anz.wholesale.exception.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ErrorMessage {
    private String message;
}
