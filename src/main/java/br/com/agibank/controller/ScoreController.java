package br.com.agibank.controller;
import java.time.LocalDate;
import br.com.agibank.beans.conta.Conta;
import br.com.agibank.controller.conta.ContaController;
import br.com.agibank.dao.transacoes.TransacaoDAO;
import java.sql.SQLException;
import java.time.ZoneId;

public class ScoreController{
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();
    public ScoreController() throws SQLException {
    }

    ContaController contaController = new ContaController();

    public double retornarSomaValorMes(int id_conta){
        try {
            return transacaoDAO.SomarMovimentacaoPorMes(id_conta);
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
        return 0;
    }

    public double calcularScorePorValor(int id_conta){
        try{
             return transacaoDAO.SomarMovimentacaoPorMes(id_conta)*0.1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public double atualizarScore(int id_conta){
        LocalDate hoje = LocalDate.now();
        Conta conta = new Conta();
        LocalDate localDate = conta.getDataAbertura().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        double pontuacaoValor = 0;
        double pontuacaoTempo = 0;

        if(hoje.getDayOfMonth() == 1){
            try{
                conta = contaController.buscarContaPorId(id_conta);
                pontuacaoValor = transacaoDAO.atualizarScoreConta(calcularScorePorValor(id_conta),id_conta);
            }catch (SQLException e){
                System.out.printf(e.getMessage());
            }
        }

        int mesesDesdeCadastro = localDate.getDayOfMonth() - hoje.getDayOfMonth();

        if(mesesDesdeCadastro % 6 == 0 && hoje.getDayOfMonth() == localDate.getDayOfMonth()){
            pontuacaoTempo = 10;
        }

        return pontuacaoValor + pontuacaoTempo;
    }


}


