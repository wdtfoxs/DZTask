package ru.dz.restapi.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestResponse {
    private int code;
    private String message;
}