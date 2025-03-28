package br.com.agibank.controller;

import br.com.agibank.beans.transacoes.StatusTransacao;
import br.com.agibank.dao.transacoes.StatusTransacaoDAO;

import java.sql.SQLException;

public class StatusTransacaoController {

    StatusTransacaoDAO statusTransacaoDAO = new StatusTransacaoDAO();
    ContaController controller = new ContaController();
    TransacaoController transacaoController = new TransacaoController();

    public StatusTransacaoController() throws SQLException {

    }

    public int criarStatusTransacao(StatusTransacao statusTransacao){
        try{
            statusTransacaoDAO.criarStatusTransacao(statusTransacao);
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if(statusTransacao.getStatus() == "APROVADO"){
            try{
                controller.atualizarSaldo(statusTransacao.getIdTransacao(), transacaoController.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getValor());
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

        }

        return 0;
    }


}
