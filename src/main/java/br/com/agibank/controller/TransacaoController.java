package br.com.agibank.controller;

import br.com.agibank.beans.transacoes.Transacao;
import br.com.agibank.dao.transacoes.TransacaoDAO;

import java.sql.SQLException;

public class TransacaoController {

    public TransacaoController() throws SQLException{

    }

    TransacaoDAO transacaoDAO = new TransacaoDAO();

    public Transacao buscarTransacaoPorId(int id) throws SQLException {
        return transacaoDAO.buscarTransacaoPorId(id);
    }
}
