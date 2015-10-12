package br.com.fabricadeprogramador.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtil {

	public static final String EXCLUIR_SUCESSO = "Excluído com secesso";
	public static final String EXCLUIR_FALHA = "Falha na exclusão";
	public static final String SALVAR_SUCESSO = "Salvo com sucesso";
	public static final String SALVAR_FALHA = "Falha ao salvar";

	public static void mensagemInfo (String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void mensagemErro (String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void mensagemAviso (String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
