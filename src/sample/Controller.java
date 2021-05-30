package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

import static sample.dbconnection.connect;

public class Controller  {

    @FXML
    private TextField tfname;

    @FXML
    private TextField tfid;

    @FXML
    private TextField tfnbachats;

    @FXML
    private TableView<Clients> tabclients;

    @FXML
    private TableColumn<Clients, String> tabname;

    @FXML
    private TableColumn<Clients, Integer> tabid;

    @FXML
    private TableColumn<Clients, Integer> tabnbachats;

    @FXML
    private Button btninsert;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    private Connection newConnect(){
        String url = "jdbc:sqlite:C:\\Users\\Dell\\IdeaProjects\\test\\clients.db";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return conn;
    }


    public void showclients() {
        tabname.setCellValueFactory(new PropertyValueFactory<Clients , String>("name"));
        tabid.setCellValueFactory(new PropertyValueFactory<Clients , Integer>("id"));
        tabnbachats.setCellValueFactory(new PropertyValueFactory<Clients , Integer>("nbachats"));
        tabclients.setItems(getclients());
    }


     public ObservableList<Clients> getclients(){
        ObservableList<Clients> clientslist = FXCollections.observableArrayList();
        Connection connection = dbconnection.connect();
        String query;
        query = "SELECT * FROM clients";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Clients clients ;
            while(rs.next()){
                clients = new Clients(rs.getString("name"),rs.getInt("id"),rs.getInt("nbachats"));
                clientslist.add(clients);
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return clientslist;
    }


      //@FXML public void btninsert(ActionEvent event){
      // clients.add(new Clients(tfname.getText(),Integer.parseInt(tfid.getText()),Integer.parseInt(tfnbachats.getText())));
     // }
      public void executequery(String query){
          Connection connection = connect();
          Statement st;
          try {
              st = connection.createStatement();
              st.executeUpdate(query);
          }catch (Exception ex){
              System.out.println(ex.getMessage());
//              ex.printStackTrace();
              System.out.println("maranich nakhdem");
          }
      }
    public void btninsert(ActionEvent event) {
//        String query = "INSERT INTO clients (name,id,nbachats) VALUES ('" + tfname.getText() + "'," + tfid.getText() + "," + tfnbachats.getText() + ");";
//        executequery(query);
        insertValue(tfname.getText(), Integer.parseInt(tfid.getText()), Integer.parseInt(tfnbachats.getText()));
        showclients();

    }

    public void insertValue(String name, int id, int nombreAchat){
         String sqlQuery = "INSERT INTO clients (name, id, nbachats) VALUES (?, ?, ?)";

         try (Connection c = this.newConnect(); PreparedStatement pstm = c.prepareStatement(sqlQuery)){
             pstm.setString(1, name);
             pstm.setInt(2, id);
             pstm.setInt(3, nombreAchat);
             pstm.execute();
             System.out.println(pstm.toString());
         }
         catch (SQLException e){
             e.printStackTrace();
             System.out.println(e.getMessage());
         }
    }
    public void btndelete (ActionEvent event){
        try {
            Clients clients= tabclients.getSelectionModel().getSelectedItem();
            String query = " DELETE FROM clients where id ="+clients.getId();
            Statement st = connect().prepareStatement(query);
            st.executeQuery(query);
            showclients();

        }catch (Exception e){
           e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }



}


