package com.dio.votacao.mircroservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
@AllArgsConstructor
public class VotacaoModel {

	@Id
	private String id;
	private Date data;
	private ParticipanteModel participante;
}
