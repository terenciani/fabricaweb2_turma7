package br.com.fabricadeprogramador.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.fabricadeprogramador.entidade.Usuario;
import br.com.fabricadeprogramador.messages.MensagemUtil;
import br.com.fabricadeprogramador.service.ServiceException;
import br.com.fabricadeprogramador.service.UsuarioService;

@Controller ("usuarioController")
public class UsuarioController {
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarioList;
	
	@Autowired
	private UsuarioService usuarioService;	
	
	@PostConstruct
	public void init() {
		usuarioList = usuarioService.buscarTodos();
	}

	public void salvar() {
		try {
			Usuario usuarioPersistido = usuarioService.salvar(usuario);
			if (usuario.getId() == null)
				usuarioList.add(usuarioPersistido);
			usuario = new Usuario();
			
			MensagemUtil.mensagemAviso(MensagemUtil.SALVAR_SUCESSO);
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro(MensagemUtil.SALVAR_FALHA);
			e.printStackTrace();
		}
	}
	
	public void excluir(Usuario usuario){
		try {
			usuarioService.excluir(usuario);
			usuarioList.remove(usuario);
			
			
			MensagemUtil.mensagemAviso(MensagemUtil.EXCLUIR_SUCESSO);
		} catch (ServiceException e) {

			MensagemUtil.mensagemErro(MensagemUtil.EXCLUIR_FALHA);
			e.printStackTrace();
		}
	}
	
	public void editar(Usuario usuario){
		this.usuario=usuario;
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
