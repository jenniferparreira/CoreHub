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

    public int criarStatusTransacao(StatusTransacao statusTransacao,boolean transacaoExterna){
        try{
            statusTransacaoDAO.criarStatusTransacao(statusTransacao);
            if(statusTransacao.getStatus().equals("APROVADO")){
                controller.atualizarSaldo(transacaoController.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getIdContaOrigem(), -transacaoController.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getValor());
                if(transacaoExterna){
                    controller.atualizarSaldo(transacaoController.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getIdContaDestino(), transacaoController.buscarTransacaoPorId(statusTransacao.getIdTransacao()).getValor());
                }
            }
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }


}
