package com.cmx.mall.listener;

import com.cmx.mall.service.VisitCountService;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VisitCountListener implements HttpSessionListener {
    @Autowired
    private VisitCountService visitCountService;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session已经创建");

        Date date = new Date(se.getSession().getCreationTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String visitDate = simpleDateFormat.format(date);
        visitCountService.addVisitCount(visitDate);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
