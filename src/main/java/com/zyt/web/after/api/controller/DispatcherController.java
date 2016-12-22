package com.zyt.web.after.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="api/dispatcher")
public class DispatcherController extends ApiController{
	
	@RequestMapping(value = "{hPath}")
	public String dispatcher(@PathVariable String hPath){
		
		return "after/dispatcher/"+hPath;
	}
	
}
