package com.example.movieticketbookingmanage;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.SpinnerValueFactory;



import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.Time;


public class dashboardController implements Initializable {

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_InsertBtn;

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_date;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_duration;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_genre;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_movieTitle;

    @FXML
    private DatePicker addMovies_date;

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private Button addMovies_import;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;

    @FXML
    private TableView<moviesData> addMovies_tableView;

    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private TableView<moviesData> addScreening_tableView;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private Button availableMovies_clearBtn;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_genre;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_movieTitlle;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_showingDate;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<Integer> availableMovies_normalClass_quantity;

    @FXML
    private Button availableMovies_receiptBtn;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Spinner<Integer> availableMovies_specialClass_quantity;

    @FXML
    private TableView<moviesData> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Button dashboard_close;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private Button customers_btn;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableView<customerData> customer_tableView;

    @FXML
    private TableColumn<customerData, String> customers_col_date;

    @FXML
    private TableColumn<customerData, String> customers_col_movieTitle;

    @FXML
    private TableColumn<customerData, String> customers_col_ticketNumber;

    @FXML
    private TableColumn<customerData, String> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private AnchorPane dashboard_availableMovies;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane dashboard_totalEarnToday;

    @FXML
    private AnchorPane dashboard_totalSoldTicket;

    @FXML
    private Button editScreening_btn;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_current;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_duration;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_genre;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_delete;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_update;

    @FXML
    private Button dashboard_minimize;

    @FXML
    private Button signout;

    @FXML
    private AnchorPane topForm;

    @FXML
    private Label username;

    private Image image;

    private double x = 0;
    private double y = 0;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private SpinnerValueFactory<Integer> spinner1;
    private SpinnerValueFactory<Integer> spinner2;

    private float price1 = 0;
    private float price2 = 0;
    private float total = 0;
    private int qty1 = 0;
    private int qty2 = 0;


/*
    public void receipt(){

        if(total>0){

        HashMap hash = new HashMap();
        hash.put("receipt", num);
        try{
            JasperDesign jDesign = JRXmlLoader.loader("/Users/inzurahym/IdeaProjects/main/src/movieticketbookingmanage/src/main/resources/com/example/movieticketbookingmanage/report.jrxml");//локация вашего джаспер файла
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, connect);

            JasperViewer.viewReport(jPrint,false);

        } catch(JRException ex){
            Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message ");
        alert.setHeaderText(null);
        alert.setContentText("INvalid");
        alert.showAndWait();
    }
    }
    private int num;
*/

