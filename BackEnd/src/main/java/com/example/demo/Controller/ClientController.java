package com.example.demo.Controller;

import com.example.demo.model.Client;
import com.example.demo.model.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){this.clientService=clientService;}


    @GetMapping("/fetchClients")
    public ResponseEntity<?> fetchClients(){
        try{
           return  ResponseEntity.ok(clientService.fetchClients());
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CREATED).body("fetch Clients failed");
        }
    }

    @PostMapping("/createClient")
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client, BindingResult bindingResult){
  if(bindingResult.hasErrors()){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getValidationErrors(bindingResult));
  }
        clientService.createClient((client));
        return ResponseEntity.status(HttpStatus.CREATED).body("Client created successfully");

    }

    @PostMapping("/editClient")
    public ResponseEntity<?> editClient(@Valid @RequestBody Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getValidationErrors(bindingResult));
        }
        clientService.editClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client edited successfully");
    }

    private String getValidationErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }

}
