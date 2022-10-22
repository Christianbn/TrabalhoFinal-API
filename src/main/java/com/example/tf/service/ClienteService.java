package com.example.tf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tf.DTO.ClienteDTO_POST;
import com.example.tf.domain.Cliente;
import com.example.tf.exception.CpfException;
import com.example.tf.exception.EmailException;
import com.example.tf.repository.ClienteRepository;

@Service
public class ClienteService {
   
    @Autowired
    ClienteRepository clienteRepository;
    
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }
    
    @Transactional
    public Cliente PostCliente(ClienteDTO_POST cliente) throws EmailException, CpfException {      
       if (clienteRepository.findByEmailCliente(cliente.getEmailCliente()) != null) {
            throw new EmailException();
        }    
        if (clienteRepository.findByCpfCliente(cliente.getCpfCliente()) != null) {
			throw new CpfException();
		}
        
        Cliente clienteBanco = new Cliente();
        clienteBanco.setNomeCompletoCliente(cliente.getNomeCompletoCliente());
        clienteBanco.setEmailCliente(cliente.getEmailCliente());
        clienteBanco.setCpfCliente(cliente.getCpfCliente());
        clienteBanco.setTelefoneCliente(cliente.getTelefoneCliente());
        clienteBanco.setDataNascimentoCliente(cliente.getDataNascimentoCliente());
             
        clienteRepository.save(clienteBanco);
        
        return clienteBanco;
    }
   
    public Boolean DeleteCategoria(Long id) {
		Optional<Cliente> categoriaTemp = clienteRepository.findById(id);
		if (!categoriaTemp.isPresent()) {
			return false;
		}
		clienteRepository.deleteById(id);
		return true;
	}  
    
}