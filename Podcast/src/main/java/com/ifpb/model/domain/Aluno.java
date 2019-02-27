package com.ifpb.model.domain;

import com.ifpb.model.domain.Enum.NivelAcesso;
import com.ifpb.model.domain.Enum.Sexo;
import com.ifpb.model.domain.Enum.Tipo;

import java.io.File;
import java.time.LocalDate;

/**
 *
 * @author Mailson Dennis
 *
 */

public class Aluno extends Usuario{

    public Aluno() {
    }

    public Aluno(String nome, String email, String senha, LocalDate nascimento, Tipo tipo, NivelAcesso nivelAcesso, File foto, Sexo sexo, String telefone, Endereco endereco) {
        super(nome, email, senha, nascimento, tipo, nivelAcesso, foto, sexo, telefone, endereco);
    }
}
