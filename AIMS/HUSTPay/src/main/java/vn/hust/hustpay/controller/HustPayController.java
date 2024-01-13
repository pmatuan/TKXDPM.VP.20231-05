package vn.hust.hustpay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HustPayController {
  @GetMapping("")
  public String home(){
    return "index";
  }
}
