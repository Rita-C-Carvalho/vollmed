package br.com.vollmed.vollmed.domain.consulta.validacoes;

import br.com.vollmed.vollmed.domain.consulta.DadosCadastroConsulta;

public interface ValidadorDeAgendamentoDeConsulta {

    //NÃO PRECISA COLOCAR O PUBLIC ANTES DO VOID AQUI, POIS TODOS OS MÉTODOS "VALIDAR" FEITOS NAS OUTRAS CLASES QUE SERAO UTILIZADAS SÃO PUBLICOS
    void validar(DadosCadastroConsulta dadosConsulta);
}
