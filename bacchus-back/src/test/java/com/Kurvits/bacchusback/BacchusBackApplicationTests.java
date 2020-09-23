package com.Kurvits.bacchusback;


import com.Kurvits.bacchusback.User.User;
import com.Kurvits.bacchusback.User.UserController;
import com.Kurvits.bacchusback.User.UserRepository;
import com.Kurvits.bacchusback.User.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.UUID;

import static com.Kurvits.bacchusback.UserAssert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class BacchusBackApplicationTests {

	@Autowired
	private UserServiceImpl userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	void savedUserHasBidDate() throws IOException, ParseException {
		UUID uuid = UUID.randomUUID();
		User user = new User(uuid, "Test User", "Ball", 10);

		when(userService.saveUser(any(User.class))).then(returnsFirstArg());
		User savedUser = userService.saveUser(user);
		assertThat(savedUser).hasBidDate();
	}


}
