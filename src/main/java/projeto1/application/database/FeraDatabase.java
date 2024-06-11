package projeto1.application.database;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_feras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeraDatabase {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome_completo", nullable = false, length = 90)
    private String nomeCompleto;

    @Column(name = "usuario", nullable = false, length = 100)
    private String usuario;






    @Override
    public String toString() {
        return "{ id = " + id
                + ", Nome Completo = " + nomeCompleto
                + ", Usuario = " + usuario
                + "}";
    }
}
