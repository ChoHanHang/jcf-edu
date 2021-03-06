package jcf.edu.user.controller;

import java.util.List;

import jcf.edu.user.model.UserVO;
import jcf.edu.user.service.UserService;
import jcf.sua.mvc.MciRequest;
import jcf.sua.mvc.MciResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/user/create")
	public void test(MciRequest request, MciResponse response) {
		response.setViewName("/user/userReg");
	}

	@RequestMapping("/user/findUsers")
	public void findUser(MciRequest request, MciResponse response) {
		List<UserVO> findUserList = userService.findUserList(null, null);
		response.setList("userList", findUserList);
		response.setViewName("/user/userList");
	}

	@RequestMapping("/user/insertUser")
	public void insertUser(MciRequest request, MciResponse response) {
		UserVO user = request.getParam(UserVO.class);
		userService.insertUser(user);
		response.setViewName("redirect:/user/findUsers");
	}

	@RequestMapping("/user/info/{userId}")
	public void info(MciRequest request, MciResponse response, @PathVariable String userId) {
		UserVO userVo = userService.info(userId);
		response.set("user", userVo);
		response.setViewName("/user/userDetail");
	}

	@RequestMapping("/user/updateUser")
	public void updateUser(MciRequest request, MciResponse response) {
		UserVO user = request.getParam(UserVO.class);
		userService.updateUser(user);
		response.setViewName("redirect:/user/findUsers");
	}

	@RequestMapping("/user/deleteUser")
	public void deleteUser(MciRequest request, MciResponse response) {
		UserVO user = request.getParam(UserVO.class);
		userService.deleteUser(user);
		response.setViewName("redirect:/user/findUsers");
	}

}
