package at.irian.jsfatwork.gui.jsf.component;

import javax.faces.view.facelets.*;

public class InputSpinnerTagHandler extends ComponentHandler {

    private static final String ATTR_INC = "inc";

    private TagAttribute inc;

    public InputSpinnerTagHandler(ComponentConfig config) {
        super(config);
        this.setInc(getRequiredAttribute(ATTR_INC));
    }

	public TagAttribute getInc() {
		return inc;
	}

	public void setInc(TagAttribute inc) {
		this.inc = inc;
	}

}
