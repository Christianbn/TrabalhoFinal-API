package com.example.tf.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class EnderecoViaCepDTO {

	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 8)
	@ApiModelProperty(value = "CEP do endereço do cliente", required = true)
	private String cep;

	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 80)
	@ApiModelProperty(value = "Logradouro do cliente", required = true)
	private String logradouro;

	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 200)
	@ApiModelProperty(value = "Complemento do endereço do cliente", required = true)
	private String complemento;

	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 50)
	@ApiModelProperty(value = "Bairro do cliente", required = true)
	private String bairro;

	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 80)
	@ApiModelProperty(value = "Cidade do cliente", required = true)
	private String localidade;

	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 2)
	@ApiModelProperty(value = "UF do cliente", required = true)
	private String uf;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}