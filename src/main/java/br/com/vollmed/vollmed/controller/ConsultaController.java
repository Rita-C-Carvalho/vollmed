package br.com.vollmed.vollmed.controller;

import br.com.vollmed.vollmed.domain.consulta.*;
import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.medico.MedicoRepository;
import br.com.vollmed.vollmed.domain.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //MÉTODO PARA CADASTRAR CONSULTAS
    @PostMapping
    @Transactional
    //@RequestBody, É PARA DIZER QUE A INFORMAÇÃO ESTÁ VINDO DO CORPO DA REQUISIÇÃO.
    public ResponseEntity cadastrarConsulta(@RequestBody @Valid DadosCadastroConsulta dadosConsulta, UriComponentsBuilder uriBuilder){
        var medico = medicoRepository.getReferenceById(dadosConsulta.id_medico());
        var paciente = pacienteRepository.getReferenceById(dadosConsulta.id_paciente());
        var consulta = new Consulta(dadosConsulta, medico, paciente);
        consultaRepository.save(consulta);

        var uri = uriBuilder.path("consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(new Consulta(consulta));
    }

    //MÉTODO PARA LISTAR CONSULTAS
    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listar(@PageableDefault(size = 10) Pageable paginacao){
        var page = consultaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);
    }


    //MÉTODO PARA ATUALIZAR CONSULTAS
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoConsulta dadosConsulta) {
        // para pegar as informações no banco de dados pela id
        var consulta = consultaRepository.getReferenceById(dadosConsulta.id());

        //para fazer a atualização
        consulta.atualizaInfomacoes(dadosConsulta);
        return ResponseEntity.ok(new Consulta(consulta));
    }


	/*MÉTODO PARA DELETAR O CAMPO
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable long id) {
		repository.deleteById(id);
	}
	 */

    //MÉTODO PARA FAZER A EXCLUSÃO LÓGICA (DEIXAR O CAMPO INATIVO)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable long id) {
        var consulta = consultaRepository.getReferenceById(id);
        consulta.excluir();

        return ResponseEntity.noContent().build();
    }


}
