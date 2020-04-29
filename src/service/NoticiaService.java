package service;

import java.util.List;

import dao.NoticiaDAO;
import model.Noticia;

public class NoticiaService {
	
	private NoticiaDAO noticiaDAO =  new NoticiaDAO();

	public void cadastrar(Noticia noticia) {
		noticiaDAO.cadastrar(noticia);
	}
	

	public List<Noticia> listar(){
		
		return noticiaDAO.listaNoticias();
	}
	
	public void alterar(int idNoticia, Noticia noticia) {
		
		noticiaDAO.alterar(idNoticia, noticia);
	}
	

	public void excluir(int idNoticia) {
		
		noticiaDAO.excluir(idNoticia);
	}
}

