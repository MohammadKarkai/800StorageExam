package com.example.demo.model;

import com.example.demo.Repesotery.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    public List<Client> fetchClients(){
        try{
            return clientRepository.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null ;
    }

    public Client createClient(Client client){
        try {
            return this.clientRepository.save(client);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Client editClient(Client client){

        try {
            if (clientRepository.existsById(client.getId())) {
                return clientRepository.save(client);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            }
        return null ;
    }

}
