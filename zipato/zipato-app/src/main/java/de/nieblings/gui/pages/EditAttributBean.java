package de.nieblings.gui.pages;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import de.nieblings.zipato.data.AttributUtil;
import de.nieblings.zipato.data.attribut.Attribut;
import de.nieblings.zipato.data.attribut.Value;

@Named
@ViewAccessScoped
public class EditAttributBean implements Serializable {

	private static final long serialVersionUID = 8649982876548428091L;
	

	@Inject
    private SessionBean sessionBean;

	private Attribut attribut;
	private String value;
	private boolean collapsed = false;

	public Class<? extends ViewConfig> editAttribut(Attribut attribut) {
		this.attribut = attribut;
		value=attribut.getValue().getValue();
		return View.EditAttribut.class;
	}

	public Class<? extends ViewConfig> showListe() {
		return de.nieblings.gui.pages.View.ZipatoList.class;
	}


	public Boolean isAttributEditable() {
		return (attribut!=null && attribut.getDefinition().getWritable());
	}

	
	
	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public Attribut getAttribut() {
		return attribut;
	}
	
	public void saveAttribut() {
		
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {
		this.value=value;
		Value attibutValue =attribut.getValue();
		attibutValue.setValue(value);
		
		attribut.setValue(attibutValue);
		AttributUtil.setValue(sessionBean.getZipatoSession(), attribut, value);
	}

	public void setAttribut(Attribut attribut) {
		this.attribut = attribut;
	}
	
	

}
