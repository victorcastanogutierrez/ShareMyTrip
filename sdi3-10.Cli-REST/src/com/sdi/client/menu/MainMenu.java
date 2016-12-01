package com.sdi.client.menu;

import alb.util.menu.BaseMenu;

import com.sdi.client.menu.action.ListaViajesAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {		
		menuOptions = new Object[][] {
				{ "Usuario", null },
				{ "Listar viajes abiertos como promotor: ",
						ListaViajesAction.class }, };
	}

}
