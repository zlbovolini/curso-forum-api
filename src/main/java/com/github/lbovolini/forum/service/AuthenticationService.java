package com.github.lbovolini.forum.service;

import com.github.lbovolini.forum.model.Usuario;
import com.github.lbovolini.forum.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AuthenticationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> optionalUserDetails = usuarioRepository.findByEmail(email);

        if (optionalUserDetails.isEmpty()) {
            throw new UsernameNotFoundException("Dados inválidos");
        }

        return optionalUserDetails.get();
    }
}
