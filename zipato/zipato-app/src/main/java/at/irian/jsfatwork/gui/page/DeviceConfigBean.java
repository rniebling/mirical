package at.irian.jsfatwork.gui.page;




import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

@Named
@ViewAccessScoped
public class DeviceConfigBean extends CustomerBeanBase {

	private static final long serialVersionUID = 380451624179817802L;

    private boolean collapsed = false;


    @PostConstruct
    public void init() {
        super.init();
    }

   
   

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

}
