package de.nieblings.gui.pages;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import de.nieblings.gui.pages.View.ZipatoList;
import de.nieblings.zipato.data.AttributUtil;
import de.nieblings.zipato.data.attribut.Attribut;
import de.nieblings.zipato.data.devices.State;
import de.nieblings.zipato.data.internal.DeviceConfig;

@Named
@ViewAccessScoped
public class ZipatoBean implements Serializable {

	private static final long serialVersionUID = 8649982876548428091L;

	@Inject
	private EditAttributBean editAttributBean;

	@Inject
	private SessionBean sessionBean;

	private DeviceConfig element;
	private boolean collapsed = false;

	public Class<? extends ViewConfig> showElement(DeviceConfig element) {
		this.element = element;
		return View.Zipato.class;
	}

	public Class<? extends ViewConfig> showListe() {
		return ZipatoList.class;
	}

	public Class<? extends ViewConfig> editAttribut(Attribut attribut) {
		return editAttributBean.editAttribut(attribut);
	}

	public DeviceConfig getDeviceConfig() {
		return element;
	}

	public Boolean isAttributEditable(Attribut attribut) {
		return (attribut != null && attribut.getDefinition().getWritable());
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public String getStatus() {
		if (element == null) {
			return "";
		}
		State status = AttributUtil.getStatus(sessionBean.getZipatoSession(),
					element.getId());
		if (status==null){
			return "";
		}
		return status.getOnlineState();
		
	}

}
