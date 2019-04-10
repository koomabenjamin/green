package com.shapeworks.mivule.Entities;

/**
 * Created by KoomaBenjamin on 28/11/2017.
 */

public class Employee {

    public String name, email, intro, experience, status;

    public Employee(){

    }

    public Employee(String emp_name, String emp_email, String emp_intro, String emp_experience, String emp_status) {

        this.name = emp_name;
        this.email = emp_email;
        this.intro = emp_intro;
        this.experience = emp_experience;
        this.status = emp_status;

    }

}
