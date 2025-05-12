package model;

import java.time.LocalDate;

public class MovimentaEstoque {

    public enum TipoMovimentacao {
        ENTRADA,
        SAIDA
    }

    private TipoMovimentacao tipoMovimentacao;
    private int quantidadeMovimentada;
    private LocalDate dataMovimentacao;
    private Operador operador;

    public MovimentaEstoque(TipoMovimentacao tipoMovimentacao, int quantidadeMovimentada, LocalDate dataMovimentacao, Operador operador) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidadeMovimentada = quantidadeMovimentada;
        this.dataMovimentacao = dataMovimentacao;
        this.operador = operador;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidadeMovimentada() {
        return quantidadeMovimentada;
    }

    public void setQuantidadeMovimentada(int quantidadeMovimentada) {
        this.quantidadeMovimentada = quantidadeMovimentada;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    @Override
    public String toString() {
        return "MovimentacaoEstoque{"
                + "Tipo de Movimentacao=" + tipoMovimentacao
                + ", Quantidade Movimentada=" + quantidadeMovimentada
                + ", Data de Movimentacao=" + dataMovimentacao
                + ", Operador=" + operador.toString()
                + '}';
    }
}