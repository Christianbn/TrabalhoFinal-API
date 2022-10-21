//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.tf.domain.Cliente;
//import com.example.tf.repository.ClienteRepository;
//
//@Service
//public class ClienteService {
//    
//    @Autowired
//    ClienteRepository clienteRepository;
//    
//    public List<Cliente> findAll() {
//        return clienteRepository.findAll();
//    }
//    
//    public Optional<Cliente> findById(Long id) {
//        return clienteRepository.findById(id);
//    }
//    
//    @Transactional
//    public Cliente PostCliente(Cliente cliente) throws EmailException {
//        
//        if (clienteRepository.findByEmailCliente(cliente.getEmailCliente()) != null) {
//            throw new EmailException("Email já existente");
//        }
//        
//        Cliente cliente1 = new Cliente();
//        cliente1.setNomeCompletoCliente(cliente.getNomeCompletoCliente());
//        cliente1.setEmailCliente(cliente.getEmailCliente());
//        cliente1.setCpfCliente(cliente.getCpfCliente());
//        cliente1.setTelefoneCliente(cliente.getTelefoneCliente());
//        cliente1.setDataNascimentoCliente(cliente.getDataNascimentoCliente());
//        cliente1.setEndereco(cliente.getEndereco());
//        
//        cliente1 = clienteRepository.save(cliente1);
//        return cliente1;
//    }
//}