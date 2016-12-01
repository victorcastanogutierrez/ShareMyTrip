package com.sdi.client;

import alb.util.menu.BaseMenu;

import com.sdi.client.action.DeshabilitarUsuarioAction;
import com.sdi.client.action.EliminarComentariosYPuntuacionesAction;
import com.sdi.client.action.ListaUsuariosAction;
import com.sdi.client.action.ListarComentariosYPuntuacionesAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Administrador", null },
				{ "Listar usuarios del sistema: ", ListaUsuariosAction.class },
				{ "Deshabilitar usuario", DeshabilitarUsuarioAction.class },
				{ "Listar comentarios y puntuaciones del ultimo mes ",
						ListarComentariosYPuntuacionesAction.class },
				{ "Eliminar comentarios y puntuaciones ",
						EliminarComentariosYPuntuacionesAction.class }, };
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
