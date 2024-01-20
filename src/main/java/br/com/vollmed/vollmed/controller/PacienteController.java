package br.com.vollmed.vollmed.controller;

import br.com.vollmed.vollmed.domain.paciente.*;
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
//MÉTODO PARA CADASTRAR PACIENTES
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    //Anotação para fazer injeção de dependencias

    @Autowired
    private PacienteRepository repositoryP;


    //MÉTODO PARA CADASTRAR PACIENTES

    //UriComponentesBuilder É UMA CLASSE DO SPRINGBOOT QUE SERVER PARA CRIAR URI

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dadosP, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dadosP);
        repositoryP.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    //MÉTODO PARA LISTAR PACIENTES
    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page =  repositoryP.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }


    //MÉTODO PARA ATUALIZAR PACIENTES
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dadosP) {
        // para pegar as informações no banco de dados pela id
        var paciente = repositoryP.getReferenceById(dadosP.id());

        //para fazer a atualização
        paciente.atualizaInformacoes(dadosP);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    //MÉTODO PARA FAZER A EXCLUSÃO LÓGICA (DEIXAR O CAMPO INATIVO)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable long id) {
        var paciente = repositoryP.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }


}

