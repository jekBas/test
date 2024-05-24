package com.example.test;

import com.example.test.entity.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TestApplicationTests {
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Captor
	private ArgumentCaptor<Collection<User>> userCaptor;

	@Test
	public void testUpdateUserBalances() {
		Map<Integer, Integer> userBalances = new HashMap<>();
		userBalances.put(1, 100);
		userBalances.put(2, 200);

		List<User> existingUsers = Arrays.asList(
			new User(1L, "John", 0),
			new User(2L, "Jane", 0)
		);

		when(userRepository.findAllById(any())).thenReturn(existingUsers);
		when(userRepository.saveAll(userCaptor.capture())).thenReturn(existingUsers);

		userService.updateUserBalances(userBalances);

		verify(userRepository, times(1)).saveAll(userCaptor.capture());
		List<User> userList = userCaptor.getValue().stream().toList();
		assertEquals(2, userList.size());
		assertEquals(100, userList.get(0).getBalance());
		assertEquals(200, userList.get(1).getBalance());
	}
}
