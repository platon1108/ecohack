package com.example.ecthone.Controllers;

import com.example.ecthone.DAO.EventDAO;
import com.example.ecthone.DAO.PersonDAO;
import com.example.ecthone.Model.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OrganizationController {
    private final PersonDAO personDAO;
    private final EventDAO eventDAO;

    @Autowired
    public OrganizationController(PersonDAO personDAO,EventDAO eventDAO) {
        this.personDAO = personDAO;
        this.eventDAO = eventDAO;
    }

    @PostMapping("/event")
    @ResponseBody
    public ResponseEntity<Map<String,String>> event(@RequestBody String body, Model model) {
        JSONObject jsonObject = new JSONObject(body);
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        Format format = Format.getFormat(jsonObject.getString("format"));
        Duration duration = Duration.getDuration(jsonObject.getString("duration"));
        String address = jsonObject.getString("address");
        Integer personid = jsonObject.getInt("personid");
        Event event = new Event(name,description,format,duration,address,personid);
        eventDAO.save(event);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl","/home?id="+personid);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/event")
    @ResponseBody
    public ResponseEntity<Map<String,String>> event_patch(@RequestBody String body, Model model) {
        JSONObject jsonObject = new JSONObject(body);
        System.out.println(body);
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        Format format = Format.getFormat(jsonObject.getString("format"));
        Duration duration = Duration.getDuration(jsonObject.getString("duration"));
        String address = jsonObject.getString("address");
        Integer personid = jsonObject.getInt("personid");
        Event event = new Event(name,description,format,duration,address,personid);
        event.setId(jsonObject.getInt("id"));
        eventDAO.update(event);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl","/home?id="+personid);
        return  ResponseEntity.ok(response);
    }
    @DeleteMapping("/event")
    @ResponseBody
    public ResponseEntity<Map<String,String>> event_delete(@RequestBody String body, Model model) {
        JSONObject jsonObject = new JSONObject(body);
        System.out.println(jsonObject);
        eventDAO.deleteById(jsonObject.getInt("id"));
        Integer personid = jsonObject.getInt("personid");
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl","/home?id="+personid);
        return  ResponseEntity.ok(response);
    }


    @GetMapping("/event/{id}")
    public String event(@PathVariable Integer id, Model model) {
        model.addAttribute("event", eventDAO.findById(id));
        return "EventPage";
    }

    @RequestMapping("/home")
    public String home(@RequestParam("id") Integer id , Model model) {
        model.addAttribute("name", personDAO.findById(id).getFio());
        model.addAttribute("ids", id);
        model.addAttribute("events", eventDAO.findPersonId(id));
        return "home";
    }
}
