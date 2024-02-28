package com.filmstar.api.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@SpringBootTest
class UserEntityTests {

 private Validator validator;

 @BeforeEach
 void setUp() {
     ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
     validator = factory.getValidator();
 }

 @Test
 void testUserWithoutEmail() {
     User user = new User();
     user.setId(1L);
     user.setFirstname("John");
     user.setLastname("Doe");
     user.setPassword("password");

     Set<ConstraintViolation<User>> violations = validator.validate(user);
     assertThat(violations).hasSize(1);
     assertThat(violations.iterator().next().getMessage()).isEqualTo("El correo electrónico no puede ser nulo");
 }

 @Test
 void testUserWithoutPassword() {
     User user = new User();
     user.setId(1L);
     user.setFirstname("John");
     user.setLastname("Doe");
     user.setEmail("john.doe@example.com");

     Set<ConstraintViolation<User>> violations = validator.validate(user);
     assertThat(violations).hasSize(1);
     assertThat(violations.iterator().next().getMessage()).isEqualTo("La contraseña no puede ser nula");
 }

 @Test
 void testValidUser() {
     User user = new User();
     user.setId(1L);
     user.setFirstname("John");
     user.setLastname("Doe");
     user.setEmail("john.doe@example.com");
     user.setPassword("password");

     Set<ConstraintViolation<User>> violations = validator.validate(user);
     assertThat(violations).isEmpty();
 }

 @Test
 void testValidUserWithRoles() {
     User user = new User();
     user.setId(1L);
     user.setFirstname("John");
     user.setLastname("Doe");
     user.setEmail("john.doe@example.com");
     user.setPassword("password");
     user.setRoles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN));

     Set<ConstraintViolation<User>> violations = validator.validate(user);
     assertThat(violations).isEmpty();
 }
 
 @Test
 void testUserAuthorities() {
     User user = new User();
     user.setId(1L);
     user.setFirstname("John");
     user.setLastname("Doe");
     user.setEmail("john.doe@example.com");
     user.setPassword("password");
     user.setRoles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN));

     Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

     assertThat(authorities)
         .hasSize(2)
         .extracting(GrantedAuthority::getAuthority)
         .containsExactlyInAnyOrder(Role.ROLE_USER.name(), Role.ROLE_ADMIN.name());
 }


 @Test
 void testUserGettersAndSetters() {
     User user = new User();
     user.setId(1L);
     user.setFirstname("John");
     user.setLastname("Doe");
     user.setEmail("john.doe@example.com");
     user.setPassword("password");
     // Set roles if needed

     assertThat(user.getId()).isEqualTo(1L);
     assertThat(user.getFirstname()).isEqualTo("John");
     assertThat(user.getLastname()).isEqualTo("Doe");
     assertThat(user.getEmail()).isEqualTo("john.doe@example.com");
     assertThat(user.getPassword()).isEqualTo("password");
     // Assert roles if needed
 }

 @Test
 void testUserBuilder() {
     User user = User.builder()
         .id(1L)
         .firstname("John")
         .lastname("Doe")
         .email("john.doe@example.com")
         .password("password")
         .roles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN))
         .build();

     assertThat(user.getId()).isEqualTo(1L);
     assertThat(user.getFirstname()).isEqualTo("John");
     assertThat(user.getLastname()).isEqualTo("Doe");
     assertThat(user.getEmail()).isEqualTo("john.doe@example.com");
     assertThat(user.getPassword()).isEqualTo("password");
     assertThat(user.getRoles()).containsExactlyInAnyOrder(Role.ROLE_USER, Role.ROLE_ADMIN);
 }

}
