/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxController;

import com.arc.cheque.dao.DaoBanco;
import com.arc.cheque.dao.DaoCheque;
import com.arc.cheque.dao.DaoChequera;
import com.arc.cheque.dao.DaoCuenta;
import com.arc.cheque.fxModels.BancoFx;
import com.arc.cheque.fxModels.ChequesValueFactory;
import com.arc.cheque.model.Banco;
import com.arc.cheque.model.Cheque;
import com.arc.cheque.model.Chequera;
import com.arc.cheque.model.Cuenta;
import com.arc.cheque.model.ResumenChequePorFechaTotal;
import com.arc.cheque.utilidad.Utilidad;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Brother
 */
public class MovimientosViewController implements Initializable {

    @FXML
    private JFXTabPane tabPaneMovimientos;
    @FXML
    private Tab emitirCheque;
    @FXML
    private Tab anularCheque;
    @FXML
    private Tab pagarCheque;

    @FXML
    private JFXComboBox<BancoFx> cbBanco;

    @FXML
    private JFXComboBox<Cuenta> cbCuenta;

    @FXML
    private JFXComboBox<Chequera> cbChequera;

    @FXML
    private JFXComboBox<Cheque> cbCheque;

    @FXML
    private Label lblRotulo;

    @FXML
    private Label lblFecha;

    @FXML
    private JFXTextField txOrden;

    @FXML
    private JFXTextField txDias;

    @FXML
    private JFXTextField txMonto;

    @FXML
    private JFXDatePicker dateCobro;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private TableView<ResumenChequePorFechaTotal> tablaVencimientos;

    @FXML
    private TableColumn<ResumenChequePorFechaTotal, String> colVencimiento;

    @FXML
    private TableColumn<ResumenChequePorFechaTotal, String> colMonto;

    @FXML
    private TableColumn<ResumenChequePorFechaTotal, String> colDias;

    //controles de pestana pagar
    @FXML
    private JFXComboBox<BancoFx> cbBancopay;

    @FXML
    private JFXComboBox<Cuenta> cbCuentappay;

    @FXML
    private JFXComboBox<Chequera> cbChequerapay;

    @FXML
    private Label lblRotulopay;

    @FXML
    private Label lblRotuloNull;

    @FXML
    private TableView<Cheque> tablaChequespay;

    @FXML
    private TableColumn<Cheque, String> colVencimientopay;

    @FXML
    private TableColumn<Cheque, String> colMontopay;

    @FXML
    private TableColumn<Cheque, CheckBox> colPagar;

    @FXML
    private JFXButton btnGuardarpay;
    // controles anular
    @FXML
    private JFXComboBox<BancoFx> cbBancoNull;

    @FXML
    private JFXComboBox<Cuenta> cbCuentaNull;

    @FXML
    private JFXComboBox<Chequera> cbChequeraNull;

    @FXML
    private JFXComboBox<Cheque> cbChequeNull;
    @FXML
    private Label lblOrden;

    @FXML
    private Label lblEmision;

    @FXML
    private Label lblFechaVencimiento;

    @FXML
    private JFXButton btnAnular;

    @FXML
    private AnchorPane paneChequeAnularDetalle;

    private BancoFx bancoFx;
    ObservableList<BancoFx> bancos = FXCollections.observableArrayList();
    private DaoCuenta daoCuenta;
    private DaoChequera daoChequera;
    private List<Chequera> chequeras;
    private DaoCheque daoCheque;
    private List<Cheque> cheques;
    private Cheque cheque;
    private List<Object[]> totales;
    private ResumenChequePorFechaTotal total;
    private List<ResumenChequePorFechaTotal> totalCheques;
    private boolean bandera = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        estadoInicial();
        cargarBanco();
        if (bandera) {
            cargarBanco(cbBanco);
            bandera = false;
        }

        determinarTab();

