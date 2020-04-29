package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectionFactory;
import model.Noticia;

public class NoticiaDAO {

	private Connection conect;

	public NoticiaDAO(){
		new ConnectionFactory();
		this.conect = ConnectionFactory.conectar();
	}

	
	public void cadastrar(Noticia noticia) {

		String insert = "INSERT INTO noticia (descricao,titulo,texto) " + "VALUES (?,?,?)";

		try (PreparedStatement preparedStatement = conect.prepareStatement(insert);) {

			preparedStatement.setString(1, noticia.getDescricao());
			preparedStatement.setString(2, noticia.getTitulo());
			preparedStatement.setString(3, noticia.getTexto());
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


	public List<Noticia> listaNoticias() {

		String sqlSelect = "SELECT * FROM noticia";

		List<Noticia> listanoticia = new ArrayList<>();

		try (PreparedStatement preparedStatement = conect.prepareStatement(sqlSelect);) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Noticia noticia = new Noticia();
				noticia.setTitulo(resultSet.getString("titulo"));
				noticia.setDescricao(resultSet.getString("descricao"));
				noticia.setTexto(resultSet.getString("texto"));
				noticia.setId(resultSet.getInt("id"));
				listanoticia.add(noticia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listanoticia;
	}

	
	public void alterar (int idnoticia, Noticia noticia) {

		String alterar = "UPDATE noticia " + "SET titulo = ?, descricao = ?, texto = ? " + " WHERE id = ? ";

		try (PreparedStatement preparedStatement = conect.prepareStatement(alterar)) {

			preparedStatement.setString(1, noticia.getTitulo());
			preparedStatement.setString(2, noticia.getDescricao());
			preparedStatement.setString(3, noticia.getTexto());
			preparedStatement.setInt(4, idnoticia);

			preparedStatement.execute();

		} catch (SQLException ex) {
			System.err.println("Não foi possivel modificar" + "a tabela Noticia.");
			ex.printStackTrace();
		}
	}


	public void excluir(int idnoticia) {

		String excluir = "DELETE FROM noticia " + " WHERE id = ? ";

		try (PreparedStatement pst = conect.prepareStatement(excluir)) {

			pst.setInt(1, idnoticia);
			pst.execute();

		} catch (SQLException ex) {
			System.err.println("Não foi possivel excluir " + "a noticia");
			ex.printStackTrace();
		}
	}

}