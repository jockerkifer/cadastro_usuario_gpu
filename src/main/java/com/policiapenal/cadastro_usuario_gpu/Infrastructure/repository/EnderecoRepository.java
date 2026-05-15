package com.policiapenal.cadastro_usuario_gpu.Infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