        //addListener a plazo en dias
        txDias.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue) {
                calcularIntervaloTabla();

            }
        });
    }

    //eventos de Emitir Cheque
    @FXML
    void cargarCheque(ActionEvent event) {
        lblRotulo.setText("Seleccione el cheque a emitir");
        cbCheque.setVisible(true);
        daoCheque = new DaoCheque();
        cheques = daoCheque.recuperarChequesPorChequera(cbChequera.getSelectionModel().getSelectedItem().getNro());
        ObservableList<Cheque> chequesList = FXCollections.observableArrayList(cheques);
        traerListaCheques(cbCheque, chequesList);

    }

    @FXML
    void cargarChequera(ActionEvent event) {
        //habilitar el siguiente combobox
        lblRotulo.setText("Seleccione chequera");
        cbChequera.setVisible(true);
        daoChequera = new DaoChequera();
        chequeras = daoChequera.recuperarPorCuenta(cbCuenta.getSelectionModel().getSelectedItem().getId());
        ObservableList<Chequera> chequerasList = FXCollections.observableArrayList(chequeras);
        traerChequeras(cbChequera, chequerasList);

    }

    @FXML
    void cargarCuenta(ActionEvent event) {
        lblRotulo.setText("Seleccione una cuenta asociada");
        cbCuenta.setVisible(true);
        daoCuenta = new DaoCuenta();
        List<Cuenta> cuentas = daoCuenta.recuperarPorBanco(cbBanco.getSelectionModel().getSelectedItem().getId());
        ObservableList<Cuenta> cuentasList = FXCollections.observableArrayList(cuentas);
        cargarCuentasBanco(cbCuenta, cuentasList);

    }

    @FXML
    void cargarTablaVencimientos(ActionEvent event) {
        calcularIntervaloTabla();

    }

    @FXML
    void mostrartextFields(ActionEvent event) {
        lblRotulo.setText("");
        txOrden.setVisible(true);
        txDias.setVisible(true);
        dateCobro.setVisible(true);
        dateCobro.setValue(LocalDate.now());
        txMonto.setVisible(true);
        lblFecha.setVisible(true);

    }

    //eventos de anular
    @FXML
    void cargarChequeNull(ActionEvent event) {
        lblRotuloNull.setText("Seleccione el cheque a emitir");
        cbChequeNull.setVisible(true);
        daoCheque = new DaoCheque();
        cheques = daoCheque.recuperarChequesPorChequera(cbChequeraNull.getSelectionModel().getSelectedItem().getNro());
        ObservableList<Cheque> chequesList = FXCollections.observableArrayList(cheques);
        traerListaCheques(cbChequeNull, chequesList);

    }

    @FXML
    void cargarChequeraNull(ActionEvent event) {
        //habilitar el siguiente combobox
        lblRotuloNull.setText("Seleccione chequera");
        cbChequeraNull.setVisible(true);
        daoChequera = new DaoChequera();
        chequeras = daoChequera.recuperarPorCuenta(cbCuentaNull.getSelectionModel().getSelectedItem().getId());
        ObservableList<Chequera> chequerasList = FXCollections.observableArrayList(chequeras);
        traerChequeras(cbChequeraNull, chequerasList);

    }

    @FXML
    void cargarCuentaNull(ActionEvent event) {
        lblRotuloNull.setText("Seleccione una cuenta asociada");
        cbCuentaNull.setVisible(true);
        daoCuenta = new DaoCuenta();
        List<Cuenta> cuentas = daoCuenta.recuperarPorBanco(cbBancoNull.getSelectionModel().getSelectedItem().getId());
        ObservableList<Cuenta> cuentasList = FXCollections.observableArrayList(cuentas);
        cargarCuentasBanco(cbCuentaNull, cuentasList);

    }

    @FXML
    void mostrarLabelsNull(ActionEvent event) {
        if (!paneChequeAnularDetalle.isVisible()) {
            paneChequeAnularDetalle.setVisible(true);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(600));
            fadeIn.setNode(paneChequeAnularDetalle);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.setCycleCount(1);
            fadeIn.setAutoReverse(false);
            fadeIn.playFromStart();
          
        }

        //lblRotuloNull.setText("");
//        daoCheque = new DaoCheque();
//        cheque = daoCheque.recuperarPorId(cbChequeNull.getSelectionModel().getSelectedItem().getNro());
          cheque = cbChequeNull.getSelectionModel().getSelectedItem();
        System.out.println("id seleccionado: "+ cheque.getNro());
            System.out.println("cheque orden: "+cheque.getOrden());
           
        lblOrden.setText(cheque.getOrden());
        if (cheque.getCobro() == null) {
            lblFechaVencimiento.setText("Cheque no emitido");
        }else{lblFechaVencimiento.setText(cheque.getEmision().toString());}
         if (cheque.getCobro() == null) {
            lblEmision.setText("Cheque no emitido");
        }else{lblEmision.setText(cheque.getEmision().toString());}
