package de.nieblings.zipato.data;

import java.util.ArrayList;
import java.util.List;

import de.nieblings.zipato.data.attribut.Attribut;
import de.nieblings.zipato.data.internal.DeviceConfig;


public class start {
	public static void main(String[] args) {
		
		final ZipatoSession session = Login.get("jjj","lll");
		VirtualEndpoints[] devices= Endpoints.getDevices(session);
		Attribut[] attribute = AttributUtil.getAttribute(session);
		
		List<DeviceConfig> deviceConfig=new ArrayList<DeviceConfig>();
		
		
		int lauf = 0;
		while (lauf < devices.length) {
			final DeviceConfig config = new DeviceConfig();
			config.setDevice(devices[lauf]);
			deviceConfig.add(config);
			int lauf2 = 0;
			while (lauf2 < attribute.length) {
				if(devices[lauf].getUuid().equals(attribute[lauf2].getDevice().getUuid())){
					config.getAttribute().add(attribute[lauf2]);
					
				}
				lauf2++;
			}
			lauf++;
		}
		System.exit(0);
	}

}
