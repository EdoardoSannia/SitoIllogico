package com.generation.sitoillogico.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Altro
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long e;
    private String f;

    @ManyToOne
    @JoinColumn(name = "id_qualcosa")
    private Qualcosa qualcosa;
}
