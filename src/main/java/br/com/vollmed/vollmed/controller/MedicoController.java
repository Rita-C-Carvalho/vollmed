package br.com.vollmed.vollmed.controller;

import br.com.vollmed.vollmed.domain.medico.*;
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
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;


    //MÉTODO PARA CADASTRAR MÉDICOS
    @PostMapping
    @Transactional
    //@RequestBody, É PARA DIZER QUE A INFORMAÇÃO ESTÁ VINDO DO CORPO DA REQUISIÇÃO.
    public ResponseEntity  cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    //MÉTODO PARA LISTAR MÉDICOS
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }


    //MÉTODO PARA ATUALIZAR MÉDICOS
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        // para pegar as informações no banco de dados pela id
        var medico = repository.getReferenceById(dados.id());

        //para fazer a atualização
        medico.atualizaInfomacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
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
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }


}
