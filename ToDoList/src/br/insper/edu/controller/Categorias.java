package br.insper.edu.controller;

public class Categorias {
	private Integer id;
	private String nomeCategoria;
	private String postIt;
	private Integer usuarioId;
	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getPostIt() {
		return postIt;
	}
	public void setPostIt(String postIt) {
		this.postIt = postIt;
	}
}
