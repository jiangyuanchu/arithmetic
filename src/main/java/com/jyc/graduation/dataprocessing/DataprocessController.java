package com.jyc.graduation.dataprocessing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DataprocessController {

    @GetMapping("getDataProcess")
    public String getDataProcess(ServletRequest request, HttpServletRequest hrequest) throws Exception{
        MekaTest mekaTest = new MekaTest();
        String result = mekaTest.meka();
        request.setAttribute("result", result);
        return "datapage";
    }

}


