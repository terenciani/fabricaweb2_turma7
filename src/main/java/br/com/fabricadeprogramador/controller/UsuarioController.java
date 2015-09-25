package br.com.fabricadeprogramador.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.fabricadeprogramador.entidade.Usuario;
import br.com.fabricadeprogramador.service.ServiceException;
import br.com.fabricadeprogramador.service.UsuarioService;

@Controller ("usuarioController")
public class UsuarioController {
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarioList = new ArrayList<Usuario>();
	
	@Autowired
	private UsuarioService usuarioService;

	
	
	@PostConstruct
	public void init() {
		usuarioList = usuarioService.buscarTodos();
	}

	public void salvar() {
		try {
			Usuario usuarioPersistido = usuarioService.salvar(usuario);
			usuarioList.add(usuarioPersistido);
			usuario = new Usuario();
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}
	
	

}
