package de.nieblings.gui.pages;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.PreRenderView;

import de.nieblings.zipato.data.AttributUtil;
import de.nieblings.zipato.data.Endpoints;
import de.nieblings.zipato.data.VirtualEndpoints;
import de.nieblings.zipato.data.attribut.Attribut;
import de.nieblings.zipato.data.devices.State;
import de.nieblings.zipato.data.internal.DeviceConfig;

@Named
@ViewAccessScoped
public class ZipatoListBean implements Serializable {

	private static final long serialVersionUID = 8649982876548428091L;

	@Inject
	private SessionBean sessionBean;

	@Inject
	private ZipatoBean zipatoBean;

	@PreRenderView
	public void preRenderView() {
		VirtualEndpoints[] devices = Endpoints.getDevices(sessionBean
				.getZipatoSession());
		Attribut[] attribute = AttributUtil.getAttribute(sessionBean
				.getZipatoSession());
		final List<DeviceConfig> deviceConfig = sessionBean.getDeviceConfig();
		deviceConfig.clear();
		int lauf = 0;
		while (lauf < devices.length) {
			final DeviceConfig config = new DeviceConfig();
			config.setId(devices[lauf].getUuid());
			config.setViewName(devices[lauf].getName());

			config.setType(devices[lauf].getDescription());
			config.setDevice(devices[lauf]);
			
			int lauf2 = 0;
			boolean gefunden = false;
			while (lauf2 < attribute.length) {
				if (devices[lauf].getUuid().equals(
						attribute[lauf2].getDevice().getUuid())) {
					if (gefunden==false) {
						State status = AttributUtil.getStatus(
								sessionBean.getZipatoSession(), devices[lauf].getUuid());
						if (status != null) {
							config.setOnlineStatus(status.getOnlineState());
						}
					}
					gefunden = true;
					config.getAttribute().add(attribute[lauf2]);
					if (config.getRaum() == null
							&& attribute[lauf2].getRoom() != null) {
						config.setRaum(attribute[lauf2].getRoom().getName());
					}

				}
				lauf2++;
			}
			lauf++;
			if (gefunden && config.getOnlineStatus()!=null) {
				deviceConfig.add(config);
			}

		}

	}

	public List<DeviceConfig> getElementList() {
		return sessionBean.getDeviceConfig();
	}

	public Class<? extends ViewConfig> showElement(final DeviceConfig element) {
		return zipatoBean.showElement(element);
	}

}
