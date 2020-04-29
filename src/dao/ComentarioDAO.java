package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectionFactory;
import model.Comentario;


public class ComentarioDAO {

	private Connection conect;

	public ComentarioDAO() {
		new ConnectionFactory();
		this.conect = ConnectionFactory.conectar();
	}
	

	public void cadastrar (Comentario comentario) {

		String insert = "INSERT INTO comentario (nome,texto, fk_noticia_id) " + "VALUES (?,?,?)";

		try (PreparedStatement preparedStatement = conect.prepareStatement(insert);) {

			preparedStatement.setString(1, comentario.getNome());
			preparedStatement.setString(2, comentario.getComentario());
			preparedStatement.setInt(3, comentario.getPk());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conect.rollback();
			} catch (SQLException ex) {
				System.out.println(ex.getStackTrace());
			}
		}
	}


	public List<Comentario> listaComentarios(Integer idNoticia) {

		String sqlSelect = "SELECT * FROM comentario WHERE fk_noticia_id = ?";

		List<Comentario> listaComentario = new ArrayList<>();

		try (PreparedStatement preparedStatement = conect.prepareStatement(sqlSelect);) {
			
			preparedStatement.setInt(1, idNoticia);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Comentario comentario= new Comentario();
				comentario.setNome(resultSet.getString("nome"));
				comentario.setComentario(resultSet.getString("texto"));
				listaComentario.add(comentario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaComentario;
	}
}