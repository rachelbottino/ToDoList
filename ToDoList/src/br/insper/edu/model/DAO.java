package br.insper.edu.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.insper.edu.controller.Usuarios;
import br.insper.edu.controller.Categorias;
import br.insper.edu.controller.Tarefas;

public class DAO {
	private Connection connection = null;
	public DAO() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/todolist", "root", "adgjlra1");
		
	} 
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	}
	
	public void adicionaUsuario(Usuarios usuario){
		String sql = "INSERT into Usuario"+"(nome,email,senha) values(?,?,?)";
		PreparedStatement stmt;
		try {
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.execute();
			stmt.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Usu�rio adicionado!");
		
	}
	
	public boolean valida_usuario(String email, String senha){
		boolean status = false;
		String sql = "SELECT * FROM Usuario WHERE email=? AND senha=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //System.out.println("Entrou em valida usuario!");
        
        try{
        	stmt = (PreparedStatement) connection.prepareStatement(sql);
        	stmt.setString(1, email);
        	stmt.setString(2, senha);
        	rs = stmt.executeQuery();
        	status=rs.next();
        	rs.close();
        	stmt.close();
        } catch (SQLException e){
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        
        return status;
	}
	
	public int getUsuarioId(String email){
		int usuarioId = 0;
		String sql = "select id from Usuario where email=?";
		PreparedStatement stmt;
		ResultSet rs;
		try{
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			rs.next();
			usuarioId = rs.getInt("id");
			rs.close();
			stmt.close();
		}catch (SQLException e){
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
		return usuarioId;
	}
	
	public String getColorFromNome(String nomeCategoria){
		
		String postit_color = null;
		
		String sql = "SELECT postit FROM Categoria WHERE nome_categoria=?";
		PreparedStatement stmt;
		ResultSet rs;
		System.out.println("ENTRO NO METODO GET COLOR FROM NOME");
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, nomeCategoria);
			rs = stmt.executeQuery();
			rs.next();
			postit_color = rs.getString("postit");
			System.out.println(rs.getString("postit"));
			
			stmt.execute();
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return postit_color;
	}
	
	public void adicionaTarefa(Tarefas tarefa){
		String sql = "INSERT into tarefa "+"(nome_tarefa,descricao_tarefa,categoria,concluida,usuario_id) values(?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			System.out.println("adiciona tarefa");
			//stmt.setInt(1, tarefa.getUsuarioId());
			stmt.setString(1,tarefa.getNomeTarefa());
			stmt.setString(2, tarefa.getDescricaoTarefa());
			stmt.setString(3, tarefa.getCategoria());
			stmt.setString(4, tarefa.getConcluida());
			stmt.setInt(5, tarefa.getUsuarioId());
			//stmt.setString(5, tarefa.getData());
			stmt.execute();
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void concluiTarefa(Tarefas tarefa){
		System.out.println("Entrou em concluiTarefa");
		String sql = "UPDATE Tarefa SET concluida =? WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getConcluida());
			stmt.setInt(2, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<Tarefas> listaTarefas(){
		List<Tarefas> tarefas = new ArrayList<Tarefas>();
		
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tarefa WHERE concluida='Nao'");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tarefas tarefa = new Tarefas();
				tarefa.setNomeTarefa(rs.getString("nome_tarefa"));
				tarefa.setDescricaoTarefa(rs.getString("descricao_tarefa"));
				tarefa.setCategoria(rs.getString("categoria"));
				tarefa.setId(rs.getInt("id"));
				
				tarefas.add(tarefa);
			}			
			rs.close();
			stmt.close();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tarefas;
	}
	
	public List<Tarefas> listaTarefasConcluidas(){
		List<Tarefas> tarefas = new ArrayList<Tarefas>();
		
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tarefa WHERE concluida='Sim'");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tarefas tarefa = new Tarefas();
				tarefa.setNomeTarefa(rs.getString("nome_tarefa"));
				tarefa.setDescricaoTarefa(rs.getString("descricao_tarefa"));
				tarefa.setCategoria(rs.getString("categoria"));
				tarefa.setId(rs.getInt("id"));
				
				tarefas.add(tarefa);
			}			
			rs.close();
			stmt.close();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tarefas;
	}
	
	public void remove(Tarefas tarefa){
		PreparedStatement stmt;
		System.out.println("Entrou em remove");
		try {
			stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM tarefa WHERE id=?");
			stmt.setInt(1, tarefa.getId());
			stmt.execute();
			stmt.close();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("REMOVEU A TAREFA");
	}
	
	public void adicionaCategoria(Categorias categoria){
		String sql = "INSERT into categoria "+"(nome_categoria,postit,usuario_id) values(?,?,?)";
		System.out.println("Entrou em adiciona categoria");
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			//System.out.println("adiciona categoria");
			stmt.setString(1, categoria.getNomeCategoria());
			stmt.setString(2,categoria.getPostIt());
			stmt.setInt(3, categoria.getUsuarioId());
			System.out.println("Categoria adicionada!");
			stmt.execute();
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<Categorias> listaCategorias(){
		List<Categorias> categorias = new ArrayList<Categorias>();
		
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM categoria");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Categorias categoria = new Categorias();
				categoria.setNomeCategoria(rs.getString("nome_categoria"));
				categoria.setPostIt(rs.getString("postit"));
				categorias.add(categoria);
				System.out.println(categoria.getNomeCategoria());
			}			
			rs.close();
			stmt.close();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorias;
	}
	
}
