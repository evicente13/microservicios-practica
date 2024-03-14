package net.javaguides.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
	
	private UserRepository userRepository;

	public UserDto createUser(UserDto userDto) 
	{
		User user = AutoUserMapper.MAPPER.mapToUser(userDto);
		User savedUser = userRepository.save(user);
		UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
		return savedUserDto;
	}

	public UserDto getUserById(Long userId) 
	{
		Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        UserDto userDto = AutoUserMapper.MAPPER.mapToUserDto(user);
		return userDto;
	}

	public List<UserDto> getAllUsers() 
	{
		List<User> users = userRepository.findAll();
		List<UserDto> usersDto = users.stream().map((user)-> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
		return usersDto;
	}

	public UserDto updateUser(UserDto user) 
	{
		User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
		existingUser.setUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());

        User updatedUser = userRepository.save(existingUser);
        UserDto userDto = AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
		return userDto;
	}

	public void deleteUser(Long userId) 
	{
		userRepository.deleteById(userId);
	}

}
