package com.yuxin.zhouyang;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 企业中的枚举使用方法
 */
@AllArgsConstructor
@Getter
public enum CountryEnum {
    ONE(1,"齐国"),TWO(2,"赵国"),THREE(3,"韩国"),FOUR(4,"燕国"),FIVE(5,"楚国"),SIX(6,"魏国");
    private int id;
    private String countryName;
    public static CountryEnum forEach_CountryEnum(int id){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum: countryEnums) {
            if (id == countryEnum.getId()){
                return countryEnum;
            }
        }
        return null;
    }
}
