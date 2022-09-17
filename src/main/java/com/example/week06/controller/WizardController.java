package com.example.week06.controller;

import com.example.week06.pojo.Wizard;
import com.example.week06.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class WizardController {

    @Autowired
    private WizardService wizardService;

    @GetMapping(value = "/wizards")
    public List getWizards() {
        List<Wizard> wizardsList = wizardService.retrieveWizards();
        System.out.println(wizardsList);
        return wizardsList;
    }
    @PostMapping(value = "/addWizard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createWizards(@RequestBody Wizard wizard) {
        //System.out.println(payload);
        Wizard wizards =  wizardService.createWizard(wizard);
        //System.out.println(payload.get("sex").getClass());
        return "Wizard has been created";
    }

    @PostMapping(value = "/deleteWizard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteWizards(@RequestBody Wizard wizard){
        Wizard wizards = wizardService.retrieveByID(wizard.get_id());
        //System.out.println(wizards);
        boolean Status = wizardService.deleteWizard(wizards);
        return  Status?"Wizard has been deleted":"Nothing for delete";
    }
    @PostMapping(value="/updateWizard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateWizards(@RequestBody Wizard wizard){
        Wizard wizards = wizardService.retrieveByID(wizard.get_id());
        if (wizards != null){
            wizardService.updateWizard(wizard);
            return "Wizard has been updated";
        }else{
            return "Update fail";
        }
    }
}
