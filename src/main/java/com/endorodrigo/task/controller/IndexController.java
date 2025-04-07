package com.endorodrigo.task.controller;

import com.endorodrigo.task.model.Tarea;
import com.endorodrigo.task.presentation.SistemaTareasFx;
import com.endorodrigo.task.service.TareaServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    public Integer idTarea;
    public TextField txtTarea;
    public TextField txtResponsable;
    public TextField txtEstatus;

    @Autowired
    private TareaServicio tareaServicio;

    @FXML
    private TableView<Tarea> tareaTabla;

    @FXML
    private TableColumn<Tarea, Integer> idColumna;

    @FXML
    private TableColumn<Tarea, String> tareaColumna;

    @FXML
    private TableColumn<Tarea, String> responsableColumna;

    @FXML
    private TableColumn<Tarea, String> statusColumna;
    

    private final ObservableList<Tarea> tareaList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumna();
        listarTarea();
    }

    private void listarTarea() {
        log.info("Listando tareas");
        tareaList.clear();
        tareaList.addAll(tareaServicio.listarTarea());
        tareaTabla.setItems(tareaList);
    }

    private void configurarColumna() {
        idColumna.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        tareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        responsableColumna.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        statusColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }

    public void AgregarTarea(ActionEvent actionEvent) {
        if (txtTarea == null) {
            MostrarMensaje("Error validacion", "Debe agregar un tarea");
            txtTarea.requestFocus();
            return;
        }else  {
            var tarea = new Tarea();
            recolectarDatosFormulario(tarea);
            tareaServicio.guardarTarea(tarea);
            MostrarMensaje("Informacion", "Tarea guardada con exito");
            limpiarFormulario();
            listarTarea();
        }
    }

    private void limpiarFormulario() {
        txtTarea.setText("");
        txtResponsable.setText("");
        txtEstatus.setText("");
    }

    private void recolectarDatosFormulario(Tarea tarea) {
        tarea.setNombreTarea(txtTarea.getText());
        tarea.setResponsable(txtResponsable.getText());
        tarea.setEstatus(txtEstatus.getText());
    }

    private void MostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void agregarTareaFormulario(MouseEvent mouseEvent) {
        log.info("Seleciono una tarea");
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        log.info(tarea.toString());
        if (tarea != null) {
            idTarea = tarea.getIdTarea();
            txtTarea.setText(tarea.getNombreTarea());
            txtResponsable.setText(tarea.getResponsable());
            txtEstatus.setText(tarea.getEstatus());
            log.info("Seleciono la tarea con ID: " + idTarea);
        }
    }
}
