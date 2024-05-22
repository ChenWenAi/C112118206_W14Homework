package mypos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.OrderDetail;


public class AppOrderV0 extends Application{
    
    //ObservableList有新增或刪除都會觸動table的更新，也就是發生任何改變時都被通知
     //以下視窗元件有操作到models目錄下的OrderDetail，因此要修改專案模組設定
    private ObservableList<OrderDetail> order_list;
    //顯示訂單詳情表格
    private TableView<OrderDetail>  table;
    
    
    /*
    //初始化所有元件與事件並將所有元件放入root
    public void initializeOrderTable() {
    }*/
    
    /*
    //表格新增項目刪除項目之操作區塊
     public TilePane getOrderOperationContainer() {

        return ;
    }
    */


    @Override
    public void start(Stage stage){
        
        //根容器 所有的元件都放在裡面container，最後再放進布景中scene，布景再放進舞台中stage
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getStylesheets().add("/css/bootstrap3.css");
        
        //新增項目、刪除項目之操作區塊------------------------
        //定義新增一筆按鈕
        Button btnAdd = new Button();
        btnAdd.setText("新增一筆果汁");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //新增一筆
                order_list.add(new OrderDetail("p-109", "新增的果汁", 30, 1));
                System.out.println("新增一筆");
                //addToCart("p-j-101");
            }
        });

        //定義刪除一筆按鈕
        Button btnDelete = new Button("刪除一筆");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //從table中取得目前被選到的項目
                Object selectedItem = table.getSelectionModel().getSelectedItem();
                //從表格table中或是從order_list刪除這一筆，擇一進行
                //table.getItems().remove(selectedItem);
                order_list.remove(selectedItem);
                //checkTotal();
                System.out.println("執行刪除訂單");
            }
        });

        //放置任務功能按鈕的窗格容器
        TilePane operationBtnTile = new TilePane();
        //operationBtnTile.setAlignment(Pos.CENTER_LEFT);
        //operationBtnTile.setPrefColumns(6);
        operationBtnTile.setVgap(10);
        operationBtnTile.setHgap(10);

        operationBtnTile.getChildren().add(btnAdd);
        operationBtnTile.getChildren().add(btnDelete);
        //新增項目刪除項目之操作區塊---------------

        //套入新增項目刪除項目之操作
        root.getChildren().addAll(operationBtnTile);
        
        //talbe初始化----------------------        
        //訂單陣列串列初始化FXCollections類別有很多靜態方法可以操作ObservableList的"訂單陣列串列"
        order_list = FXCollections.observableArrayList();
        //前面是空的串列，若已知有兩筆訂單可以這樣設定
        order_list = FXCollections.observableArrayList(
                new OrderDetail("p-101", "葡萄汁", 80, 3),
                new OrderDetail("p-102", "番茄汁", 70, 1)
        );

        //也可以這樣加入一筆訂單
        order_list.add(new OrderDetail("p-103", "西瓜汁", 80, 3));

        //表格初始化
        table = new TableView<>();
        table.setEditable(true); //表格允許修改
        table.setPrefHeight(300);
        //表格內置放的資料來自於order_list，依據置放順序顯示
        table.setItems(order_list);

        //table也可以這樣放入訂單
        table.getItems().add(new OrderDetail("p-104", "奇異果汁", 50, 2));

        //定義第一個欄位column"品名"，其值來自於Order物件的某個String變數
        TableColumn<OrderDetail, String> order_item_name = new TableColumn("品名");
        //置放哪個變數值?指定這個欄位 對應到OrderDetail的"product_name"實例變數值
        order_item_name.setCellValueFactory(new PropertyValueFactory("product_name"));

        order_item_name.setPrefWidth(100); //設定欄位寬度
        order_item_name.setMinWidth(100);
        //定義欄位column"價格"
        TableColumn<OrderDetail, Integer> order_item_price = new TableColumn("價格");
        order_item_price.setCellValueFactory(new PropertyValueFactory("product_price"));
        //定義欄位column"數量"
        TableColumn order_item_qty = new TableColumn("數量");
        order_item_qty.setCellValueFactory(new PropertyValueFactory("quantity"));
        
        //這個欄位值內容可以被修改，因為quantity是整數，因此須將整數轉為字串，才能異動OrderDetail物件，否則會報錯!
        order_item_qty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        //edit完成事件
        order_item_qty.setOnEditCommit(new EventHandler<CellEditEvent>(){
            @Override
            public void handle(CellEditEvent event) {
                int row_num = event.getTablePosition().getRow();//哪一筆被修改
                int new_val = (Integer) event.getNewValue(); //改成甚麼數值 需要將物件轉為整數
                OrderDetail target = (OrderDetail) event.getTableView().getItems().get(row_num); //取得該筆果汁傳參考呼叫
                //修改成新的數值 該筆訂單存放於order_list
                target.setQuantity(new_val);

                System.out.println("哪個產品被修改數量：" + order_list.get(row_num).getProduct_name()); //顯示修改後的數值
                System.out.println("數量被修改為：" + order_list.get(row_num).getQuantity()); //顯示修改後的數值

            }
            
        
        });
        
        //把3個欄位加入table中
        table.getColumns().addAll(order_item_name, order_item_price, order_item_qty);

        //表格最後一欄是空白，不要顯示!
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        
         //root套入表格
        root.getChildren().addAll(table);

        Scene scene = new Scene(root);
        stage.setTitle("訂單表格");
        stage.setScene(scene);
        stage.show();

    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
