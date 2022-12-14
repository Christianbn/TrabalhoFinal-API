package com.example.tf.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
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

import com.example.tf.DTO.ClienteDTO_POST;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", nullable = false)
	@ApiModelProperty(value = "Identificador único do cliente")
	private Long idCliente;

	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 50)
	@Column(name = "nome_completo_cliente", nullable = false, length = 50)
	@JsonProperty(access = Access.READ_ONLY)
	@ApiModelProperty(value = "Nome completo do cliente", required = true)
	private String nomeCompletoCliente;

	@Email
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 80)
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "email_cliente", nullable = false, length = 80, unique = true)
	@ApiModelProperty(value = "E-mail do cliente", required = true)
	private String emailCliente;

	@CPF
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 11)
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "cpf_cliente", nullable = false, length = 11, unique = true)
	@ApiModelProperty(value = "CPF do cliente", required = true)
	private String cpfCliente;

	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 40)
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "telefone_cliente", nullable = false, length = 40)
	@ApiModelProperty(value = "Telefone do cliente", required = true)
	private String telefoneCliente;

	@Column(name = "data_nascimento_cliente")
	@JsonProperty(access = Access.READ_ONLY)
	@ApiModelProperty(value = "Data de nascimento do cliente")
	private LocalDate dataNascimentoCliente;

	@NotNull(message = "Este campo não pode ser nulo.")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	@JsonProperty(access = Access.READ_ONLY)
	@ApiModelProperty(value = "Endereço do cliente", required = true)
	private Endereco endereco;

	public Cliente(Long idCliente, String nomeCompletoCliente, String emailCliente, String cpfCliente,
			String telefoneCliente, LocalDate dataNascimentoCliente, Endereco endereco) {
		super();
		this.idCliente = idCliente;
		this.nomeCompletoCliente = nomeCompletoCliente;
		this.emailCliente = emailCliente;
		this.cpfCliente = cpfCliente;
		this.telefoneCliente = telefoneCliente;
		this.dataNascimentoCliente = dataNascimentoCliente;
		this.endereco = endereco;
	}

	public Cliente(Long idCliente, Cliente cliente) {
		super();
		this.idCliente = idCliente;
		this.nomeCompletoCliente = cliente.getNomeCompletoCliente();
		this.emailCliente = cliente.getEmailCliente();
		this.cpfCliente = cliente.getCpfCliente();
		this.telefoneCliente = cliente.getCpfCliente();
		this.dataNascimentoCliente = cliente.getDataNascimentoCliente();
		this.endereco = cliente.getEndereco();

	}

	public Cliente(ClienteDTO_POST clienteDTO, Endereco endereco) {
		super();
		this.nomeCompletoCliente = clienteDTO.getNomeCompletoCliente();
		this.emailCliente = clienteDTO.getEmailCliente();
		this.cpfCliente = clienteDTO.getCpfCliente();
		this.telefoneCliente = clienteDTO.getTelefoneCliente();
		this.dataNascimentoCliente = clienteDTO.getDataNascimentoCliente();
		this.endereco = endereco;
	}

	public Cliente() {
		super();
	}

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
