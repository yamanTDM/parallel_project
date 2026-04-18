package com.ITE.parallel_project.dto;

import lombok.Getter;
import lombok.Setter;

public class AddToCartRequest {


    @Setter
    @Getter
    private int productId;


    @Setter
    @Getter
    private int quantity;

}
