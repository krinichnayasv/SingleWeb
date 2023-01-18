package main;

import model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import model.ToDoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoListController {

      @Autowired
    private ToDoListRepository toDoRepository;



    @GetMapping("/businesses/")
    public List<ToDoList> list() {
        Iterable<ToDoList> businessIterable = toDoRepository.findAll();
        ArrayList<ToDoList> businesses = new ArrayList<>();
        for(ToDoList business : businessIterable) {
            businesses.add(business);
        }
        return businesses;
    }

    @PostMapping("/businesses/")
    public int addOne(ToDoList business) {
        ToDoList newBusiness = toDoRepository.save(business);
        return newBusiness.getId();
    }

    @GetMapping("/businesses/{id}")
    public ResponseEntity getBusiness(@PathVariable int id) {
        Optional<ToDoList> optionalList = toDoRepository.findById(id);
        if (!optionalList.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalList.get(), HttpStatus.OK);
    }

    @DeleteMapping("/businesses/{id}")
    public ResponseEntity deleteBusiness(@PathVariable int id) {
        Optional<ToDoList> optionalList = toDoRepository.findById(id);
        if (!optionalList.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        toDoRepository.deleteById(id);
        return new ResponseEntity(optionalList.get(), HttpStatus.OK);
    }


    @DeleteMapping("/businesses/")
    public void deleteAll(){
        toDoRepository.deleteAll();
    }

    @PutMapping ("/businesses/{id}")
    public ResponseEntity updateBusiness(@PathVariable int id, String name, String date) {
//        ToDoList business = Storage.updateBusiness(id, name, date);
//        if (business == null) {
//           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } return new ResponseEntity(business, HttpStatus.OK);

        Optional<ToDoList> optionalList = toDoRepository.findById(id);
        if (!optionalList.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ToDoList newBusiness = optionalList.get();
        newBusiness.setName(name);
        newBusiness.setDate(date);
        toDoRepository.save(newBusiness);
        return new ResponseEntity(newBusiness, HttpStatus.OK);
    }

}
