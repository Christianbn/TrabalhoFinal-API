package com.example.tf.DTO;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;


public class ClienteDTO_POST {
	
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 50)
	@ApiModelProperty(value="Nome completo do cliente", required = true)
	private String nomeCompletoCliente; 
	
	@Email
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 80)
	@ApiModelProperty(value="E-mail do cliente", required = true)
	private String emailCliente;
	
	@CPF
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 11)
	@ApiModelProperty(value="CPF do cliente", required = true)
	private String cpfCliente; 
	
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 40)
	@ApiModelProperty(value="Telefone do cliente", required = true)
	private String telefoneCliente;
	
	@ApiModelProperty(value="Data de nascimento do cliente")
	private LocalDate dataNascimentoCliente;
	
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 8)
	@ApiModelProperty(value="CEP do endereço do cliente", required = true)
	private String cepEndereco; 
	
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 20)
	@ApiModelProperty(value="Número do endereço do cliente", required = true)
	private String numeroEndereco;
	
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 200)
	@ApiModelProperty(value="Complemento do endereço do cliente", required = true)
	private String complementoEndereco;

	public ClienteDTO_POST() {
		super();
	}

	public ClienteDTO_POST(String nomeCompletoCliente, String emailCliente, String cpfCliente, String telefoneCliente,
			LocalDate dataNascimentoCliente, String cepEndereco, String numeroEndereco, String complementoEndereco) {
		super();
		this.nomeCompletoCliente = nomeCompletoCliente;
		this.emailCliente = emailCliente;
		this.cpfCliente = cpfCliente;
		this.telefoneCliente = telefoneCliente;
		this.dataNascimentoCliente = dataNascimentoCliente;
		this.cepEndereco = cepEndereco;
		this.numeroEndereco = numeroEndereco;
		this.complementoEndereco = complementoEndereco;
		
	}


	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
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

	public String getCepEndereco() {
		return cepEndereco;
	}

	public void setCepEndereco(String cepEndereco) {
		this.cepEndereco = cepEndereco;
	}

	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	
}
