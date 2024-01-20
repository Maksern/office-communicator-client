package org.example.client.Frames.InterfaceAbstractFactory.Factory;

import org.example.client.Frames.InterfaceAbstractFactory.Authorization.Authorization;
import org.example.client.Frames.InterfaceAbstractFactory.ClientMenu.ClientMenu;

public interface AbstractFactory {
    public Authorization getAuthorization();
    public ClientMenu getClientMenu();
}
