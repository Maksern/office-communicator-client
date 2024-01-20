package org.example.client.Frames.InterfaceAbstractFactory.Factory;

import org.example.client.Frames.InterfaceAbstractFactory.Authorization.Authorization;
import org.example.client.Frames.InterfaceAbstractFactory.ClientMenu.ClientMenu;

public class DefaultFactory implements AbstractFactory{
    @Override
    public Authorization getAuthorization() {
        return null;
    }

    @Override
    public ClientMenu getClientMenu() {
        return null;
    }
}
