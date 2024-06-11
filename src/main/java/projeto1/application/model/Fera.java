package projeto1.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Fera {

    public Fera(Long id, String nomeCompleto, String usuario) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.usuario = usuario;
    }

    public Fera() {
    }

    @Pattern(regexp = "[^[0-9]*$]", message = "O nome não pode ter caracteres especiais ou números.")
    @JsonProperty("id")
    private Long id;

    @NotNull(message= "O nome é obrigatorio." )
    @NotBlank(message = "O nome deve ser informado.")
    @NotEmpty(message = "O nome não pode ser vazio.")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "O nome não pode ter caracteres especiais ou números.")
    @JsonProperty("nome_completo")
    private String nomeCompleto;


    @JsonProperty("usuario")
    private String usuario;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
