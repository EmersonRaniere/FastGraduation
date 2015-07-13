package br.edu.ifpb.pweb.tsi.model;

import java.util.ArrayList;
import java.util.Date;

public class Aluno {
	private int matricula;
	private String nomeAluno, Orientador, tituloProjeto;
	private Double notaAluno;
	private Date dataApresentacao;
	private ArrayList<Professor> banca;
}
