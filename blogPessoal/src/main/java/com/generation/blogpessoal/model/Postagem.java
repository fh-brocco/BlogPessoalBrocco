package com.generation.blogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//transforma o objeto criado em uma tabela no banco de dados
@Entity

//dá um nome para a tabela no meu banco de dados
@Table(name = "tb_postagem")
public class Postagem {

	// define a coluna de id como chave primaria
	@Id

	// equivalente ao auto_increment no mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	// define que o campo é obrigatório
	@NotNull

	// define um numero minimo e maximo de caracteres no campo
	@Size(min = 5, max = 100, message = "O campo deve ter no minimo 5 caracteres, e no maximo 100 caracteres")
	public String titulo;

	@NotNull
	@Size(min = 10, max = 500)
	public String texto;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	/* Relacionamento com a classe Usuario
	 * Não esqueça de criar os métodos getters e setters para o atributo usuario.
	 */

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	/**
	 * 
	 * Os Métodos Get e Set obrigatoriamente devem ser criados para todos os atributos
     * da Classe, inclusive os novos atributos que forem adicionados no decorrer do
     * processo de Desenvolvimento.
	 * 
	 */	

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * 
	 * Os Métodos Get e Set obrigatoriamente devem ser criados para todos os atributos
     * da Classe, inclusive os novos atributos que forem adicionados no decorrer do
     * processo de Desenvolvimento.
	 * 
	 */	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
