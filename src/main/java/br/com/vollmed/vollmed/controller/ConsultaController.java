package br.com.vollmed.vollmed.controller;

import br.com.vollmed.vollmed.domain.consulta.*;
import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.medico.MedicoRepository;
import br.com.vollmed.vollmed.domain.paciente.Paciente;
import br.com.vollmed.vollmed.domain.paciente.PacienteRepository;
import br.com.vollmed.vollmed.service.AgendaDeConsultas;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    //CHAMANDO O SERVIÇO DE CADASTRAR CONSULTAS
    @PostMapping
    @Transactional
    //@RequestBody, É PARA DIZER QUE A INFORMAÇÃO ESTÁ VINDO DO CORPO DA REQUISIÇÃO.
    public ResponseEntity cadastrarConsulta(@RequestBody DadosCadastroConsulta dadosConsulta){
        agendaDeConsultas.cadastrarConsulta(dadosConsulta);
        return ResponseEntity.ok(dadosConsulta);
    }


    //MÉTODO PARA LISTAR CONSULTAS
    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listar(@PageableDefault(size = 50) Pageable paginacao){
        Page<DadosListagemConsulta> page;
        page = consultaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);
    }


    //CHAMANDO O SERVIÇO ATUALIZAR CONSULTAS
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid  DadosAtualizacaoConsulta dadosConsulta) {
        agendaDeConsultas.atualizarConsulta(dadosConsulta);
        return ResponseEntity.ok(dadosConsulta);
    }

    //MÉTODO PARA FAZER A EXCLUSÃO LÓGICA (DEIXAR O CAMPO INATIVO)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable long id) {
        var consulta = consultaRepository.getReferenceById(id);
        consulta.excluir();

        return ResponseEntity.noContent().build();
    }


}
