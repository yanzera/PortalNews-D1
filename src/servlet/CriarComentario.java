package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Comentario;
import service.ComentarioService;


@WebServlet("/CriarComentario.do")
public class CriarComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	private final ComentarioService comentarioService = new ComentarioService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao= request.getSession();
		Integer pk = (Integer) sessao.getAttribute("pk");
		
		Comentario comentario =  new Comentario();
		comentario.setNome(request.getParameter("nome"));
		comentario.setComentario(request.getParameter("comentario"));	
		comentario.setPk(pk);

		comentarioService.cadastrar(comentario);
	}

}
