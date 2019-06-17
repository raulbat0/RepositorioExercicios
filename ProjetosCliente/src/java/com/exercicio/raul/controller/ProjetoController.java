package com.exercicio.raul.controller;

import com.exercicio.raul.entidades.Projeto;
import com.exercicio.raul.controller.util.JsfUtil;
import com.exercicio.raul.controller.util.JsfUtil.PersistAction;
import com.exercicio.raul.entidades.Empresa;
import com.exercicio.raul.facade.ProjetoFacade;
import com.exercicio.raul.util.ParamDB;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
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

@Named("projetoController")
@SessionScoped
public class ProjetoController implements Serializable {

    @EJB
    private com.exercicio.raul.facade.ProjetoFacade ejbFacade;
    private List<Projeto> items = null;
    private Projeto selected, projeto;

    public ProjetoController() {
    }

    public Projeto getSelected() {
        return selected;
    }

    public void setSelected(Projeto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProjetoFacade getFacade() {
        return ejbFacade;
    }

    public Projeto prepareCreate() {
        selected = new Projeto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() throws NoClassDefFoundError, SQLException, ClassNotFoundException, ParseException {
        //Variáveis auxiliares para cálculo do intervalo de tempo 
        Long dif, desat;
        Date desativacao = new Date();
        if (selected.getDataAtivacao().before(validaProjeto())) {

            //Cálculo das novas datas de ativação e desativação do projeto
            dif = selected.getDataDesativacao().getTime() - selected.getDataAtivacao().getTime();//Diferença em mili-segundos entre as duas datas
            selected.setDataAtivacao(validaProjeto());//Nova data de ativação
            desat = dif + selected.getDataAtivacao().getTime();//Calsulando a nova data de desativação
            desativacao.setTime(desat);//Convertendo data de desativação de mili-segundos para data
            selected.setDataDesativacao(desativacao);//Nova data de desativação

            //Persistindo os dados e retornando a mensagem da nova vigência do projeto ao usuário
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProjetoAlterado")
                    + selected.getDataAtivacao() + " até " + selected.getDataDesativacao() + ".");
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }

            //Caso os dados estejam adequados, o sistema persistirá os dados e emitirá uma mensagem de "sucesso"    
        } else {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProjetoCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProjetoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProjetoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; 
            items = null;    
        }
    }

    public Date validaProjeto() throws NoClassDefFoundError, SQLException, ClassNotFoundException, NullPointerException, ParseException {
        try {
            //Instanciando e criando objetos auxiliares para este processo
            Projeto p = selected;
            Empresa e = p.getIdEmpresa();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con;
            
            //Buscando dados para acesso ao BD
            ParamDB pr = new ParamDB();
            pr.setUrlDB(ResourceBundle.getBundle("/Bundle").getString("URLDB"));
            pr.setUserDB(ResourceBundle.getBundle("/Bundle").getString("UserDB"));
            pr.setSenhaDB(ResourceBundle.getBundle("/Bundle").getString("SenhaDB"));

            //Conectando com o BD, executando a query e armazenando resultados
            con = DriverManager.getConnection(pr.getUrlDB(), pr.getUserDB(), pr.getSenhaDB());
            String sql = ResourceBundle.getBundle("/Bundle").getString("QueryValidaProjeto");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setShort(1, e.getIdEmpresa());//Parâmetro 1: código da empresa
            stmt.setShort(2, Short.parseShort("1"));//Parâmetro 2: código para verificar se há projetos ativos na empresa (1 = "Ativo")
            ResultSet rs = stmt.executeQuery();//Armazenando o resultado do processamento da query no result set

            //Tratando o result set para definir se o projeto está ou não dentro da vigência de algum projeto ativo para a empresa
            if (rs.first() && rs.getString("data_desativacao") != null){
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");//Tratando a formatação da data 
                Date date;
                date = formato.parse(rs.getString("data_desativacao"));//Convertendo a data obtida do BD de String para Date
                stmt.close();
                con.close();
                return date;
                
            } else {
                //Caso não haja projetos em vigência para aquela empresa o sistema atribui a data de desativação
                //à data inicial de 01/01/1970 para não termos uma exception quando o método for chamado nesta condição
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Result set vazio");
                rs.close(); stmt.close(); con.close();
                return Date.from(Instant.EPOCH);
            }

        } catch (SQLException | NoClassDefFoundError | NullPointerException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Erro durante checagem da vigência", ex);
            JsfUtil.addErrorMessage((Exception) ex, ResourceBundle.getBundle("/Bundle").getString("ErroVigencia"));
            return null;
        }
    }

    public List<Projeto> getItems() {
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

    public Projeto getProjeto(java.lang.Short id) {
        return getFacade().find(id);
    }

    public List<Projeto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Projeto> getItemsAvailableSelectOne() {
        return getFacade().findSome();

    }

    @FacesConverter(forClass = Projeto.class)
    public static class ProjetoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProjetoController controller = (ProjetoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "projetoController");
            return controller.getProjeto(getKey(value));
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
            if (object instanceof Projeto) {
                Projeto o = (Projeto) object;
                return getStringKey(o.getIdProjeto());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Projeto.class.getName()});
                return null;
            }
        }

    }

}
