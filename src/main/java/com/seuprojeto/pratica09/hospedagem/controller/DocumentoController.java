package com.seuprojeto.pratica09.hospedagem.controller;

import com.seuprojeto.pratica09.hospedagem.dto.DocumentoDTO;
import com.seuprojeto.pratica09.hospedagem.model.Categoria;
import com.seuprojeto.pratica09.hospedagem.model.Documento;
import com.seuprojeto.pratica09.hospedagem.repository.CategoriaRepository;
import com.seuprojeto.pratica09.hospedagem.repository.DocumentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/documentos")
@Tag(name = "Documentos", description = "Operações de CRUD para documentos com relacionamento a categorias")
public class DocumentoController {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Operation(summary = "Lista todos os documentos")
    @GetMapping
    public List<DocumentoDTO> listar() {
        return documentoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Busca um documento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> buscarPorId(@PathVariable Long id) {
        return documentoRepository.findById(id)
                .map(documento -> ResponseEntity.ok(convertToDto(documento)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo documento")
    @PostMapping
    public ResponseEntity<DocumentoDTO> criar(@RequestBody DocumentoDTO documentoDTO) {
        return categoriaRepository.findById(documentoDTO.getCategoriaId())
                .map(categoria -> {
                    Documento documento = new Documento();
                    documento.setTitulo(documentoDTO.getTitulo());
                    documento.setConteudo(documentoDTO.getConteudo());
                    documento.setCategoria(categoria);
                    return ResponseEntity.ok(convertToDto(documentoRepository.save(documento)));
                })
                .orElse(ResponseEntity.badRequest().build()); // Categoria não encontrada
    }

    @Operation(summary = "Atualiza um documento existente")
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDTO> atualizar(@PathVariable Long id, @RequestBody DocumentoDTO documentoDTO) {
        return documentoRepository.findById(id)
                .flatMap(documento -> categoriaRepository.findById(documentoDTO.getCategoriaId())
                        .map(categoria -> {
                            documento.setTitulo(documentoDTO.getTitulo());
                            documento.setConteudo(documentoDTO.getConteudo());
                            documento.setCategoria(categoria);
                            return ResponseEntity.ok(convertToDto(documentoRepository.save(documento)));
                        }))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um documento por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (documentoRepository.existsById(id)) {
            documentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private DocumentoDTO convertToDto(Documento documento) {
        DocumentoDTO dto = new DocumentoDTO();
        dto.setId(documento.getId());
        dto.setTitulo(documento.getTitulo());
        dto.setConteudo(documento.getConteudo());
        dto.setCategoriaId(documento.getCategoria().getId());
        return dto;
    }
}
