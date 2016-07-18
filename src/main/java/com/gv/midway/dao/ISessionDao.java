package com.gv.midway.dao;

import com.gv.midway.pojo.session.SessionBean;

public interface ISessionDao {

    public SessionBean getSessionBean();

    public SessionBean saveSesionBean(SessionBean sessionBean);

}
