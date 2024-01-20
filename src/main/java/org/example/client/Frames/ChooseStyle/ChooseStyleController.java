package org.example.client.Frames.ChooseStyle;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.example.client.Frames.InterfaceAbstractFactory.Factory.AbstractFactory;
import org.example.client.Frames.InterfaceAbstractFactory.FactoryProducer;


public class ChooseStyleController {
    public Label cyber;
    public Label magic;
    public Polygon cyberChoose;
    public Polygon magicalChoose;

    public void dragCyber(){
        cyberChoose.setStroke(Color.DODGERBLUE);
        magicalChoose.setStroke(Color.TRANSPARENT);
        cyber.setVisible(true);
        magic.setVisible(false);
    }

    public void dragMagical(){
        cyberChoose.setStroke(Color.TRANSPARENT);
        magicalChoose.setStroke(Color.VIOLET);
        magic.setVisible(true);
        cyber.setVisible(false);
    }

    public void chooseCyber(){
        chooseStyleForApplication("Cyber");
    }

    public void chooseMagical(){
        chooseStyleForApplication("Magic");
    }

    public void chooseStyleForApplication(String interfaceType){
        magicalChoose.getScene().getWindow().hide();
        AbstractFactory factory = FactoryProducer.getFactory(interfaceType);
        factory.getAuthorization().start(new Stage());
    }
}
