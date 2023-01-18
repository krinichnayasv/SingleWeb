package main;


import model.ToDoList;
import model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class DefaultController {

    @Autowired
    private ToDoListRepository listRepository;

    @Value("${someParameter}")
    private Integer someParameter;

    @RequestMapping("/")
    public String index(Model model) {

        Iterable<ToDoList> listIterable = listRepository.findAll();
        ArrayList<ToDoList> businesses = new ArrayList<>();
        for(ToDoList business : listIterable) {
            businesses.add(business);
        }
        model.addAttribute("businesses", businesses);
        model.addAttribute("businessesCount", businesses.size());
        model.addAttribute("someParameter", someParameter);

        return "index";
    }

}
