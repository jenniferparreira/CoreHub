package br.com.agibank.controller;

import br.com.agibank.beans.conta.Conta;
import br.com.agibank.beans.conta.TipoConta;
//import br.com.agibank.bo.conta.ContaBO;
import br.com.agibank.dao.ContaDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContaController {

    private ContaDAO contaDAO = new ContaDAO();
    //private final ContaBO contaBO = new ContaBO();
    private String resultado;

    public ContaController() throws SQLException {
    }

//    public String buscarStatusConta(int idConta){
//        try{
//            resultado = contaBO.buscarStatusConta(idConta);
//        }catch (SQLException e){
//            resultado = "Erro: " + e.getMessage();
//        }
//        return resultado;
//    }

//    public String exibirTitularConta(int idConta){
//        try{
//            resultado = contaBO.exibirTitularConta(idConta);
//        }catch (SQLException e){
//            resultado = "Erro: " + e.getMessage();
//        }
//        return resultado;
//    }

//    public String exibirTiposConta(int idConta) throws SQLException {
//
//        return contaBO.exibirTiposConta(idConta);
//
//    }


//    public int cadastrarConta(int idUsuario, int idTipo, double idClasse, int idAgencia, int numero, double saldo, String dataAbertura) {
//        contaBO.cadastrarConta(idUsuario, idTipo, idClasse, idAgencia, numero, saldo, dataAbertura);
//        return 1;
//    }

//    public int deletarConta(int idConta){
//        contaBO.deletarConta(idConta);
//        return 1;
//    }
    public Conta buscarContaPorId(int id_conta){
        try{
            return contaDAO.buscarConta(id_conta);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public int tratamento(int idUsuario) throws SQLException {
        contaDAO.listarContasUsuario(idUsuario);
        return 1;
    }

    public int listarContaUsuario(int idUsuario) throws SQLException {
        tratamento(idUsuario);
        return 1;
    }

    public void atualizarSaldo(int id_conta, double valor){
        try{
            contaDAO.atualizarSaldo(id_conta,valor);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }





}