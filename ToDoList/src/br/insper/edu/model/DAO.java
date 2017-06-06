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
		System.out.println("Conectado com banco de dados");
		
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
		String sql = "INSERT into usuario"+"(nome,email,senha) values(?,?,?)";
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
		System.out.println("Usuário adicionado!");
		
	}
	
	public boolean valida_usuario(String email, String senha){
		boolean status = false;
		String sql = "SELECT * FROM usuario WHERE email=? AND senha=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println("Entrou em valida usuario!");
        
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
		String sql = "select id from usuario where email=?";
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
	
	public void adicionaTarefa(Tarefas tarefa){
		String sql = "INSERT into tarefa "+"(usuario_id,nome_tarefa,descricao_tarefa) values(?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			System.out.println("adiciona tarefa");
			stmt.setInt(1, tarefa.getUsuarioId());
			stmt.setString(2,tarefa.getNomeTarefa());
			stmt.setString(3, tarefa.getDescricaoTarefa());
			//stmt.setString(4, tarefa.getData());
			stmt.execute();
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<Tarefas> listaTarefas(){
		List<Tarefas> tarefas = new ArrayList<Tarefas>();
		
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tarefa");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tarefas tarefa = new Tarefas();
				tarefa.setNomeTarefa(rs.getString("nome_tarefa"));
				tarefa.setDescricaoTarefa(rs.getString("descricao_tarefa"));
				
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
	}
	
	public void adicionaCategoria(Categorias categoria){
		String sql = "INSERT into categoria "+"(nome_categoria,postit) values(?,?)";
		System.out.println("Entrou em adiciona categoria");
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			System.out.println("adiciona categoria");
			stmt.setString(1, categoria.getNomeCategoria());
			stmt.setString(2,categoria.getPostIt());
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
				categoria.setPostIt(rs.getString("postit_color"));
				categorias.add(categoria);
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
