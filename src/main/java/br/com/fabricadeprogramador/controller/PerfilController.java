package br.com.fabricadeprogramador.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.fabricadeprogramador.entidade.Perfil;
import br.com.fabricadeprogramador.messages.MensagemUtil;
import br.com.fabricadeprogramador.service.PerfilService;
import br.com.fabricadeprogramador.service.ServiceException;


@Controller(value="perfilController")
public class PerfilController {


	@Autowired
	private PerfilService perfilService;
	
	private Perfil perfil =  new Perfil();
	private List<Perfil> perfilList ;
	
	public PerfilController() {
		
	}
	//Busca feita após o construtor já com a injeção de dependencia do perfilService
	@PostConstruct
	public void init(){
		setPerfilList(perfilService.buscarTodos());
	} 
	
	public void excluir(Perfil perfil){
		try {
			perfilService.excluir(perfil);
			perfilList.remove(perfil);
			MensagemUtil.mensagemAviso(MensagemUtil.EXCLUIR_SUCESSO);
		
		} catch (ServiceException e) {
			
			MensagemUtil.mensagemErro(MensagemUtil.EXCLUIR_FALHA);
			e.printStackTrace();
		}
	}
	
	public void editar(Perfil perfil){
		this.perfil=perfil;
	}
	
	public void salvar(){
		try {
			Perfil usuSalvo = perfilService.salvar(perfil);
			if(perfil.getId()==null)
			perfilList.add(usuSalvo);
			
			//Limpa o form
			perfil= new Perfil();
			MensagemUtil.mensagemAviso(MensagemUtil.SALVAR_SUCESSO);
		} catch (ServiceException e) {
			MensagemUtil.mensagemAviso(MensagemUtil.SALVAR_FALHA);
			e.printStackTrace();
		}
	}

	//Getters and Setters
	public Perfil getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<Perfil> perfilList) {
		this.perfilList = perfilList;
	}
}