//        lblEmision.setText(cheque.getEmision().toString());
//        lblFechaPago.setText(cheque.getCobro().toString());
           //lblOrden.setText(cheque.getOrden());

    }

    @FXML
    void guardar(ActionEvent event) {
        cheque = cbCheque.getSelectionModel().getSelectedItem();
        cheque.setCobro(dateCobro.getValue());
        cheque.setEmision(LocalDate.now());
        cheque.setMonto(Double.parseDouble(txMonto.getText()));
        cheque.setOrden(txOrden.getText());
        cheque.setSituacion("Pendiente");
        cheque.setEstado(false);

        daoCheque = new DaoCheque();
        try {
            daoCheque.actualizar(cheque);
            daoCheque.commit();
        } catch (Exception ex) {
            daoCheque.rollback();
            Logger.getLogger(MovimientosViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // metodos para pestanha pagar cheque
    @FXML
    void cargarCuentapay(ActionEvent event) {
        lblRotulopay.setText("Seleccione una cuenta asociada");
        cbCuentappay.setVisible(true);
        daoCuenta = new DaoCuenta();
        List<Cuenta> cuentas = daoCuenta.recuperarPorBanco(cbBancopay.getSelectionModel().getSelectedItem().getId());
        ObservableList<Cuenta> cuentasList = FXCollections.observableArrayList(cuentas);
        cargarCuentasBanco(cbCuentappay, cuentasList);

    }

    @FXML
    void cargarChequepay(ActionEvent event) {
        //se carga la tabla
        traerCheques();

    }

    @FXML
    void cargarChequerapay(ActionEvent event) {
        lblRotulo.setText("Seleccione chequera");
        cbChequerapay.setVisible(true);
        traerChequerasPay();

    }

    @FXML
    void guardarPago(ActionEvent event) {
        filasMarcadas();

    }

    @FXML
    void anularCheque(ActionEvent event) {

    }

    private void estadoInicial() {
        lblRotulo.setText("Seleccione un banco");
        lblRotulopay.setText("Seleccione un banco:");
        cbCuenta.setVisible(false);
        cbCuentappay.setVisible(false);
        cbChequera.setVisible(false);
        cbChequerapay.setVisible(false);
        cbCheque.setVisible(false);
        txOrden.setVisible(false);
        txDias.setVisible(false);
        dateCobro.setVisible(false);
        txMonto.setVisible(false);
        lblFecha.setVisible(false);
        paneChequeAnularDetalle.setVisible(false);

    }

    private void cargarBanco() {

        DaoBanco daoBanco = new DaoBanco();
        List<Banco> bancos = daoBanco.recuperarTodo();

        for (int i = 0; i < bancos.size(); i++) {
            bancoFx = new BancoFx(bancos.get(i).getId(), bancos.get(i).getBanco());
            this.bancos.add(bancoFx);
        }

    }

    private void calcularIntervaloTabla() {
        if (txDias.getText().isEmpty()) {
            txDias.setText("0");
        }
        LocalDate desde = LocalDate.now();
        LocalDate hasta = desde.plus(Integer.parseInt(txDias.getText()), ChronoUnit.DAYS);
        dateCobro.setValue(hasta);
        cargarTablaEmitirCheque(desde, hasta);
    }

    private void cargarTablaEmitirCheque(LocalDate desde, LocalDate hasta) {
        daoCheque = new DaoCheque();
        totales = daoCheque.resumenDeChequesEmitidosPorDia(desde, hasta);
        totalCheques = new ArrayList<>();
        for (Object[] q : totales) {
            total = new ResumenChequePorFechaTotal();
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            String strFecha = q[0].toString();
            Date fecha = null;
            try {
                fecha = formatoDelTexto.parse(strFecha);
            } catch (ParseException ex) {
                Logger.getLogger(MovimientosViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            total.setFecha(Utilidad.formatoFechaDateAString(fecha));
            total.setMonto(Utilidad.formatoNumDoubleString(Double.parseDouble(q[1].toString())));
            LocalDate fechaCalculada = LocalDate.parse(q[0].toString());
            long calculo = ChronoUnit.DAYS.between(LocalDate.now(), fechaCalculada);
            if (calculo==0) {
                total.setDiasVencimiento("Vence hoy!");
            }
            else if(calculo == 1){total.setDiasVencimiento("Vence mañana!");}
            else{
                total.setDiasVencimiento("Vence en "+calculo+" días");
            }
            
            totalCheques.add(total);

        }
        calcularDiasVencimiento();
        ObservableList<ResumenChequePorFechaTotal> chequesList = FXCollections.observableArrayList(totalCheques);
        this.colVencimiento.setCellValueFactory(new PropertyValueFactory<ResumenChequePorFechaTotal, String>("fecha"));
        this.colMonto.setCellValueFactory(new PropertyValueFactory<ResumenChequePorFechaTotal, String>("monto"));
        this.colDias.setCellValueFactory(new PropertyValueFactory<ResumenChequePorFechaTotal, String>("diasVencimiento"));
        tablaVencimientos.setItems(chequesList);

    }

    private void calcularDiasVencimiento() {
        

    }

    private void traerChequerasPay() {
        daoChequera = new DaoChequera();
        chequeras = daoChequera.recuperarPorCuenta(cbCuentappay.getSelectionModel().getSelectedItem().getId());
        ObservableList<Chequera> chequerasList = FXCollections.observableArrayList(chequeras);
        cbChequerapay.setItems(chequerasList);

        StringConverter<Chequera> converter = new StringConverter<Chequera>() {
            @Override
            public String toString(Chequera object) {
                return "Nro " + object.getNro();
            }

            @Override
            public Chequera fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        cbChequerapay.setConverter(converter);
    }

    private void traerCheques() {

        DaoCheque daoCheque = new DaoCheque();
        List<Cheque> cheques = daoCheque.recuperarPorChequera(cbChequerapay.getSelectionModel().getSelectedItem().getNro(), false, false);
     
        ObservableList<Cheque> chequesList = FXCollections.observableArrayList(cheques);

        tablaChequespay.getItems().clear();
        tablaChequespay.getItems().addAll(chequesList);
        colMontopay.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMonto().toString()));
        colVencimientopay.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmision().toString()));
        colPagar = (TableColumn<Cheque, CheckBox>) tablaChequespay.getColumns().get(2);
        colPagar.setCellValueFactory(new ChequesValueFactory());

    }

    private void filasMarcadas() {

        ObservableList<Cheque> chequesMarcados = FXCollections.observableArrayList(tablaChequespay.getItems());
        cheques = new ArrayList<>();
        for (int i = 0; i < chequesMarcados.size(); i++) {
            if (chequesMarcados.get(i).getPagado()) {

                cheques.add(chequesMarcados.get(i));
            }

        }
        for (int i = 0; i < cheques.size(); i++) {
            cheque = cheques.get(i);
            cheque.setPagado(true);
            cheque.setSituacion("Cobrado");
            daoCheque = new DaoCheque();
            try {
                daoCheque.actualizar(cheque);
                daoCheque.commit();
            } catch (Exception ex) {

                daoCheque.rollback();
                System.out.println("Error al guardar los datos " + ex);
            }
        }

    }

    private void determinarTab() {
        emitirCheque.selectedProperty().addListener((observable) -> {
            cargarBanco(cbBanco);
        });
        pagarCheque.selectedProperty().addListener((observable) -> {
            cargarBanco(cbBancopay);
        });
        anularCheque.selectedProperty().addListener((observable) -> {
            cargarBanco(cbBancoNull);
        });

    }

    private void cargarBanco(JFXComboBox combo) {
        combo.setItems(bancos);
        StringConverter<BancoFx> converter = new StringConverter<BancoFx>() {
            @Override
            public String toString(BancoFx object) {
                return object.getBanco();
            }

            @Override
            public BancoFx fromString(String id) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        combo.setConverter(converter);

    }

    private void cargarCuentasBanco(JFXComboBox combo, ObservableList observableList) {
        combo.setItems(observableList);
        StringConverter<Cuenta> converter = new StringConverter<Cuenta>() {
            @Override
            public String toString(Cuenta object) {
                return object.getCuenta();
            }

            @Override
            public Cuenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        combo.setConverter(converter);
    }

    private void traerChequeras(JFXComboBox chequera, ObservableList chequerasList) {
        chequera.setItems(chequerasList);

        StringConverter<Chequera> converter = new StringConverter<Chequera>() {
            @Override
            public String toString(Chequera object) {
                return "Nro " + object.getNro();
            }

            @Override
            public Chequera fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        chequera.setConverter(converter);
    }

    private void traerListaCheques(JFXComboBox cbCheque, ObservableList chequesList) {
        cbCheque.setItems(chequesList);
        cbCheque.setConverter(new StringConverter<Cheque>() {
            @Override
            public String toString(Cheque object) {
                return String.valueOf(object.getCheque());
            }

            @Override
            public Cheque fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       
    }

}
