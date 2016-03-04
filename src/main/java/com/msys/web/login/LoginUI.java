package com.msys.web.login;

import org.springframework.beans.factory.annotation.Autowired;
 
import com.msys.repository.UserRepository;
import com.msys.web.login.LoginMainView;
import com.msys.web.view.ImportView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
public class LoginUI extends UI {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository userRepo; 

	@Override
	protected void init(VaadinRequest request) {
		new Navigator(this, this);
		getNavigator().addView(LoginView.NAME, LoginView.class);
		getNavigator().addView(LoginMainView.NAME, LoginMainView.class);
		getNavigator().addView(ImportView.NAME, ImportView.class);  
		
		getNavigator().addViewChangeListener(new ViewChangeListener() {

			private static final long serialVersionUID = 1L; 
		 
			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {

				// Check if a user has logged in
				View view = event.getNewView();
				boolean isLoggedIn = getSession().getAttribute("user") != null;
				boolean isLoginView = view instanceof LoginView;

				if (view instanceof LoginView) {
					LoginView log = (LoginView) view;
					log.setUserRepository(userRepo);
				}

				if (!isLoggedIn && !isLoginView) {
					// Redirect to login view always if a user has not yet
					// logged in
					getNavigator().navigateTo(LoginView.NAME);
					return false;

				} else if (isLoggedIn && isLoginView) {
					// If someone tries to access to login view while logged in,
					// then cancel
					return false;
				}
				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {

			}
		});
	}
}
