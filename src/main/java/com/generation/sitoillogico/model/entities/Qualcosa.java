package com.generation.sitoillogico.model.entities;

import com.generation.sitoillogico.model.entities.enums.Enumeratore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Qualcosa
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long a;
    private String b;
    @Enumerated(EnumType.STRING)
    private Enumeratore c;
    private LocalDate d;

    @OneToMany(mappedBy = "qualcosa",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Altro> altri;
}
