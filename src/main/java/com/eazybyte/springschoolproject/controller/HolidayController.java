package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Holiday;
import com.eazybyte.springschoolproject.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Controller
public class HolidayController {
    @Autowired
    private HolidayRepository holidayRepository;
@RequestMapping("/holiday/{display}")
    public String displayHolidays(@PathVariable String display , Model model){
      if(null  != display && display.equals("all")){
          model.addAttribute("festival", true);
          model.addAttribute("federal", true);
    } else if(null!= display && display.equals("federal")){
          model.addAttribute("federal", true);
      }else if(null!= display && display.equals("festival"))
      {
          model.addAttribute("festival",true);
      }
       Iterable<Holiday>holidays =  holidayRepository.findAll();
      List<Holiday> holidaysList = StreamSupport.stream(holidays.spliterator(),false).collect(Collectors.toList());
        Holiday.Type[] types = Holiday.Type.values();
        for(Holiday.Type  type : types){
            model.addAttribute(type.toString(),
                    holidaysList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holiday";
    }

}
