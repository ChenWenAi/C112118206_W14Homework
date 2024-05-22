package mypos;

import java.util.TreeMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Product;
import models.ReadCategoryProduct;

public class AppMenuV3 extends Application {

    //1宣告全域變數)並取得三種菜單的磁磚窗格，隨時被取用。
    private TilePane menuDrink = getProductCategoryMenu("飲料");
    private TilePane menuToast = getProductCategoryMenu("吐司");
    private TilePane menuEggCake = getProductCategoryMenu("蛋餅");
    private TilePane menuDessert = getProductCategoryMenu("點心");

    // 產品菜單磁磚窗格，置放你需要用到的菜單
    public TilePane getProductCategoryMenu(String category) {

        //取得產品清單(呼叫靜態方法取得)
        TreeMap<String, Product> product_dict = ReadCategoryProduct.readProduct();
        //磁磚窗格
        TilePane category_menu = new TilePane(); //
        category_menu.setVgap(10);  //垂直間隙
        category_menu.setHgap(10);
        //設定一個 row有4個columns，放不下就放到下一個row
        category_menu.setPrefColumns(4);

        //將產品清單內容一一置放入產品菜單磁磚窗格
        for (String item_id : product_dict.keySet()) {
            //用if選擇產品類別
            if (product_dict.get(item_id).getCategory().equals(category)) {
                //定義新增一筆按鈕
                Button btn = new Button();

                //width, height 按鈕外框的大小，你要自行調整，讓它美觀。沒有設定外框會大小不一不好看
                btn.setPrefSize(120, 120);
                //btn.setText(product_dict.get(item_id).getName()); //不要顯示文字，顯示圖片就好

                //按鈕元件顯示圖片Creating a graphic (image)
                //讀出圖片
                Image img = new Image("/imgs/" + product_dict.get(item_id).getPhoto());
                ImageView imgview = new ImageView(img);//圖片顯示物件
                imgview.setFitHeight(80); //設定圖片高度，你要自行調整，讓它美觀
                imgview.setPreserveRatio(true); //圖片的寬高比維持

                //Setting a graphic to the button
                btn.setGraphic(imgview); //按鈕元件顯示圖片
                category_menu.getChildren().add(btn);  //放入菜單磁磚窗格

                //定義按鈕事件-->點選一次，就加入購物車，再點選一次，數量要+1
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //新增一筆訂單到order_list (ArrayList)
                        //addToCart(item_id);
                        //order_list.add(new Order("p-109", "新增的果汁", 30, 1));
                        System.out.println(product_dict.get(item_id).getName());
                    }
                });
            }
        }
        return category_menu;
    }//getProductCategoryMenu()

    //2.宣告一個容器(全域變數) menuContainerPane，裝不同種類的菜單，菜單類別選擇按鈕被按下，立即置放該種類的菜單。
    VBox menuContainerPane = new VBox();

    //3.多一個窗格(可以用磁磚窗格最方便)置放菜單類別選擇按鈕，置放於主視窗的最上方區域。
    public TilePane getMenuSelectionContainer() {

        //定義"飲料類"按鈕
        Button btnDrink = new Button();
        btnDrink.setText("飲料");
        btnDrink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(menuDrink);
            }
        });
        //定義"吐司類"按鈕
        Button btnToast = new Button("吐司");
        btnToast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(menuToast);
            }
        });
        //定義"蛋餅類"按鈕
        Button btnEggCake = new Button("蛋餅");
        btnEggCake.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                select_category_menu(e);
            }
        });
        //定義"點心類"按鈕
        Button btnDessert = new Button("點心");
        btnDessert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                select_category_menu(e);
            }
        });

        //使用磁磚窗格，放置前面三個按鈕
        TilePane conntainerCategoryMenuBtn = new TilePane();
        //conntainerCategoryMenuBtn.setAlignment(Pos.CENTER_LEFT);
        //conntainerCategoryMenuBtn.setPrefColumns(6); //
        conntainerCategoryMenuBtn.setVgap(10);
        conntainerCategoryMenuBtn.setHgap(10);
        conntainerCategoryMenuBtn.setHgap(10);

        conntainerCategoryMenuBtn.getChildren().add(btnDrink);
        conntainerCategoryMenuBtn.getChildren().add(btnToast);
        conntainerCategoryMenuBtn.getChildren().add(btnEggCake);
        conntainerCategoryMenuBtn.getChildren().add(btnDessert);

        return conntainerCategoryMenuBtn;
    }// getMenuSelectionContainer()方法

    // 前述三個類別按鈕可以呼叫以下事件
    public void select_category_menu(ActionEvent event) {
        String category = ((Button) event.getSource()).getText();
        menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
        switch (category) {
            case "飲料":
                menuContainerPane.getChildren().add(menuDrink);
                break;
            case "吐司":
                menuContainerPane.getChildren().add(menuToast);
                break;
            case "蛋餅":
                menuContainerPane.getChildren().add(menuEggCake);
                break;
            case "點心":
                menuContainerPane.getChildren().add(menuDessert);
                break;
            default:
                break;
        }
    }

    @Override
    public void start(Stage stage) {

        //根容器 所有的元件都放在裡面container，最後再放進布景中scene，布景再放進舞台中stage
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10)); //外框內墊片
        root.getStylesheets().add("/css/bootstrap3.css");

        //塞入菜單選擇區塊
        //root.getChildren().add(getMenuSelectionContainer());
        TilePane menuSelectionTile = getMenuSelectionContainer();
        root.getChildren().add(menuSelectionTile);

        // 塞入菜單區塊 預設為果汁類
        menuContainerPane.getChildren().add(menuDrink);

        //取得菜單磁磚窗格並放入根容器
        root.getChildren().add(menuContainerPane);

        Scene scene = new Scene(root);
        //Scene scene = new Scene(root, 600,800); 
        stage.setTitle("早餐店");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
