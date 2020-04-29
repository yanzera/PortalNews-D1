package service;

import java.util.List;

import dao.ComentarioDAO;
import model.Comentario;

public class ComentarioService {

private ComentarioDAO comentarioDAO;
	
	public ComentarioService() {
		this.comentarioDAO = new ComentarioDAO();
	}

	public List<Comentario> listar(int idNoticia){
		return comentarioDAO.listaComentarios(idNoticia);	
	}
	
	public void cadastrar(Comentario comentario) {
		comentarioDAO.cadastrar(comentario);
	}
}
