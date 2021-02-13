/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class PersonaControlador implements Initializable {

    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoApellido;
    @FXML
    private TextField campoEdad;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellido;
    @FXML
    private TableColumn colEdad;
    @FXML
    private TableView<Persona> tablaPersonas;

    private ObservableList<Persona> personas;

    @FXML
    private TableColumn<?, ?> colCorreo;
    @FXML
    private TextField campoCorreo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnElimianr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personas = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellido.setCellValueFactory(new PropertyValueFactory("Apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("edad"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }

    @FXML
    private void agregarPersona(ActionEvent event) {

        try {

            String nombre = campoNombre.getText();
            String Apellidos = campoApellido.getText();
            int edad = Integer.parseInt(campoEdad.getText());
            String correo = campoCorreo.getText();
            Persona persona1 = new Persona(nombre, Apellidos, edad, correo);

            //Si no hay una persona igual a la que estoy introduciendo entonces agregarme esa persona  
            if (!personas.contains(persona1)) {
                personas.add(persona1);
                this.tablaPersonas.setItems(personas);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Persona añadida");
                alert.showAndWait();
            } else {
                JOptionPane ventana = new JOptionPane();
                JOptionPane.showMessageDialog(null, "La Persona existe");

            }

        } catch (NumberFormatException e) {
            JOptionPane ventana = new JOptionPane();
            JOptionPane.showMessageDialog(null, "Ingresar Datos Validos");
        }

    }

    @FXML
    private void seleccionar(MouseEvent event) {

        Persona p = this.tablaPersonas.getSelectionModel().getSelectedItem();

        if (p != null) {
            this.campoNombre.setText(p.getNombre());
            this.campoApellido.setText(p.getApellidos());
            this.campoEdad.setText(p.getEdad() + "");
            this.campoCorreo.setText(p.getCorreo());

            tablaPersonas.refresh();
        }
    }

    @FXML
    private void modificar(ActionEvent event) {

        Persona p = this.tablaPersonas.getSelectionModel().getSelectedItem();

        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar");
            alert.showAndWait();
        } else {

            try {

                String nombre = campoNombre.getText();
                String Apellidos = campoApellido.getText();
                int edad = Integer.parseInt(campoEdad.getText());
                String correo = campoCorreo.getText();
                Persona persona1 = new Persona(nombre, Apellidos, edad, correo);

                if (!personas.contains(persona1)) {

                    p.setNombre(persona1.getNombre());
                    p.setEdad(persona1.getEdad());
                    p.setApellidos(persona1.getApellidos());
                    p.setCorreo(persona1.getCorreo());

                } else {
                    JOptionPane ventana = new JOptionPane();
                    JOptionPane.showMessageDialog(null, "La Persona existe");

                }

            } catch (NumberFormatException e) {
                JOptionPane ventana = new JOptionPane();
                JOptionPane.showMessageDialog(null, "Ingresar Datos Validos");
            }

        }

    }

    @FXML
    private void eliminar(ActionEvent event) {

        Persona p = this.tablaPersonas.getSelectionModel().getSelectedItem();

        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar");
            alert.showAndWait();
        } else {
            personas.remove(p);
            this.tablaPersonas.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Persona Eliminada");
            alert.showAndWait();
        }
    }
}
