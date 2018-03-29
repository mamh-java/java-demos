package com.mamh.spring.demo.action;


public class PersonAction {
    private PersonService personService;

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public String execute() {
        System.out.println("execute().....");
        personService.save();
        return "success";
    }
}
