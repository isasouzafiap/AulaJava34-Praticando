package br.com.fiap.bean;

public interface IDAO {
    String inserir(Object obj);
    String alterar(Object obj);
    String excluir(Object obj);
    String listarUm(Object obj);
}