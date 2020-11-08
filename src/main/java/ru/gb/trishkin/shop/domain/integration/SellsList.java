package ru.gb.trishkin.shop.domain.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellsList {
    private List<Sell> sells;
}
