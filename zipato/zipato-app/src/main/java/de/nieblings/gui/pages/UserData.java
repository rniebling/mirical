package de.nieblings.gui.pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import de.nieblings.gui.pages.View.ZipatoList;
import de.nieblings.zipato.data.Login;
import de.nieblings.zipato.data.ZipatoSession;
import de.nieblings.zipato.data.internal.DeviceConfig;

@Named
@ViewAccessScoped
public class UserData implements Serializable {

 	private static final long serialVersionUID = 1682864095930632376L;

// 	@Inject
//    private ServletFilter servletFilter;	
// 	

    private String user;
    private String passwort;
    private ZipatoSession zipatoSession;
    private List<DeviceConfig> deviceConfig=new ArrayList<DeviceConfig>();
    
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

    @PostConstruct
    public void init() {
//    	user=servletFilter.getUser();
//    	passwort=servletFilter.getPassord();
    	user="ralf.niebling@subito.de";
    	passwort="privat10";

    }
    
       
    public Class<? extends ViewConfig> login() {
    	zipatoSession = Login.get(user,passwort);
        return ZipatoList.class;
    }
    
	public ZipatoSession getZipatoSession() {
		if(zipatoSession==null) {
			zipatoSession = Login.get(user,passwort);
		}
		return zipatoSession;
	}
	public List<DeviceConfig> getDeviceConfig() {
		return deviceConfig;
	}
	public void setDeviceConfig(List<DeviceConfig> deviceConfig) {
		this.deviceConfig = deviceConfig;
	}

}
