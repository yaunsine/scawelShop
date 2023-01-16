package com.hiido.shop.scawelshop;


/**
 * @description: TODO 字段转换mybatis
 * @author YSLin
 * @date 2023/1/12 17:25
 */
public class StringTests {
    public static void main(String[] args) {
        String sql = "ad_id,\n" +
                "additional_image_link,\n" +
                "adult,\n" +
                "advertiser_country,\n" +
                "advertiser_id,\n" +
                "advertiser_name,\n" +
                "age_group,\n" +
                "availability,\n" +
                "availability_date,\n" +
                "brand,\n" +
                "catalog_id,\n" +
                "color,\n" +
                "`condition`,\n" +
                "custom_label0,\n" +
                "custom_label1,\n" +
                "custom_label2,\n" +
                "custom_label3,\n" +
                "custom_label4,\n" +
                "description,\n" +
                "energy_efficiency_class,\n" +
                "energy_efficiency_class_max,\n" +
                "energy_efficiency_class_min,\n" +
                "expiration_date,\n" +
                "gender,\n" +
                "google_product_category,\n" +
                "gtin,\n" +
                "identifier_exists,\n" +
                "image_link,\n" +
                "installment,\n" +
                "is_bundle,\n" +
                "is_deleted,\n" +
                "item_group_id,\n" +
                "item_list_id,\n" +
                "item_list_name,\n" +
                "last_updated,\n" +
                "link,\n" +
                "link_code,\n" +
                "loyalty_points,\n" +
                "material,\n" +
                "maximum_handling_time,\n" +
                "minimum_handling_time,\n" +
                "mobile_link,\n" +
                "mpn,\n" +
                "multipack,\n" +
                "pattern,\n" +
                "price,\n" +
                "product_type,\n" +
                "sale_price,\n" +
                "sale_price_effective_date_end,\n" +
                "sale_price_effective_date_start,\n" +
                "serviceable_areas,\n" +
                "shipping,\n" +
                "`size`,\n" +
                "size_system,\n" +
                "size_type,\n" +
                "source_feed_type,\n" +
                "target_country,\n" +
                "tax,\n" +
                "title,\n" +
                "unit_pricing_base_measure,\n" +
                "unit_pricing_measure";
        sql = sql.replace("\n", "#{item.").replace(",", "},");
        String[] slist = sql.split("_");
        String result = "#{item." + slist[0];
        for (int i = 1; i < slist.length; i++) {
            slist[i] = firstToUpper2(slist[i]);
            result += slist[i];
        }
        result = result + "}";
        System.out.println(result + "}");
    }

    public static String firstToUpper2(String s){
        char[] arr = s.toCharArray();
        arr[0]-=32;
        return String.valueOf(arr);
    }
}
