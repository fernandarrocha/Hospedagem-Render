package com.seuprojeto.pratica09.hospedagem.repository;

import com.seuprojeto.pratica09.hospedagem.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
