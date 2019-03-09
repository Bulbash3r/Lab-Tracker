package App.Controllers;

import App.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectWindow implements Initializable {

    private @FXML Button btnOK;
    private @FXML TextField txtfieldIP;
    private @FXML TextField txtfieldPort;

    private String ip;
    private int port;

    private boolean isIpOk = false;
    private boolean isPortOk = false;

    private ControllersManager manager;
    private Stage stage;

    public ConnectWindow(ControllersManager manager) {
        this.manager = manager;
        stage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/ConnectWindow.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnOK.setDisable(true);

        txtfieldIP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() == 0) {
                    if (isIpOk)
                        isIpOk = false;
                } else {
                    boolean wrong = false;
                    char[] chars = newValue.toCharArray();
                    for (char c : chars)
                        if (c != '.' && (c > '9' || c < '0')) {
                            if (isIpOk)
                                isIpOk = false;
                            wrong = true;
                            break;
                        }
                    if (!isIpOk && !wrong)
                        isIpOk = true;
                }

                if (isPortOk && isIpOk && btnOK.isDisable())
                    btnOK.setDisable(false);
            }
        });

        txtfieldPort.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() == 0) {
                    if (isPortOk)
                        isPortOk = false;
                } else {
                    boolean wrong = false;
                    char[] chars = newValue.toCharArray();
                    for (char c : chars)
                        if (c > '9' || c < '0') {
                            if (isPortOk)
                                isPortOk = false;
                            wrong = true;
                            break;
                        }
                    if (!isPortOk && !wrong)
                        isPortOk = true;
                }

                if (isPortOk && isIpOk && btnOK.isDisable())
                    btnOK.setDisable(false);
            }
        });

        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                manager.joinGame(txtfieldIP.getText(), Integer.parseInt(txtfieldPort.getText()));
                stage.close();
            }
        });
    }
}
