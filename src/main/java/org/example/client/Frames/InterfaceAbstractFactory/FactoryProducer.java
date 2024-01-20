package org.example.client.Frames.InterfaceAbstractFactory;

import org.example.client.Frames.InterfaceAbstractFactory.Factory.AbstractFactory;
import org.example.client.Frames.InterfaceAbstractFactory.Factory.CyberFactory;
import org.example.client.Frames.InterfaceAbstractFactory.Factory.DefaultFactory;
import org.example.client.Frames.InterfaceAbstractFactory.Factory.MagicFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(String factoryType){
        return switch (factoryType) {
            case "Magic" -> new MagicFactory();
            case "Cyber" -> new CyberFactory();
            default -> new DefaultFactory();
        };
    }
}
