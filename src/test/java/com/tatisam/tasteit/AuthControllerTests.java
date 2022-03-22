package com.tatisam.tasteit;

import com.tatisam.tasteit.entities.auth.User;
import com.tatisam.tasteit.exceptions.ErrorDetails;
import com.tatisam.tasteit.payload.auth.LoginDTO;
import static org.assertj.core.api.Assertions.*;

import com.tatisam.tasteit.payload.auth.LoginResponseDTO;
import com.tatisam.tasteit.payload.auth.SignUpDTO;
import com.tatisam.tasteit.repositories.auth.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthControllerTests {
    public static final String API_1_AUTH_LOGIN = "/api/1/auth/login";
    public static final String API_1_AUTH_SIGNUP = "/api/1/auth/signup";
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    private void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
        assertThat(testRestTemplate).isNotNull();
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void signUpUser_whenUserIsValid_receiveCreated(){
        var signUpDTO = createValidSignUpDTO();
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void signUpUser_whenUserIsValid_userSavedToDatabase(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void signUpUser_whenUserIsValid_receiveSuccessMessage(){
        var signUpDTO = createValidSignUpDTO();
        var response = postSignUp(signUpDTO, String.class);
        var body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body).isEqualTo("User created successfully");
    }

    @Test
    public void signUpUser_whenUserIsValid_passwordIsHashedInDatabase(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        var users = userRepository.findAll();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(1);
        User userFromDb = users.get(0);
        assertThat(signUpDTO.getPassword()).isNotEqualTo(userFromDb.getPassword());
    }

    @Test
    public void signUpUser_whenUserIsValid_withUserNameThatExistsInDatabase_receiveForbidden(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        var newSignUpDTO = new SignUpDTO();
        newSignUpDTO.setUserName(signUpDTO.getUserName());
        newSignUpDTO.setEmail("email@mail.co");
        newSignUpDTO.setPassword("P4ssword");
        var response = postSignUp(newSignUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void signUpUser_whenUserIsValid_withEmailThatExistsInDatabase_receiveForbidden(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        var newSignUpDTO = new SignUpDTO();
        newSignUpDTO.setUserName("user");
        newSignUpDTO.setEmail(signUpDTO.getEmail());
        newSignUpDTO.setPassword("P4ssword");
        var response = postSignUp(newSignUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void signUpUserTwoTimes_whenUserIsValid_onlyOneSavedInDatabase(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        postSignUp(signUpDTO, String.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void signUpUser_whenUserHasNullUsername_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setUserName(null);
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasNullEmail_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setEmail(null);
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasNullPassword_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setPassword(null);
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasUserNameShorterThanRequired_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setUserName("a");
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasPasswordShorterThanRequired_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setPassword("a");
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasUserNameLongerThanRequired_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setUserName("a".repeat(25));
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasPasswordLongerThanRequired_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setPassword("a".repeat(25));
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasPasswordAllLowercase_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setPassword("lowercase");
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasPasswordAllUppercase_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setPassword("UPPERCASE");
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasPasswordAllDigits_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setPassword("123456");
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasEmailWithoutAt_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setEmail("invalid.com");
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserHasInvalidEmail_receiveBadRequest(){
        var signUpDTO = createValidSignUpDTO();
        signUpDTO.setEmail("i@");
        var response = postSignUp(signUpDTO, String.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpUser_whenUserIsInvalid_receiveHashMapWithValidationErrors(){
        SignUpDTO signUpDTO = new SignUpDTO();
        var response = postSignUp(signUpDTO, HashMap.class);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(3);
    }


    @Test
    public void loginUser_whenUserIsRegistered_receiveOk(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        var loginDTO = createValidLoginDto();
        var response = postLogin(loginDTO, LoginResponseDTO.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void loginUser_whenUserIsRegistered_receiveJWTForUser(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        var loginDTO = createValidLoginDto();
        var response = postLogin(loginDTO, LoginResponseDTO.class);
        var body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getJwt()).isNotNull();
    }

    @Test
    public void loginUser_whenUserHasNullUsernameOrEmail_receiveBadRequest(){
        var loginDTO = createValidLoginDto();
        loginDTO.setUserNameOrEmail(null);
        var response = postLogin(loginDTO, LoginResponseDTO.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void loginUser_whenUserHasNullPassword_receiveBadRequest(){
        var loginDTO = createValidLoginDto();
        loginDTO.setPassword(null);
        var response = postLogin(loginDTO, LoginResponseDTO.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void loginUser_whenUserIsRegistered_withWrongUserNameOrEmail_receiveUnAuthorizedError(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        var loginDTO = createValidLoginDto();
        loginDTO.setUserNameOrEmail("another");
        var response = postLogin(loginDTO, ErrorDetails.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.UNAUTHORIZED);

    }

    @Test
    public void loginUser_whenUserIsRegistered_withWrongPassword_receiveUnAuthorizedError(){
        var signUpDTO = createValidSignUpDTO();
        postSignUp(signUpDTO, String.class);
        var loginDTO = createValidLoginDto();
        loginDTO.setPassword("paSSword4");
        var response = postLogin(loginDTO, LoginResponseDTO.class);
        var status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.UNAUTHORIZED);
    }



    private SignUpDTO createValidSignUpDTO(){
        var dto = new SignUpDTO();
        dto.setUserName("UserName");
        dto.setEmail("email@gmail.com");
        dto.setPassword("P4ssword");
        return dto;
    }
    private LoginDTO createValidLoginDto(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserNameOrEmail("UserName");
        loginDTO.setPassword("P4ssword");
        return loginDTO;
    }
    private <T> ResponseEntity<T> postSignUp(Object request, Class<T> responseType){
        return testRestTemplate.postForEntity(API_1_AUTH_SIGNUP, request, responseType);
    }
    private <T> ResponseEntity<T> postLogin(Object request, Class<T> responseType){
        return testRestTemplate.postForEntity(API_1_AUTH_LOGIN, request, responseType);
    }
}
