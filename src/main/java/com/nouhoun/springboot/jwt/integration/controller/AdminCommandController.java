package com.nouhoun.springboot.jwt.integration.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/shell")
public class AdminCommandController {
    @RequestMapping(value ="/cmd")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public String getCommandResponse(){
        String cmd = "dir C:\\Users";
        String response = "";

        try {
            response = execCmd(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    public static String execCmd(String cmd) throws java.io.IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        java.io.InputStream is = proc.getInputStream();
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        String val = "";
        if (s.hasNext()) {
            val = s.next();
        }
        else {
            val = "";
        }
        return val;
    }
}