    public void searchCustomer() {
        FilteredList<customerData> filter = new FilteredList<>(custList, e -> true);
        customers_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateCustomer ->{
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if(predicateCustomer.getId().toString().contains(searchKey)){
                    return true;
                }else if(predicateCustomer.getTitle().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateCustomer.getDate().toString().contains(searchKey)){
                    return true;
                }
                return false;
            });
            SortedList<customerData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(customer_tableView.comparatorProperty());
            customer_tableView.setItems(sortData);
        });
    }

    public void deleteCustomer(){
        String sql = "DELETE FROM customer WHERE id = '" + customers_ticketNumber.getText() + "'";

        connect = database.connectDb();

        try{

            Alert alert;

            statement = connect.createStatement();
            if(customers_ticketNumber.getText().isEmpty()
                    || customers_movieTitle.getText().isEmpty()
                    || customers_date.getText().isEmpty()
                    || customers_time.getText().isEmpty()){

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the customer first");
                alert.showAndWait();

            } else{

                alert = new Alert (AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete" + customers_movieTitle.getText() + "?");

                Optional<ButtonType> option  = alert.showAndWait();
                if(option.get() == ButtonType.OK){
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully removed");
                    alert.showAndWait();

                    showCustomerList();
                    clearCustomer();
                } else {
                    return;
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCustomer(){
        customers_ticketNumber.setText("");
        customers_movieTitle.setText("");
        customers_date.setText("");
        customers_time.setText("");

    }

    public void selectCustomerList(){
        customerData custD = customer_tableView.getSelectionModel().getSelectedItem();
        int num = customer_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1 ) < -1){
            return;
        }

        customers_ticketNumber.setText(String.valueOf(custD.getId()));
        customers_movieTitle.setText(custD.getTitle());
        customers_date.setText(String.valueOf(custD.getDate()));
        customers_time.setText(String.valueOf(custD.getTime()));

    }


    public ObservableList<customerData> customerList(){
        ObservableList<customerData> customerL = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer";
        connect = database.connectDb();

        try{
            customerData customerD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                customerD = new customerData(result.getInt("id"),
                        result.getString("type"),
                        result.getString("movieTitle"),
                        result.getInt("quantity"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getTime("time"));

                customerL.add(customerD);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return customerL;

    }

    private ObservableList<customerData> custList;
    public void showCustomerList(){
        custList = customerList();
        customers_col_ticketNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        customers_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customers_col_time.setCellValueFactory(new PropertyValueFactory<>("time"));

        customer_tableView.setItems(custList);
    }

    private int num;
    private int qty;

    public void buy(){

        String sql = "INSERT INTO customer (type,movieTitle, quantity,total,date, time) VALUES (?,?,?,?,?,?)";

        connect = database.connectDb();
        String type = "";
        if(price1 > 0 && price2 == 0){
            type = "Special Class";
        }else if(price2 > 0 && price1 == 0){
            type = "Normal Class";
        }else if(price1 > 0 && price2 > 0){
            type = "Special & Normal Class";
        }

        java.sql.Date setDate = new java.sql.Date(System.currentTimeMillis());

        try{

            LocalTime localTime = LocalTime.now();
            Time time  = Time.valueOf(localTime);
            qty = qty1 + qty2;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, type);
            prepare.setString(2, availableMovies_title.getText());
            prepare.setString(3, String.valueOf(qty));
            prepare.setString(4, String.valueOf(total));
            prepare.setString(5, String.valueOf(setDate));
            prepare.setString(6, String.valueOf(time));

            Alert alert;

            if(availableMovies_imageView.getImage() == null
                    || availableMovies_title.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first!");
                alert.showAndWait();
            }
            else if(price1 == 0 && price2 == 0){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please indicate the quantity of ticket you want to purchase.");
                alert.showAndWait();
            }else{

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully purchase!");
                alert.showAndWait();

                String sql1 = "SELECT * FROM customer";

                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();

                num = 0;

                while(result.next()){
                    num = result.getInt("id");
                }
                String sql2 = "INSERT INTO customer_info (customer_id,type,,quantity, total,movieTitle) VALUES (?,?,?,?,?)";

                prepare = connect.prepareStatement(sql2);
                prepare.setString(1,String.valueOf(num));
                prepare.setString(2,type);
                prepare.setString(3,String.valueOf(qty));
                prepare.setString(4,String.valueOf(total));
                prepare.setString(5,availableMovies_title.getText());
                prepare.execute();

                clearPurchaseTicketInfo();

            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void clearPurchaseTicketInfo(){

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);

        availableMovies_specialClass_quantity.setValueFactory(spinner1);
        availableMovies_normalClass_quantity.setValueFactory(spinner2);

        availableMovies_specialClass_price.setText("$0.0");
        availableMovies_normalClass_price.setText("$0.0");
        availableMovies_total.setText("$0.0");

        availableMovies_imageView.setImage(null);
        availableMovies_title.setText("");

    }

    public void showSpinnerValue(){

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);

        availableMovies_specialClass_quantity.setValueFactory(spinner1);
        availableMovies_normalClass_quantity.setValueFactory(spinner2);

    }

    public void getSpinnerValue(MouseEvent event){
        qty1 = availableMovies_specialClass_quantity.getValue();
        qty2 = availableMovies_normalClass_quantity.getValue();

        price1 = (qty1 * 2500);
        price2 = (qty2 * 1000);

        total = (price1 + price2);
        availableMovies_specialClass_price.setText(String.valueOf(price1) + "kzt");
        availableMovies_normalClass_price.setText(String.valueOf(price2) + "kzt");

        availableMovies_total.setText(String.valueOf(total) + "kzt");
    }

    public ObservableList<moviesData> availableMoviesList(){

        ObservableList<moviesData> listAvMovies = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie WHERE current = 'Showing'";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            moviesData movD;

            while(result.next()){
                movD = new moviesData(result.getInt("id")
                        , result.getString("movieTitle")
                        ,result.getString("genre")
                        ,result.getString("duration")
                        ,result.getString("image")
                        ,result.getDate("date")
                        ,result.getString("current"));

                listAvMovies.add(movD);
            }

        }catch(Exception e){e.printStackTrace();}
        return listAvMovies;
    }

    private ObservableList<moviesData> availableMoviesList;
    public void showAvailableMovies(){

        availableMoviesList = availableMoviesList();

        availableMovies_col_movieTitlle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableMovies_col_showingDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        availableMovies_tableView.setItems(availableMoviesList);
    }

    public void selectAvailableMovies(){

        moviesData movD = availableMovies_tableView.getSelectionModel().getSelectedItem();
        int num = availableMovies_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){
            return;
        }

        availableMovies_movieTitle.setText(movD.getTitle());
        availableMovies_genre.setText(movD.getGenre());
        availableMovies_date.setText(String.valueOf(movD.getDate()));

        getData.path = movD.getImage();
        getData.title = movD.getTitle();
    }

    public void selectMovie(){

        Alert alert;

        if(availableMovies_movieTitle.getText().isEmpty()
            ||availableMovies_genre.getText().isEmpty()
            ||availableMovies_date.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the movie first");
            alert.showAndWait();
        } else {
            String uri = "file:" + getData.path;

            image = new Image(uri, 136, 180, false, true);
            availableMovies_imageView.setImage(image);

            availableMovies_title.setText(getData.title);

            availableMovies_movieTitle.setText("");
            availableMovies_genre.setText("");
            availableMovies_date.setText("");

        }
    }



    private String[] currentList = {"Showing", "End Showing"};

    public void comboBox(){

        List<String> listCurrent = new ArrayList<>();

        for(String data: currentList){
            listCurrent.add(data);
        }

        ObservableList listC = FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);
    }

    public void updateEditScreening(){
        String sql = "UPDATE movie SET current = '"
                + editScreening_current.getSelectionModel().getSelectedItem()
                + "'WHERE movieTitle = '" + editScreening_title.getText() + "'";

        connect = database.connectDb();

        Alert alert;


        try{
            statement = connect.createStatement();

            if(editScreening_title.getText().isEmpty()
                || editScreening_imageView.getImage() == null
                || editScreening_current.getSelectionModel().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please Select the movie first");
                alert.showAndWait();
            } else{
                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update!");
                alert.showAndWait();

                showEditScreening();
                clearEditScreening();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clearEditScreening(){
        editScreening_title.setText("");
        editScreening_imageView.setImage(null);
        //editScreening_current.setSelectionModel(null);
    }

    public void searchEditScreening(){
         FilteredList<moviesData> filter = new FilteredList<>(editScreeningL, e -> true);
            editScreening_search.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(predicateMoviesData -> {
                    if(newValue.isEmpty() || newValue == null) {
                    return true;            }
                    String searchKey = newValue.toLowerCase();
                    if(predicateMoviesData.getTitle().toLowerCase().contains(searchKey)) {
                        return true;
                    }else if(predicateMoviesData.getGenre().toLowerCase().contains(searchKey)) {
                        return true;
                    }else if(predicateMoviesData.getDuration().toLowerCase().contains(searchKey)) {
                        return true;
                    }else if(predicateMoviesData.getCurrent().toLowerCase().contains(searchKey)) {
                        return true;
                    }
                    return false;
                });
                SortedList<moviesData> sortData = new SortedList<>(filter);
                sortData.comparatorProperty().bind(addScreening_tableView.comparatorProperty());
                addScreening_tableView.setItems(sortData);
            });
    }

    public void selectEditScreening(){
        moviesData movD = addScreening_tableView.getSelectionModel().getSelectedItem();
        int num = addScreening_tableView.getSelectionModel().getFocusedIndex();

        if((num-1) < -1){
            return;
        }

        String uri = "file:" + movD.getImage();
        image = new Image(uri, 138,183,false, true);
        editScreening_imageView.setImage(image);

        editScreening_title.setText(movD.getTitle());

    }

    public ObservableList<moviesData> editScreeningList(){

        ObservableList<moviesData> editSList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM movie";
        connect = database.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            moviesData movD;

            while(result.next()){

                movD = new moviesData(result.getInt("id")
                        , result.getString("movieTitle")
                        , result.getString("genre")
                        , result.getString("duration")
                        , result.getString("image")
                        , result.getDate("date")
                        , result.getString("current"));

                editSList.add(movD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editSList;
    }

    private ObservableList<moviesData> editScreeningL;
    public void showEditScreening(){
        editScreeningL = editScreeningList();

        editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        addScreening_tableView.setItems(editScreeningL);
    }

    public void searchAddMovies() {
        FilteredList<moviesData> filter = new FilteredList<>(listAddMovies, e -> true);
        addMovies_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMoviesData ->{
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                String keySearch = newValue.toLowerCase();
                if(predicateMoviesData.getTitle().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMoviesData.getGenre().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMoviesData.getDuration().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMoviesData.getDate().toString().contains(keySearch)){
                    return true;
                }
                return false;
            });
            SortedList<moviesData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(addMovies_tableView.comparatorProperty());
            addMovies_tableView.setItems(sortData);
        });
    }

    public void importImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*jpg"));
        Stage stage = (Stage)addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if(file != null){
            image = new Image(file.toURI().toString(), 127,167,false,true);
            addMovies_imageView.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }

    public void movieId(){

        String sql = "SELECT count(id) FROM movie";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){

                getData.movieId = result.getInt("count(id)");
            }

        }catch(Exception e){e.printStackTrace();}
    }

    public void insertAddMovies(){
        String sql1 = "SELECT * FROM movie WHERE movieTitle = '"
                + addMovies_movieTitle.getText() + "'";

        connect = database.connectDb();
        Alert alert;
        try{

            statement = connect.createStatement();
            result = statement.executeQuery(sql1);

            if(result.next()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movieTitle.getText() + "is already exist");
                alert.showAndWait();
            } else {
                if(addMovies_movieTitle.getText().isEmpty()
                        || addMovies_genre.getText().isEmpty()
                        || addMovies_duration.getText().isEmpty()
                        || addMovies_imageView.getImage() == null
                        || addMovies_date.getValue() == null){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all the blank fields");
                    alert.showAndWait();
                } else{
                    String sql = "INSERT INTO movie (id, movieTitle, genre, duration, image, date, current) VALUES (?,?,?,?,?,?,?)";


                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    movieId();
                    String mID = String.valueOf(getData.movieId + 1);


                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, mID);
                    prepare.setString(2, addMovies_movieTitle.getText());
                    prepare.setString(3, addMovies_genre.getText());
                    prepare.setString(4, addMovies_duration.getText());
                    prepare.setString(5, uri);
                    prepare.setString(6, String.valueOf(addMovies_date.getValue()));
                    prepare.setString(7, "Showing");

                    prepare.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added! ");
                    alert.showAndWait();

                    clearAddMoviesList();
                    showAddMoviesList();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateAddMovies(){

        String uri = getData.path;
        uri = uri.replace("\\","\\\\");

        String sql = "UPDATE movie SET movieTitle = '" + addMovies_movieTitle.getText()
                + "', genre = '" + addMovies_genre.getText()
                + "', duration = '" + addMovies_duration.getText()
                + "', image = '" + uri
                + "', date = '" + addMovies_date.getValue()
                + "' WHERE id = '" + String.valueOf(getData.movieId) + "'";

        connect = database.connectDb();

        try{

            statement = connect.createStatement();

            Alert alert;

            if(addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_imageView.getImage() == null
                    || addMovies_date.getValue() == null){

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();

            }else{

                statement.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Succesfully update " + addMovies_movieTitle.getText());
                alert.showAndWait();

                showAddMoviesList();
                clearAddMoviesList();
            }
        }catch(Exception e){e.printStackTrace();}

    }

    public void deleteAddMovies(){
        String sql = "DELETE FROM movie WHERE movieTitle = '"
                + addMovies_movieTitle.getText() + "'";

        connect = database.connectDb();

        try{

            statement = connect.createStatement();

            Alert alert;

            if(addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_date.getValue() == null
                    || addMovies_imageView.getImage() == null){

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            }else{

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete "
                        + addMovies_movieTitle.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if(ButtonType.OK.equals(option.get())){

                    statement.executeUpdate(sql);

                    showAddMoviesList();
                    clearAddMoviesList();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully delete!");
                    alert.showAndWait();

                }else{
                    return;
                }
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void clearAddMoviesList(){

        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);

    }

    public ObservableList<moviesData> addMoviesList(){
        ObservableList<moviesData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";
        connect = database.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            moviesData movD;
            while(result.next()){
                movD = new moviesData( result.getInt("id")
                        ,result.getString("movieTitle")
                        ,result.getString("genre")
                        ,result.getString("duration")
                        ,result.getString("image")
                        ,result.getDate("date")
                        ,result.getString("current"));

                listData.add(movD);
            }
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<moviesData> listAddMovies;
    public void showAddMoviesList(){
        listAddMovies = addMoviesList();

        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovies_tableView.setItems(listAddMovies);
    }

    public void selectAddMoviesList(){
        moviesData movD = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){
            return;
        }

        getData.path = movD.getImage();

        getData.movieId = movD.getId();

        addMovies_movieTitle.setText(movD.getTitle());
        addMovies_genre.setText(movD.getGenre());
        addMovies_duration.setText(movD.getDuration());
        String getDate = String.valueOf(movD.getDate());

        String uri = "file:" + movD.getImage();
        image = new Image(uri, 127,167,false,true);
        addMovies_imageView.setImage(image);

        addMovies_date.setValue(movD.getDate().toLocalDate());
    }

    public void logout(){
        signout.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) ->{

                x = event.getSceneX();
                y = event.getSceneY();

            });

            root.setOnMouseDragged((MouseEvent event) -> {

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

            });

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){e.printStackTrace();}
    }


    public void switchForm(ActionEvent event){
        if (event.getSource() == addMovies_btn) {
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customer_form.setVisible(false);

            addMovies_btn.setStyle("-fx-background-color: #ae2d3c;");
            availableMovies_btn.setStyle("-fx-background-color: transparent");
            editScreening_btn.setStyle("-fx-background-color: transparent");
            customers_btn.setStyle("-fx-background-color: transparent");

            showAddMoviesList();
        }else if (event.getSource() == availableMovies_btn) {
            availableMovies_form.setVisible(true);
            addMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customer_form.setVisible(false);

            availableMovies_btn.setStyle("-fx-background-color: #ae2d3c;");
            addMovies_btn.setStyle("-fx-background-color: transparent");
            editScreening_btn.setStyle("-fx-background-color: transparent");
            customers_btn.setStyle("-fx-background-color: transparent");

            showAvailableMovies();
        }else if (event.getSource() == editScreening_btn) {
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customer_form.setVisible(false);


            editScreening_btn.setStyle("-fx-background-color: #ae2d3c;");
            addMovies_btn.setStyle("-fx-background-color: transparent");
            availableMovies_btn.setStyle("-fx-background-color: transparent");
            customers_btn.setStyle("-fx-background-color: transparent");

            showEditScreening();
        }else if (event.getSource() == customers_btn) {
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customer_form.setVisible(true);

            customers_btn.setStyle("-fx-background-color:#ae2d3c");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");

            showCustomerList();
        }
    }

    public void displayUsername(){
        username.setText(getData.username);
    }

    public void dashboard_close(){
        System.exit(0);
    }
    public void dashboard_minimize(){
        Stage stage = (Stage)topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        showAddMoviesList();
        showEditScreening();
        comboBox();
        showAvailableMovies();
        selectAvailableMovies();
        showSpinnerValue();
        showCustomerList();
    }
}
