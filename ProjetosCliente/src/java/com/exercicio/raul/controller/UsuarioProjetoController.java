package com.exercicio.raul.controller;

import com.exercicio.raul.entidades.UsuarioProjeto;
import com.exercicio.raul.controller.util.JsfUtil;
import com.exercicio.raul.controller.util.JsfUtil.PersistAction;
import com.exercicio.raul.facade.UsuarioProjetoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("usuarioProjetoController")
@SessionScoped
public class UsuarioProjetoController implements Serializable {

    @EJB
    private com.exercicio.raul.facade.UsuarioProjetoFacade ejbFacade;
    private List<UsuarioProjeto> items = null;
    private UsuarioProjeto selected;

    public UsuarioProjetoController() {
    }

    public UsuarioProjeto getSelected() {
        return selected;
    }

    public void setSelected(UsuarioProjeto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioProjetoFacade getFacade() {
        return ejbFacade;
    }

    public UsuarioProjeto prepareCreate() {
        selected = new UsuarioProjeto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioProjetoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;   
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioProjetoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioProjetoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; 
            items = null;   
        }
    }

    public List<UsuarioProjeto> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UsuarioProjeto getUsuarioProjeto(java.lang.Short id) {
        return getFacade().find(id);
    }

    public List<UsuarioProjeto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsuarioProjeto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UsuarioProjeto.class)
    public static class UsuarioProjetoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioProjetoController controller = (UsuarioProjetoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioProjetoController");
            return controller.getUsuarioProjeto(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UsuarioProjeto) {
                UsuarioProjeto o = (UsuarioProjeto) object;
                return getStringKey(o.getIdusuarioProjeto());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuarioProjeto.class.getName()});
                return null;
            }
        }

    }

}
