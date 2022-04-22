package com.dio.votacao.mircroservice.service;

import java.util.Date;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.dio.votacao.mircroservice.model.ParticipanteModel;
import com.dio.votacao.mircroservice.model.VotacaoModel;
import com.dio.votacao.mircroservice.repository.VotacaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VotacaoService {
	
	 
	private final VotacaoRepository votacaoRepository;
	
	public VotacaoService(VotacaoRepository votacaoRepository) {
		this.votacaoRepository = votacaoRepository;
	}


	@KafkaListener(topics = "votacao", groupId = "GrupoMicroserviceVotacao")
	public void executar(ConsumerRecord<String, String> registro) {
		
		String participante = registro.value();
		
		ObjectMapper mapper = new ObjectMapper();
		ParticipanteModel participanteModel = null;
		
		try {
			participanteModel = mapper.readValue(participante, ParticipanteModel.class);
		} catch ( JsonProcessingException e) {
			e.printStackTrace();
			log.error("Erro ao converter String para class [{}]", participante, e);
			return;
		}
		
		
		
		
		VotacaoModel votacaoModel = new VotacaoModel(null, new Date(), participanteModel);
		
		votacaoRepository.save(votacaoModel);
		log.info("Voto computado com sucesso [nome={}], [id={}]",participanteModel.getNome(), votacaoModel.getId());
	}
}
