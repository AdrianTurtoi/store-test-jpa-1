package com.msys.web.login;

import com.msys.web.view.ImportView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

@SpringComponent
@UIScope
public class LoginMainView extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	Label text = new Label();

	Button logout = new Button("Logout", new Button.ClickListener() {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
			// "Logout" the user
			getSession().setAttribute("user", null);
			// Refresh this view, should redirect to login view
			getUI().getNavigator().navigateTo(NAME);
		}
	});

	public LoginMainView() {
		MenuBar mainMenu = new MenuBar();
		MenuItem importMenu = mainMenu.addItem("Import", null, mycommand);
		MenuItem orders = mainMenu.addItem("Orders", null, mycommand);
		MenuItem supliers = mainMenu.addItem("Supliers", null, mycommand);
		MenuItem storeManagement = mainMenu.addItem("StoreManagement", null, mycommand);
		HorizontalLayout actions = new HorizontalLayout(mainMenu, logout, text);
		setCompositionRoot(new CssLayout(actions));
	}

	MenuBar.Command mycommand = new MenuBar.Command() {
		private static final long serialVersionUID = 1L;

		public void menuSelected(MenuItem selectedItem) {
			getUI().getNavigator().navigateTo(ImportView.NAME);
		}
	};

	@Override
	public void enter(ViewChangeEvent event) {
		// Get the user name from the session
		String username = String.valueOf(getSession().getAttribute("user"));

		// And show the username
		text.setValue("Hello " + username);
	}
}