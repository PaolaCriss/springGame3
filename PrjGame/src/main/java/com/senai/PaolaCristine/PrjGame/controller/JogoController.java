package com.senai.PaolaCristine.PrjGame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.PaolaCristine.PrjGame.entities.Jogo;
import com.senai.PaolaCristine.PrjGame.servicies.JogoServicies;

@RestController
@RequestMapping("/jogos")
public class JogoController {

	private final JogoServicies jogoServices;

	@Autowired
	public JogoController(JogoServicies jogoServices) {
		this.jogoServices = jogoServices;
	}

	@PostMapping
	public Jogo createProduct(@RequestBody Jogo jogo) {
		return jogoServices.saveCliente(jogo);
	}

	//Utilizando o ResponseEntity e RequestEntity
		@GetMapping
		public ResponseEntity<List<Jogo>> getAllJogos(RequestEntity<Void> requestEntity) {
			String method = requestEntity.getMethod().name();
			String contentType = requestEntity.getHeaders().getContentType().toString();
			List<Jogo> jogos = jogoServices.getAllJogo();
			return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
					.body(jogos);
		}
		
		@PutMapping("/{id}")
		public Jogo updateJogo(@PathVariable Long id, @RequestBody Jogo jogo) {
		    return jogoServices.updateJogo(id, jogo);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogo> getJogo (@PathVariable Long id) { 
		Jogo jogo = jogoServices.getJogoById(id);
	if (jogo != null) {
	return ResponseEntity.ok (jogo);
	} else {
	return ResponseEntity.notFound ().build();
	  }
	}
	

	@GetMapping ("/home")
	public String paginaInicial () {
	 return "index"; // Nome do seu arquivo HTML (sem a extensão)
	}
	
	//@GetMapping
	//public List<Jogo> getAllJogo() {
	//	return jogoServices.getAllJogo();
	//}

	@DeleteMapping("/{id}")
	public void deleteJogo(@PathVariable Long id) {
		jogoServices.deleteJogo(id);
	}
}
