package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

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
    private List<Medico> medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private boolean ativo;
    public Consulta(DadosCadastroConsulta dadosConsulta) {
        this.ativo = true;
        this.medico = dadosConsulta.id_medico();
        this.paciente = getPaciente();
    }

    public Consulta(Consulta consulta) {
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
