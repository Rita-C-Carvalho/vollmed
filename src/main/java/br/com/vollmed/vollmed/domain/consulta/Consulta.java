package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.DadosCadastroMedico;
import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDateTime dataConsulta;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    private boolean ativo = true;

    public Consulta(Consulta consulta) {
    }


    public Consulta(LocalDateTime dataConsulta, FormaDePagamento formaDePagamento,
                    Medico medico, Paciente paciente) {

        this.dataConsulta = dataConsulta;
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
        if (formaDePagamento != null) {
            this.formaDePagamento = dadosConsulta.formaDePagamento();
        }
    }

    public void excluir() {
        this.ativo = false;
    }

}
