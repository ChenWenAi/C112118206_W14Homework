package models;

import java.util.TreeMap;
import models.Product;

public class ReadCategoryProduct {

    //準備好產品清單  
    public static TreeMap<String, Product> readProduct() {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊
        //放所有產品  產品編號  產品物件
        TreeMap<String, Product> product_dict = new TreeMap<>();
        String[][] product_array = {
            {"p-d-101", "飲料", "紅茶", "20", "blacktea.png", "產品描述"},
            {"p-d-102", "飲料", "奶茶", "25", "milktea.png", "產品描述"},
            {"p-d-103", "飲料", "豆漿", "25", "soymilk.png", "產品描述"},
            {"p-t-105", "吐司", "草莓吐司", "30", "strawberrytoast.png", "產品描述"},
            {"p-t-106", "吐司", "巧克力吐司", "30", "chocotoast.png", "產品描述"},
            {"p-t-107", "吐司", "肉蛋吐司", "60", "meattoast.png", "產品描述"},
            {"p-e-108", "蛋餅", "薯餅蛋餅", "45", "potatoeggcake.jpg", "產品描述"},
            {"p-e-109", "蛋餅", "玉米蛋餅", "45", "corneggcake.JPG", "產品描述"},
            {"p-e-110", "蛋餅", "培根蛋餅", "45", "baconeggpancake.jpg", "產品描述"},
            {"p-dt-112", "點心", "雞塊", "50", "chickennuggets.png", "產品描述"},
            {"p-dt-113", "點心", "熱狗", "30", "hotdog.jpg", "產品描述"},
            {"p-dt-114", "點心", "蘿蔔糕", "40", "carrotcake.jpg", "產品描述"}
        };

        //一筆放入字典變數product_dict中
        for (String[] item : product_array) {
            Product product = new Product(
                    item[0],
                    item[1],
                    item[2],
                    Integer.parseInt(item[3]), //價格轉為int
                    item[4],
                    item[5]);
            //將這一筆放入字典變數product_dict中 
            product_dict.put(product.getProduct_id(), product);
        }
        return product_dict;
    }
}
