package com.shapeworks.mivule.Data;

import com.shapeworks.mivule.Entities.Articles;
import com.shapeworks.mivule.Entities.Employee;
import com.shapeworks.mivule.Entities.Message;
import com.shapeworks.mivule.Entities.Payments;
import com.shapeworks.mivule.Entities.Products;
import com.shapeworks.mivule.Entities.Profiles;
import com.shapeworks.mivule.Entities.Projects;
import com.shapeworks.mivule.Entities.Tasks;
import com.shapeworks.mivule.Entities.Users;
import com.shapeworks.mivule.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koomabenjamin on 12/11/17.
 */

public class FirebaseSeeder {

    private Tasks task;
    private List<Articles> list = new ArrayList<>();
    private List<Tasks> list_task = new ArrayList<>();
    private List<Payments> list_payments = new ArrayList<>();
    private List<Message> list_messages = new ArrayList<>();
    private List<Employee> list_employees = new ArrayList<>();
    private List<Products> list_products = new ArrayList<>();
    private List<Profiles> list_profiles = new ArrayList<>();
    private FirebaseDatabase fireDb = FirebaseDatabase.getInstance();
    private DatabaseReference fireRef = fireDb.getReference("news");
    private Profiles profile;
    private Users user;

    public void addArticles(){

        Articles artic = new Articles();
        String userId = fireRef.push().getKey();
        String[] url = {"https://firebasestorage.googleapis.com/v0/b/mivule-30c90.appspot.com/o/content%2Fimages%2Fkyakachwa%2F01.png?alt=media&token=12436a05-c9b3-494a-93d7-9040164f43e5",
                "https://firebasestorage.googleapis.com/v0/b/mivule-30c90.appspot.com/o/content%2Fimages%2Fkyakachwa%2F02.png?alt=media&token=55ae8b00-a4a4-4f17-b1f7-bdd75a7b5a9e",
                "https://firebasestorage.googleapis.com/v0/b/mivule-30c90.appspot.com/o/content%2Fimages%2Fkyakachwa%2F03.png?alt=media&token=07bea6d7-c787-4e0b-8138-4a61cd34200c",
                "https://firebasestorage.googleapis.com/v0/b/mivule-30c90.appspot.com/o/content%2Fimages%2Fkyakachwa%2F04.png?alt=media&token=8a175596-25f9-43d7-a115-e8ed6672b73f"};


        artic = new Articles("topic", "0UL38u3joJcfxVQ1EEpCspzHMwz1", url[0], "not yet availed");
        list.add(artic);

        artic = new Articles("topic", "0UL38u3joJcfxVQ1EEpCspzHMwz1", url[1], "not yet availed");
        list.add(artic);

        artic = new Articles("topic", "0UL38u3joJcfxVQ1EEpCspzHMwz1", url[2], "not yet availed");
        list.add(artic);

        artic = new Articles("topic", "0UL38u3joJcfxVQ1EEpCspzHMwz1", url[3], "not yet availed");
        list.add(artic);

        //fireRef.child(userId).setValue(tasks);
    }

    public List<Tasks> prepareTaskData() {

//        list_task = new ArrayList<>();
//
//        Tasks task = new Tasks("01/04/2018", "56days", "it will take sometime grade and clear the land", "Planting");
//        list_task.add(task);
//
//        task = new Tasks("01/04/2018", "56days", "it will take sometime grade and weed the crop", "Weeding");
//        list_task.add(task);
//
//        task = new Tasks("01/04/2018", "56days", "it will take sometime grade and plough the field", "Ploughing");
//        list_task.add(task);
//
//        task = new Tasks("01/04/2018", "56days", "it will take sometime grade and cut the trees", "Cutting");
//        list_task.add(task);
//
//        task = new Tasks("01/04/2018", "56days", "it will take sometime grade and replant the damaged crop", "Re-planting");
//        list_task.add(task);
//
//        task = new Tasks("01/04/2018", "56days", "it will take sometime grade and fertilize the land", "Fertilizing");
//        list_task.add(task);
//
        return list_task;
    }

    public List<Payments> preparePaymentsData() {

        list_payments = new ArrayList<>();

//        Payments payment = new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment = new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment = new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment =  new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment =  new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment =  new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment = new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment = new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment =  new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment =  new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);
//
//        payment =  new Payments("Clearing", "this payment is for clearing the land", "fdjliuxjdnslioe654ffrjfiue", "10/06/2017", "10:58am", "electronic");
//        list_payments.add(payment);

        return list_payments;
    }

    public List<Message> prepareMessageData() {

        list_messages = new ArrayList<>();

//        Message message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);
//
//        message = new Message("Clearing", "Tree planting concluded", "eabrya@gmail.com", "10/06/2017", "10:58am");
//        list_messages.add(message);


        return list_messages;
    }

    public List<Employee> prepareEmployeeData() {

        list_employees = new ArrayList<>();

//        Employee employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);
//
//        employee = new Employee("Potus Cruzer Ssebulime", "pcs@jtg.com", "am team player and hardworker with a wealth of experience", "5 years", "working");
//        list_employees.add(employee);


        return list_employees;

    }

    public List<Products> prepareProductsData() {

//        list_products = new ArrayList<>();
//
//        Products product = new Products("muvule", "25,000 ugx", "50 logs", "depleted");
//        list_products.add(product);
//
//        product = new Products("eucaliptas", "55,000 ugx", "10 logs", "depleted");
//        list_products.add(product);
//
//
//        product = new Products("redwood", "45,000 ugx", "30 logs", "depleted");
//        list_products.add(product);
//
//
//        product = new Products("backshire", "75,000 ugx", "50 logs", "depleted");
//        list_products.add(product);
//
//
//        product = new Products("pine", "100,000 ugx", "500 logs", "depleted");
//        list_products.add(product);


        return list_products;
    }

    public List<Profiles> prepareProfile(){

        return list_profiles;
    }
}
