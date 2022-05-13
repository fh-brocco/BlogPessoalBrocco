package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id // Definir a coluna de id como chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Equivalente ao auto_increment no mysql
	private Long id;
    
	/* A anotação @NotBlank indica que o atributo não deve ser
	 * nulo e/ou conter espaços em branco.*/
	@NotBlank // Define que o campo não pode ter espaços vazios e em branco
	private String nome;

	@NotNull
	@Email(message = "O usuário deve ser um email valido ex:maria@email.com")
	private String usuario;

	@NotNull
	private String senha;

	private String foto;
	
	/**
	 * CascadeType.REMOVE -> Ele propaga a operação de remoção de um objeto Pai para um 
	 * objeto Filho. 
	 * Apenas quando remover a Entidade Usuario, também será removida todas as entidades 
	 * Postagens associadas. Nas demais operações não haverá a propagação.
	 * 
	 * CascadeType.ALL -> Ele propaga todas a operações (Inserir, Listar, Atualizar e Apagar)
	 * de um objeto Pai para um objeto Filho. 
	 */

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	// Metodos construtores para testes
		//importante seguir a mesma ordem das declarações dos atributos de usuario acima 
		//construtor cheio
		public Usuario(Long id, String nome, String usuario, String senha, String foto) {
			this.id = id;
			this.nome = nome;
			this.usuario = usuario;
			this.senha = senha;
			this.foto = foto;
		}

	//construtor vazio
	public Usuario() {}

		
	// Métodos - Getter e Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
}
