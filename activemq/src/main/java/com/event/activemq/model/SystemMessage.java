package com.event.activemq.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SystemMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private String source;
    private String message;
}
