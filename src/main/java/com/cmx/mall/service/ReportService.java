package com.cmx.mall.service;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.mapper.ILoginMapper;
import com.cmx.mall.mapper.IReportMapper;
import com.cmx.mall.mapper.IVisitCountMapper;
import com.cmx.mall.model.VisitCount;
import com.cmx.mall.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private IReportMapper reportMapper;

    @Autowired
    private IVisitCountMapper visitCountMapper;

    public List<Map<String, Object>> queryForReport() {
        return reportMapper.selectForReport();
    }

    public List<VisitCount> queryVisitReport() {

        return visitCountMapper.selectAll();
    }
}
