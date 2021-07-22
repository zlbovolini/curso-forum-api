package com.github.lbovolini.forum.repository;

import com.github.lbovolini.forum.model.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
        String email = "mail@mail.com\"";

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNome("Lucas");
        usuario.setSenha("password");

        em.persist(usuario);

        Optional<Usuario> usuarioOptional = repository.findByEmail(email);

        Assert.assertTrue(usuarioOptional.isPresent());
        Assert.assertEquals(email, usuarioOptional.get().getEmail());
    }

    @Test
    public void naoDeveriaCarregarUmCursoCujoNomeNaoEstejaCadastrado() {
        String email = "not@mail.com";

        Optional<Usuario> usuarioOptional = repository.findByEmail(email);

        Assert.assertTrue(usuarioOptional.isEmpty());
    }
}
