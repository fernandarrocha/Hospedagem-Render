package com.seuprojeto.pratica09.hospedagem.dto;

import lombok.Data;

@Data
public class DocumentoDTO {
    private Long id;
    private String titulo;
    private String conteudo;
    private Long categoriaId;
}
