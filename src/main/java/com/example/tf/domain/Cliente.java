package com.example.tf.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_cliente", nullable = false)
	private Long idCliente;
	
	

	@NotBlank
	@Size(max = 50)
	@Column(name="nome_completo_cliente", nullable = false, length = 50)
	private String nomeCompletoCliente; 
	
	@Email
	@NotBlank
	@Size(max = 80)
	@Column(name="email_cliente", nullable = false, length = 80,unique = true)
	private String emailCliente;
	
	@CPF
	@NotBlank
	@Size(max = 11)
	@Column(name="cpf_cliente", nullable = false, length = 11, unique = true)
	private String cpfCliente; 
	
	
	@NotBlank
	@Size(max = 40)
	@Column(name="telefone_cliente", nullable = false, length = 40)
	private String telefoneCliente;
	
	@Column(name= "data_nascimento_cliente")
	private LocalDate dataNascimentoCliente;
	
	@NotNull
	@OneToOne()
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCompletoCliente() {
		return nomeCompletoCliente;
	}

	public void setNomeCompletoCliente(String nomeCompletoCliente) {
		this.nomeCompletoCliente = nomeCompletoCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public LocalDate getDataNascimentoCliente() {
		return dataNascimentoCliente;
	}

	public void setDataNascimentoCliente(LocalDate dataNascimentoCliente) {
		this.dataNascimentoCliente = dataNascimentoCliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(idCliente, other.idCliente);
	}
	
	
}
