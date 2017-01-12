/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luckcompanies;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luckcompanies.models.*;
import java.util.Map;

/**
 *
 * @author johnkirksey
 */
@RestController
public class FlyLUCKController {
    
    @RequestMapping("/getallflights")
    public static String GetAllFlights(@RequestParam(value="month", defaultValue="1") String name){
        return "Hello " + name + "!";
    }
    
    @RequestMapping("/getflights")
    public String GetFlights (@RequestParam Map<String, String> RequestParams)
    {
        
        String startDate = "";
        String endDate = "";
        
        startDate = RequestParams.get("start");
        endDate = RequestParams.get("end");
        
        FlyLUCKService service = new FlyLUCKService();
        return service.GetFlights(startDate, endDate);
        
        
    }
    
}
