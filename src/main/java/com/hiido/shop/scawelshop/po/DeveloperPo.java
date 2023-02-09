package com.hiido.shop.scawelshop.po;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeveloperPo {
    int id;
    String author;
    String introduction;
    String imgSrc;
    String link;
    String score;
    String price;
    String programTechId;

}
