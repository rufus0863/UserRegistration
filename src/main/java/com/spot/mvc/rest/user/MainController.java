package com.spot.mvc.rest.user;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// prova git commit
@Controller
public class MainController {

	public static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "/template/login";
    }

    @RequestMapping("/registrazione")
    public String registrazione(Map<String, Object> model) {
        return "/template/registrazione";
    }

}
