package com.seuprojeto.pratica09.hospedagem.controller;

import com.seuprojeto.pratica09.hospedagem.dto.CategoriaDTO;
import com.seuprojeto.pratica09.hospedagem.model.Categoria;
import com.seuprojeto.pratica09.hospedagem.repository.CategoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Operações de CRUD para categorias de documentos")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Operation(summary = "Lista todas as categorias")
    @GetMapping
    public List<CategoriaDTO> listar() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Busca uma categoria por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> ResponseEntity.ok(convertToDto(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma nova categoria")
    @PostMapping
    public CategoriaDTO criar(@RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        return convertToDto(categoriaRepository.save(categoria));
    }

    @Operation(summary = "Atualiza uma categoria existente")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNome(categoriaDTO.getNome());
                    return ResponseEntity.ok(convertToDto(categoriaRepository.save(categoria)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta uma categoria por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private CategoriaDTO convertToDto(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        return dto;
    }
}
