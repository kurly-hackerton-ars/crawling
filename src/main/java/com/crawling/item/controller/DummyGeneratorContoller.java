package com.crawling.item.controller;

import com.crawling.item.config.ItemLists;
import com.crawling.item.dto.ProductInfoDto;
import com.crawling.item.dto.PurchaseDto;
import com.crawling.item.dto.UserInfoDto;
import com.crawling.item.helper.RandomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class DummyGeneratorContoller {

    private final ItemLists itemLists;

    @GetMapping("/dummy-generate")
    public void dummyGenerator() {
        String[] grades = {"bronze", "silver", "gold", "diamond"};
        Random random = new Random();

        List<ProductInfoDto> products = itemLists.getProducts().get(0);

        int randomInt = RandomHelper.randomIntInListSize(products.size());

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(RandomHelper.randomLong())
                .name(RandomHelper.randomString())
                .grade(grades[random.nextInt(grades.length)])
                .createdDate(LocalDateTime.now())
                .build();

        ProductInfoDto productInfo = ProductInfoDto.builder()
                .id(RandomHelper.randomLong())
                .imgUrl(products.get(randomInt).getImgUrl())
                .prodName(products.get(randomInt).getProdName())
                .originPrice(products.get(randomInt).getOriginPrice())
                .salePrice(products.get(randomInt).getSalePrice())
                .category(products.get(randomInt).getCategory())
                .insertDate(LocalDateTime.now())
                .count(RandomHelper.randomInt())
                .soldOut(RandomHelper.randomBoolean())
                .build();

        PurchaseDto purchaseDto = PurchaseDto.builder()
                .id(RandomHelper.randomLong())
                .userId(userInfoDto.getId())
                .productId(productInfo.getId())
                .createdDate(LocalDateTime.now())
                .build();



        System.out.println(userInfoDto.toString());
        System.out.println(productInfo.toString());
        System.out.println(purchaseDto.toString());

    }

}