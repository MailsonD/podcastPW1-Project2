package com.ifpb.listener;

import com.ifpb.control.controllers.LoginBean;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

public class AuthListener implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent event) {

        String view = event.getFacesContext().getViewRoot().getViewId();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();

        if(request.getSession()==null){
            event.getFacesContext().getApplication().getNavigationHandler().handleNavigation(context,null,"login");
        }
        LoginBean loginBean = (LoginBean)request.getSession(false).getAttribute("loginBean");
        if(!view.equals("index.xhtml")){
            if(loginBean ==null || loginBean.getUser()==null){
                event.getFacesContext().getApplication().getNavigationHandler().handleNavigation(context,null,"login");
            }
        }else{
            if(loginBean!=null && loginBean.getUser()!=null){
                event.getFacesContext().getApplication().getNavigationHandler().handleNavigation(context, null, "timeline");
            }
        }



    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {

    }

    @Override
    public PhaseId getPhaseId() {
        return null;
    }
}
