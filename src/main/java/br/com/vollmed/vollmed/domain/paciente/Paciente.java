package br.com.vollmed.vollmed.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import br.com.vollmed.vollmed.domain.endereco.Endereco;





@Embeddable
@Getter //anotação para gerar os getters
@Setter
@NoArgsConstructor //anotação para gerar o construtor sem argumentos
@AllArgsConstructor //anotação para gerar o construtor com todos os argumentos (campos)
@EqualsAndHashCode(of = "id")
@Table(name = "Pacientes")
@Entity(name = "Paciente")
public class Paciente {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Paciente(DadosCadastroPaciente dadosP) {
        this.nome = dadosP.nome();
        this.email = dadosP.email();
        this.telefone = dadosP.telefone();
        this.cpf = dadosP.cpf();
        this.endereco = new Endereco(dadosP.endereco());
        this.ativo = true;
    }


    public void atualizaInformacoes(@Valid DadosAtualizacaoPaciente dadosP) {

        if (dadosP.nome() != null) {
            this.nome = dadosP.nome();
        }
        if (dadosP.telefone() != null) {
            this.telefone = dadosP.telefone();
        }
        if (dadosP.cpf() != null) {
            this.cpf = dadosP.cpf();

            if (dadosP.email() != null) {
                this.email = dadosP.email();
            }
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
