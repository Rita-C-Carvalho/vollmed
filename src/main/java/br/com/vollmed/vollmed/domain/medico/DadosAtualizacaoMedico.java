package br.com.vollmed.vollmed.domain.medico;

import br.com.vollmed.vollmed.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(

        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {

}
