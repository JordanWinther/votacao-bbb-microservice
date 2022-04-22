package com.dio.votacao.mircroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dio.votacao.mircroservice.model.VotacaoModel;

public interface VotacaoRepository extends MongoRepository<VotacaoModel, String> {

}
