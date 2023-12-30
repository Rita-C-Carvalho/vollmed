package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.DadosCadastroMedico;
import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity(name = "Consulta")
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private boolean ativo = true;
    public Consulta(DadosCadastroMedico dados, Paciente paciente) {

        this.medico = new Medico(dados);
        this.paciente = paciente;
    }

    public Consulta(@Valid DadosCadastroConsulta consulta) {
    }

    public Consulta(Consulta consulta) {
    }

    public Consulta(DadosCadastroConsulta dadosConsulta, Medico medico, Paciente paciente) {
        this.medico = medico;
        this.paciente = paciente;
    }

    public void atualizaInfomacoes(@Valid DadosAtualizacaoConsulta dadosConsulta) {
        if (dadosConsulta.medico() != null) {
            this.medico = dadosConsulta.medico();
        }
        if (dadosConsulta.paciente() != null) {
            this.paciente = dadosConsulta.paciente();
        }
    }

    public void excluir() {
        this.ativo = false;
    }

}
