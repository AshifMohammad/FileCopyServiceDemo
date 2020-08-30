package com.ftpservice.ftpfiles.controllers;

import com.ftpservice.ftpfiles.services.FileCopyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class FileController {

  FileCopyService fileCopyService = new FileCopyService();

  @GetMapping("/copy")
  public String copy() {

    return fileCopyService.copyFiles();
  }

  @GetMapping("/")
  public String home() {

    return "home page";
  }

  @GetMapping("/error")
  public String Error() {

    return "Home page for the controller";
  }
}
