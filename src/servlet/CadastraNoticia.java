package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import service.NoticiaService;

@WebServlet("/cadastraNoticiaController")
public class CadastraNoticia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final NoticiaService noticiaService = new NoticiaService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Noticia noticia = new Noticia();
		noticia.setDescricao(request.getParameter("descricao"));
		noticia.setTitulo(request.getParameter("titulo"));
		noticia.setTexto(request.getParameter("texto"));
		
		noticiaService.cadastrar(noticia);
	}

}
