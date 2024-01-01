package br.com.vollmed.vollmed.domain.consulta;

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

    private String dataConsulta;

    private String horaConsulta;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    private boolean ativo = true;

    public Consulta(Consulta consulta) {
    }


    public Consulta(String dataConsulta, String horaConsulta, FormaDePagamento formaDePagamento,
                    Medico medico, Paciente paciente) {

        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.formaDePagamento = formaDePagamento;
        this.medico = medico;
        this.paciente = paciente;
    }


    public void atualizaInfomacoes(@Valid Medico medico, Paciente paciente, DadosAtualizacaoConsulta dadosConsulta) {
        if (medico != null) {
            this.medico = medico;
        }
        if (paciente != null) {
            this.paciente = paciente;
        }
        if (dataConsulta != null) {
            this.dataConsulta = dadosConsulta.dataConsulta();
        }
        if (horaConsulta != null) {
            this.horaConsulta = dadosConsulta.horaConsulta();
        }
        if (formaDePagamento != null) {
            this.formaDePagamento = dadosConsulta.formaDePagamento();
        }
    }

    public void excluir() {
        this.ativo = false;
    }

}
