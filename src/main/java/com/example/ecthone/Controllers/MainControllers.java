package com.example.ecthone.Controllers;

import com.example.ecthone.DAO.PersonDAO;
import com.example.ecthone.Model.Person;
import com.example.ecthone.Model.Role;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainControllers {
    private final PersonDAO personDAO;

    @Autowired
    public MainControllers(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping("/")
    public String index() {
        return "auth";
    }

    @PostMapping("/telegram-login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> telegramLogin(@RequestBody String body) {
        JSONObject obj = new JSONObject(body);
        Map<String, String> response = new HashMap<>();
        try {
            Person person = personDAO.findByName(obj.getString("first_name"));
            response.put("redirectUrl","/home?id="+person.getId());
        }
        catch (Exception e) {
            Person person = new Person(obj.getString("first_name"),"","", Role.User,true,obj.getString("first_name"));
            personDAO.save(person);
            response.put("redirectUrl","/home?id="+personDAO.findAll().size());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logining")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody String body) {
        JSONObject obj = new JSONObject(body);
        Map<String, String> response = new HashMap<>();
        try {
            Person person = obj.getString("login").contains("@") ?
                    personDAO.findByEmail(obj.getString("login")) :
                    personDAO.findByPhone(obj.getString("phone"));
            if(Integer.parseInt(person.getPassword()) == obj.getString("password").hashCode()) {
                response.put("redirectUrl","/home?id="+person.getId());
            }else{
                response.put("redirectUrl","/");
            }
        }
        catch (Exception e) {
            response.put("redirectUrl","/");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<Map<String, String>> registration(@RequestBody String body) {
        JSONObject obj = new JSONObject(body);
        Map<String, String> response = new HashMap<>();
        String firstName = obj.getString("first_name");
        String lastName = obj.getString("last_name");
        String patronymic = obj.getString("patronymic");
        String fio= lastName+"_"+firstName+"_"+patronymic;
        String email = obj.getString("email");
        String password = obj.getString("password");
        String phone = obj.getString("phone");
        Person person = new Person(fio,email,phone, Role.User,true,password.hashCode()+"");
        personDAO.save(person);
        response.put("redirectUrl","/home?id="+personDAO.findAll().size());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/reg")
    public String reg() {
        return "registartion";
    }
}
