package de.nieblings.gui.pages;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletFilter  implements ServletContextListener {
		private String user;
		private String passord;
		
	    @Override
	    public void contextInitialized(ServletContextEvent sce) {
	        ServletContext ctx = sce.getServletContext();
	        
	        setUser(ctx.getInitParameter("de.nieblings.user"));
	        setPassord(ctx.getInitParameter("de.nieblings.passwort"));

	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent sce) {}

		public String getUser() {
			return user;
		}

		private void setUser(String user) {
			this.user = user;
		}

		public String getPassord() {
			return passord;
		}

		private void setPassord(String passord) {
			this.passord = passord;
		}

	}