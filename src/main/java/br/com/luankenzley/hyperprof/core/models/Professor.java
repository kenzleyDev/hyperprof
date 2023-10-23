package br.com.luankenzley.hyperprof.core.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professores")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Professor extends Auditable{

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    @ToString.Include
    private String email;

    @Column(name = "idade")
    private int idade;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor_hora")
    private BigDecimal valorHora;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "password")
    private String password;

}
