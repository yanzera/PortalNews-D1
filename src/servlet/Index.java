package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Comentario;
import model.Noticia;
import service.ComentarioService;
import service.NoticiaService;


@WebServlet("/paginaPrincipalController.do")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final NoticiaService noticiaService = new NoticiaService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Noticia> listaNoticia = noticiaService.listar();
		
		for(Noticia noticia : listaNoticia) {
			
			response.getWriter().println("<h1>" + noticia.getTitulo() + "</h1>" 
										+"<h3>" + noticia.getDescricao() + "</h3>" 
										+"<p>" + noticia.getTexto() + "</p>");
			
			ComentarioService comentarioService = new ComentarioService();
			List<Comentario> listaComentario = comentarioService.listar(noticia.getId());
			HttpSession sessao= request.getSession();
			sessao.setAttribute("pk", noticia.getId());
			for(Comentario comentario : listaComentario) {
				
			response.getWriter().println("<hr/>"+"<h5> Nome: " + comentario.getNome() + "</h5>"
										+"<p> Comentário: " + comentario.getComentario() + "</p>"+"<hr/>");
				
			}
			
			response.getWriter().println("<form method=\"post\" action=\"CriarComentario.do\">"
										+"<br/>"
										+"<br/>"
										+ "<small> <p> Comentário: </p> </small>"
										+"<p><small> Nome: <small></p>"
										+"<input type=\"text\" value=\"\" name=\"nome\" />"
									    +"<br/>"
									    +"<br/>"
										+"<input type=\"text\" value=\"\" name=\"comentario\" />"
									    +"<input type=\"submit\" value=\"Comentar\" />"
										+"</form>"+"<hr/>");
		}
		
	}

}
