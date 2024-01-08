package br.com.vollmed.vollmed.controller;

import br.com.vollmed.vollmed.domain.medico.DadosCadastroMedico;
import br.com.vollmed.vollmed.domain.medico.DadosDetalhamentoMedico;
import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.usuario.DadosAutenticacao;
import br.com.vollmed.vollmed.domain.usuario.DadosCadastroUsuario;
import br.com.vollmed.vollmed.domain.usuario.Usuario;
import br.com.vollmed.vollmed.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //MÉTODO PARA CADASTRAR USUARIOS
    @PostMapping
    @Transactional
    //@RequestBody, É PARA DIZER QUE A INFORMAÇÃO ESTÁ VINDO DO CORPO DA REQUISIÇÃO.
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dadosCadastroUsuario);
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCadastroUsuario(usuario));
    }

}
