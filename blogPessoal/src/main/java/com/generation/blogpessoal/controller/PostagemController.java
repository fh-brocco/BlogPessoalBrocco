package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	// tranfere a responsabilidade de contruir as consultas no banco de dados para o repository
		@Autowired
		private PostagemRepository repository;

		@GetMapping
		public List<Postagem> getAll() {
			return repository.findAll();
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Postagem> getById(@PathVariable Long id) {
			return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		}
			
		
		@GetMapping("/titulo/{titulo}")
		public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
			return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
		}


		@PostMapping
		public ResponseEntity<Postagem> postPostagem (@Valid @RequestBody Postagem postagem){
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
		}
		
		// Função de atualizar  
		/*@PutMapping
		public ResponseEntity <Postagem> put(@RequestBody Postagem postagem) {
			return ResponseEntity.ok(repository.save(postagem));
		}*/
		
		
		//função de atualizar com verificação do if
		@PutMapping
		public ResponseEntity <Postagem> put(@RequestBody Postagem postagem){
			return repository.findById(postagem.getId())
					.map(resposta -> ResponseEntity.ok().body(repository.save(postagem)))
					.orElse(ResponseEntity.notFound().build());
		}
		
		

		
		//verificação antes de deletar por id
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deletePostagem(@PathVariable Long id) {
			
			return repository.findById(id)
					.map(resposta -> {
						repository.deleteById(id);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.notFound().build());
		}
}
