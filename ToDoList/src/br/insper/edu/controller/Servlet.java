package br.insper.edu.controller;
import br.insper.edu.model.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/")
public class Servlet extends HttpServlet {
	public Servlet(){
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		DAO dao = new DAO();		
		Usuarios novo_usuario = new Usuarios();
		Tarefas nova_tarefa = new Tarefas();
		Categorias nova_categoria = new Categorias();
		List<Tarefas> tarefas = dao.listaTarefas();
		List<Tarefas> tarefasconcluidas = dao.listaTarefasConcluidas();
		HttpSession session = request.getSession();
		
		if(request.getParameter("cadastro")!=null){
			System.out.println("REDIRECT CADASTRO");
			request.getRequestDispatcher("Cadastro.jsp").forward(request, response);
		}
		
		else if(request.getParameter("cadastrar")!=null){
			System.out.println("TENTANDO CADASTRAR");
			novo_usuario.setNome(request.getParameter("nome"));
			novo_usuario.setEmail(request.getParameter("email"));
			novo_usuario.setSenha(request.getParameter("senha"));
			dao.adicionaUsuario(novo_usuario);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		
		else if(request.getParameter("entrar")!=null){
			System.out.println("LOGANDO");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			if(dao.valida_usuario(email, senha)){
				session.setAttribute("user", email);
				session.setMaxInactiveInterval(30*60);
				Cookie userEmail = new Cookie("email", email);
				userEmail.setMaxAge(30*60);
				response.addCookie(userEmail);
				request.getRequestDispatcher("Home.jsp").forward(request, response);
			}
			else{
				System.out.println("email ou senha incorretos");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
		
		//BOTOES MENU PRINCIPAL
		else if(request.getParameter("ir_todo")!=null){
			System.out.println("REDIRECT MINHAS TAREFAS");
			request.getRequestDispatcher("MinhasTarefas.jsp").forward(request, response);
		}

		else if(request.getParameter("ir_categorias")!=null){
			System.out.println("REDIRECT CRIAR CATEGORIA");
			request.getRequestDispatcher("CriarCategoria.jsp").forward(request, response);
		}
		
		else if(request.getParameter("ir_tarefas")!=null){
			System.out.println("REDIRECT CRIAR TAREFAS");
			request.getRequestDispatcher("CriarTarefa.jsp").forward(request, response);
		}
		
		// BOTAO CRIAR CATEGORIA	
		else if(request.getParameter("nova_categoria")!=null){
			System.out.println("CRIANDO NOVA CATEGORIA");
			nova_categoria.setNomeCategoria(request.getParameter("nome_categoria"));
			nova_categoria.setPostIt(request.getParameter("postit_color"));
			String email = (String) session.getAttribute("email");
			nova_categoria.setUsuarioId(dao.getUsuarioId(email));
			dao.adicionaCategoria(nova_categoria);
			request.getRequestDispatcher("Home.jsp").forward(request, response);
		}
		
		// BOTAO CRIAR TAREFA
		else if(request.getParameter("nova_tarefa")!=null){
			System.out.println("CRIANDO NOVA TAREFA");
			
			nova_tarefa.setNomeTarefa(request.getParameter("nome_tarefa"));
			nova_tarefa.setDescricaoTarefa(request.getParameter("descricao_tarefa"));
			nova_tarefa.setCategoria(request.getParameter("categoria"));
			nova_tarefa.setConcluida("Nao");
			
//			String email = (String) session.getAttribute("email");
//			nova_tarefa.setUsuarioId(dao.getUsuarioId(email));
			dao.adicionaTarefa(nova_tarefa);
			System.out.println("Tarefa adicionada");
			request.getRequestDispatcher("Home.jsp").forward(request, response);
		}
		
		else {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			//System.out.println("CAIU NO ELSE");
		}
		
		for (Tarefas tarefa : tarefas) {
			//BOTAO APAGAR TAREFA
			if(request.getParameter("apagar_"+tarefa.getId())!=null){
				System.out.println("Removendo tarefa");
				dao.remove(tarefa);
				request.getRequestDispatcher("Home.jsp").forward(request, response);
			}
			//BOTAO TAREFA FEITA
			else if(request.getParameter("feita_"+tarefa.getId())!=null){
				System.out.println(tarefa.getId());
				System.out.println("entrou concluir");
				tarefa.setConcluida("Sim");
				dao.concluiTarefa(tarefa);
				System.out.println(tarefa.getConcluida());				
				request.getRequestDispatcher("Home.jsp").forward(request, response);				
			}
			request.getRequestDispatcher("Home.jsp").forward(request, response);
		}
		
		for (Tarefas tarefa : tarefasconcluidas) {
			//BOTAO APAGAR TAREFA
			if(request.getParameter("apagar_"+tarefa.getId())!=null){
				System.out.println("Removendo tarefa");
				dao.remove(tarefa);
				request.getRequestDispatcher("Home.jsp").forward(request, response);
			}
		}
			
}
			
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
	}
}
