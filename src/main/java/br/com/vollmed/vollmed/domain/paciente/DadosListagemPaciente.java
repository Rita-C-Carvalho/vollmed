package br.com.vollmed.vollmed.domain.paciente;

public record DadosListagemPaciente(Long id, String nome, String cpf, String email, String telefone) {

    public  DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone());
    }

}
