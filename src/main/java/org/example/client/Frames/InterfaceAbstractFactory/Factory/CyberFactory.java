package org.example.client.Frames.InterfaceAbstractFactory.Factory;

import org.example.client.Frames.InterfaceAbstractFactory.Authorization.Authorization;
import org.example.client.Frames.InterfaceAbstractFactory.Authorization.CyberAuthorization;
import org.example.client.Frames.InterfaceAbstractFactory.ClientMenu.ClientMenu;
import org.example.client.Frames.InterfaceAbstractFactory.ClientMenu.CyberClientMenu;

public class CyberFactory implements AbstractFactory{
    @Override
    public Authorization getAuthorization() {
        return new CyberAuthorization();
    }

    @Override
    public ClientMenu getClientMenu() {
        return new CyberClientMenu();
    }
}
