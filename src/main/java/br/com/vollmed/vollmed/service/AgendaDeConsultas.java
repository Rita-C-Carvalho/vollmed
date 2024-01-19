package br.com.vollmed.vollmed.service;


import br.com.vollmed.vollmed.domain.consulta.Consulta;
import br.com.vollmed.vollmed.domain.consulta.ConsultaRepository;
import br.com.vollmed.vollmed.domain.consulta.DadosAtualizacaoConsulta;
import br.com.vollmed.vollmed.domain.consulta.DadosCadastroConsulta;
import br.com.vollmed.vollmed.domain.consulta.validacoes.ValidadorDeAgendamentoDeConsulta;
import br.com.vollmed.vollmed.domain.medico.MedicoRepository;
import br.com.vollmed.vollmed.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorDeAgendamentoDeConsulta> validadores;

    //MÉTODO PARA CADASTRAR CONSULTAS
    public void cadastrarConsulta(DadosCadastroConsulta dadosConsulta){

            if(!medicoRepository.existsById(dadosConsulta.id_medico())){
                throw new RuntimeException("ID do médico informado não existe");
            }

            if(!pacienteRepository.existsById(dadosConsulta.id_paciente())){
                throw new RuntimeException("ID do paciente informado não existe");
            }

            //ESSA FUNCÃO VAI PERCORRER A LISTA DE VALIDAÇÃO E FAZER TODAS AS VALIDAÇÕES ANTES DE SEGUIR O FLUXO
            validadores.forEach(v -> v.validar(dadosConsulta));

            var medico = medicoRepository.getReferenceById(dadosConsulta.id_medico());
            var paciente = pacienteRepository.getReferenceById(dadosConsulta.id_paciente());
            var dataConsulta = dadosConsulta.dataConsulta();
            var formaDePagamento = dadosConsulta.formaDePagamento();
            var novaConsulta = new Consulta(dataConsulta, formaDePagamento, medico, paciente);
            consultaRepository.save(novaConsulta);
    }


    //MÉTODO PARA ATUALIZAR CONSULTAS
    public void atualizarConsulta(DadosAtualizacaoConsulta dadosConsulta){

        var medico = medicoRepository.getReferenceById(dadosConsulta.id_medico());
        var paciente = pacienteRepository.getReferenceById(dadosConsulta.id_paciente());
        var novaConsulta = consultaRepository.getReferenceById(dadosConsulta.id());


        //para fazer a atualização
        novaConsulta.atualizaInfomacoes(medico, paciente, dadosConsulta);
    }

}
