package com.alten.shop.alterShop.payload;

import java.util.List;

public class ProductResponse {
    private List<ReturnMessageDto> returnMessageDto;

    public List<ReturnMessageDto> getReturnMessageDto() {
        return returnMessageDto;
    }

    public void setReturnMessageDto(List<ReturnMessageDto> returnMessageDto) {
        this.returnMessageDto = returnMessageDto;
    }
}
