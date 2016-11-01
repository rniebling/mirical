package de.nieblings.gui.pages;

import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.Page;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.PageBean;

/**
 * @author Ralf Niebling
 */
@Page(basePath = "")
public interface View extends ViewConfig {

    @Page
    @PageBean(ZipatoListBean.class)
    public class ZipatoList implements View {
    }
    

    @Page(navigation = Page.NavigationMode.REDIRECT)
    @PageBean(ZipatoBean.class)
    public class Zipato implements View {
    }

    
    @Page(navigation = Page.NavigationMode.REDIRECT)
    @PageBean(EditAttributBean.class)
    public class EditAttribut implements View {
    }
    

}
