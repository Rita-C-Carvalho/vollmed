package br.com.vollmed.vollmed.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import br.com.vollmed.vollmed.domain.endereco.DadosEndereco;

//USANDO O RECORD O JAVA JÁ VAI CRIAR UMA CLASSE COM OS ATRIBUTOS ENTRE PARENTESES E CRIAR OS GETTERS E SETTERS POR BAIXO DOS PANOS.
public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
