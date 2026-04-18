package com.ITE.parallel_project.dto;

import lombok.Getter;
import lombok.Setter;

public class AddProductRequest {
    @Setter
    @Getter
    private String name;
    @Getter
    @Setter
    private int quantity;
}
