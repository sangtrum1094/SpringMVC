package com.sang.minishops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Setter
@Getter
@Component
@AllArgsConstructor
public class CartItemDto {
    private int id;
    private int quantity;


}