package mypos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class TableViewExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<Person> tableView = new TableView<>();

        // 創建列
        TableColumn<Person, String> column1 = new TableColumn<>("名字");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> column2 = new TableColumn<>("姓氏");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Person, Integer> column3 = new TableColumn<>("年齡");
        column3.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        // 設置列為可編輯
        tableView.setEditable(true);
        column3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        column3.setOnEditCommit(
            event -> {
                Person person = event.getRowValue();
                person.setAge(event.getNewValue());
                System.out.println("新的年齡:"+person.getAge());
            }
        );

        // 將列添加到表格中
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        // 添加數據到表格
        tableView.getItems().add(new Person("John", "Doe", 30));
        tableView.getItems().add(new Person("Jane", "Deer", 25));

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class Person {
        private String firstName;
        private String lastName;
        private int age;

        public Person(String fName, String lName, int age) {
            this.firstName = fName;
            this.lastName = lName;
            this.age = age;
        }

        // Getters and Setters
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
