package com.example.demo.Services;


import com.example.demo.myUtile.HandlerException.NotFoundException;
import com.example.demo.Models.Appuser;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;


@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;


    public Iterable<Appuser> getAllUsers()
    {
        return this.userRepository.findAll();
    }


    public Appuser save(Appuser u)
    {
        Appuser newUser = new Appuser(u.getId(),u.getName(),u.getUsername(),u.getPassword());
        return this.userRepository.save(newUser);
    }

    public Appuser getUserByUsername(String username)
    {
        Appuser user = userRepository.getByUsername(username);
        return user;
    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Appuser curruser = this.userRepository.getByUsername(username);
        if (curruser != null)
        {
            return new User(curruser.getUsername(),curruser.getPassword(),AuthorityUtils.NO_AUTHORITIES);
        }
        else
        {
           throw new NotFoundException(String.format("Username ( %s ) Not Found !",username));
        }
    }
}
