package br.com.easysoftware.sgi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "role")
@Data
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    public enum Values {
        ADMIN(1L),
        GERENTE(2L),
        USUARIO(3L);

        Long id;

        Values(Long id){
            this.id = id;
        }

        public Long getId(){
            return this.id;
        }

    }
}
