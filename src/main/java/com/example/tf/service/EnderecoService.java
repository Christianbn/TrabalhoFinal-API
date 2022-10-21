package com.example.tf.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tf.domain.Endereco;
import com.example.tf.repository.EnderecoRepository;

@Service
public class EnderecoService {
		
		@Autowired
		EnderecoRepository enderecoRepository;

		public List<Endereco> findAll() {
			return enderecoRepository.findAll();
		}

		public Optional<Endereco> findById(Long id) {
			return enderecoRepository.findById(id);
		}

		@Transactional
		public Endereco PostEndereco(Endereco endereco) {
//			Optional<Endereco> enderecoTemp = enderecoRepository.findByNome(endereco.getNomeEndereco());
//			if (enderecoTemp.isPresent()) {
//				return null;
//			}
			enderecoRepository.save(endereco);
			return endereco;
		}

		public Optional<Endereco> PutEndereco(Endereco Endereco, Long id) {
			Optional<Endereco> EnderecoTemp = enderecoRepository.findById(id);
			if (EnderecoTemp.isPresent()) {
				Endereco.setIdEndereco(id);
				Endereco = enderecoRepository.save(Endereco);
				return Optional.ofNullable(Endereco);
			}
			return null;
		}

		public Boolean Delete(Long id) {
			Optional<Endereco> enderecoTemp = enderecoRepository.findById(id);
			if (!enderecoTemp.isPresent()) {
				return false;
			}
			enderecoRepository.deleteById(id);
			return true;
		}
}
