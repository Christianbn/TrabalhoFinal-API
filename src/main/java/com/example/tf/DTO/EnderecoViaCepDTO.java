package com.example.tf.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class EnderecoViaCepDTO {
	
	@NotBlank
	@Size(max = 8)
	@ApiModelProperty(value="CEP do endereço do cliente", required = true)
	private String cep;
	
	@NotBlank
	@Size(max = 80)
	@ApiModelProperty(value="Logradouro do cliente", required = true)
	private String logradouro;
	
	@NotBlank
	@Size(max = 200)
	@ApiModelProperty(value="Complemento do endereço do cliente", required = true)
	private String complemento;
		
	@NotBlank
	@Size(max = 50)
	@ApiModelProperty(value="Bairro do cliente", required = true)
	private String bairro;
	
	@NotBlank
	@Size(max = 80)
	@ApiModelProperty(value="Cidade do cliente", required = true)
	private String localidade;
	
	@NotBlank
	@Size(max = 2)
	@ApiModelProperty(value="UF do cliente", required = true)
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