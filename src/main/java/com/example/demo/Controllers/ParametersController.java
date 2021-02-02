package com.example.demo.Controllers;

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
public class ParametersController
{

    private final String on_table = "parameter";

    @Autowired
    private CacheParameterService parameterService;

    @Autowired
    private LogService logService;


    @GetMapping(value = "allParameters")
    public ModelAndView ShowAllParameters()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allParameters");
        List<Parameter> parameters = new ArrayList<>();
        parameterService.getAllParameters().forEach(parameters::add);
        modelAndView.addObject("parameters",parameters);
        return modelAndView;
    }

    @GetMapping("parameter/edit/{key}")
    public String editParameter(@PathVariable("key") String key, Model model)
    {
        Parameter parameter = parameterService.getParameterById(key);

        if (parameter != null)
        {
            System.out.println("parameter up to view : "+parameter);
            model.addAttribute("parameter", parameter);
            return "Update_Parameter";
        }
        else
        {
            model.addAttribute("message",String.format("there is no Parameter with key : ( %s ) !",key));
            return "Error_Not_Found";
        }

    }

    @PostMapping("parameter/update/{key}")
    public String updateParameter(@PathVariable("key") String key, @Valid Parameter parameter, BindingResult result, Model model)
    {

        System.out.println("parameter from view : "+parameter);

        if (result.hasErrors())
        {
            System.err.println("valid error !");

            Parameter oldParameter = parameterService.getParameterById(key);
            model.addAttribute("parameter",oldParameter);

            return "Update_Parameter";
        }

        System.out.println("parameter from view : "+parameter);
        Parameter oldParameter = parameterService.getParameterById(key);

        if (oldParameter != null)
        {
            if (oldParameter.getVersion() == parameter.getVersion())
            {
                Parameter newparameter = parameterService.update(parameter,key);

                List<Parameter> parameters = new ArrayList<>();
                parameterService.getAllParameters().forEach(parameters::add);

                //paraRepo.findByMykey(parameter.getMykey());


                model.addAttribute("parameters", parameters);


                logService.save(on_table,key,"update");


                return "redirect:/allParameters";
            }
            else
            {
                model.addAttribute("message",String.format("Sorry please try again ,there is error in server ,someone make transaction on this parameter with key ( %s )!",key));
                return "Error_Not_Found";
            }
        }
        else
        {
            model.addAttribute("message",String.format("there is no parameter with key : ( %s ) !",key));
            return "Error_Not_Found";
        }

    }


}
