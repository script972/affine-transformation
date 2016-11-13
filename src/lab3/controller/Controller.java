package lab3.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lab3.model.Figure;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private static final double DELTA = 5;
    @FXML
    Pane mainPanel;
    @FXML
    Canvas canvas;
    @FXML
    ColorPicker CP;
    @FXML
    AnchorPane manualpanel;


    Figure f = new Figure();

    public void drawFigure(ActionEvent actionEvent) {
        draw();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.widthProperty().bind(mainPanel.widthProperty());
        canvas.heightProperty().bind(mainPanel.heightProperty());
        canvas.widthProperty().addListener(e->draw());
        canvas.heightProperty().addListener(e->draw());
        mainPanel.requestFocus();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);

        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        f.draw(gc, CP.getValue());
    }

    public void processKey(Event event) {
        KeyEvent keyEvent = (KeyEvent) event;
        KeyCode keyCode = keyEvent.getCode();

        switch (keyCode) {
            case UP:
                f.move(0,-DELTA);
                break;
            case DOWN:
                f.move(0,DELTA);
                break;
            case LEFT:
                f.move(-DELTA,0);
                break;
            case RIGHT:
                f.move(DELTA,0);
                break;
            case P:
                f.scale(1.1);
                break;
            case MINUS:
                f.scale(1/1.1);
                break;
            case R:
                f.rotate(10);
                break;
            case Q:
                f.rotate(-10);
                break;
        }
        draw();
    }

    public void showManual(ActionEvent actionEvent) {

        manualpanel.setStyle("-fx-opacity: 1");



    }
}
