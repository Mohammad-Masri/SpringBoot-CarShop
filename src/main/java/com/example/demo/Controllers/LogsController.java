package com.example.demo.Controllers;

import com.example.demo.Models.Log;
import com.example.demo.Models.Parameter;
import com.example.demo.Services.CacheParameterService;
import com.example.demo.Services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class LogsController
{

    @Autowired
    private LogService logService;

//    @Autowired
//    private paraRepo paraRepo;


    @GetMapping(value = "allLogs")
    public ModelAndView ShowAllParameters()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allLogs");
        List<Log> logs = new ArrayList<>();
        logService.getAllLogs().forEach(logs::add);
        modelAndView.addObject("logs",logs);
        return modelAndView;
    }




}
