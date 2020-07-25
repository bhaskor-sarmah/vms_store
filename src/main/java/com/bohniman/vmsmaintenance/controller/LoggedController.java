package com.bohniman.vmsmaintenance.controller;

import java.util.Objects;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.model.User;
import com.bohniman.vmsmaintenance.payload.ChangePassword;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.service.UserService;
import com.bohniman.vmsmaintenance.utilities.LoggedInUser;
import com.bohniman.vmsmaintenance.utilities.PasswordUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = {"/store" , "/headmechanic" , "/mtoadmin", "/user", "/admin"})
public class LoggedController {
    
    @Autowired
    UserService userService;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    // ========================================================================
    // Change Password Page
    // ========================================================================
    @GetMapping("/change-password")
    public ModelAndView changePassword(ModelAndView mv) {
        mv = new ModelAndView();
        mv.setViewName("logged/change_password");
        return mv;
    }

    // ========================================================================
    // Change Password Put
    // ========================================================================
    @PutMapping("/put-change-password")
	public ResponseEntity<JsonResponse> postChangePassword(@Valid @ModelAttribute ChangePassword changePassword,
			BindingResult bindingResult) throws BindException {

		JsonResponse res = new JsonResponse();

		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}

		User user = userService.findUserByUsername(LoggedInUser.getLoggedInUsername()).get();

		if (!(bCryptPasswordEncoder.matches(changePassword.getOldPassword(), user.getPassword()))) {
			bindingResult.rejectValue("oldPassword", "error.oldPassword", "*Incorrect old password");
		}

		PasswordUtility passwordUtility = new PasswordUtility();
		String passwordStrength = passwordUtility.validateNewPass(changePassword.getNewPassword(),
				changePassword.getConfirmPassword());
		if (!(Objects.equals(passwordStrength, "SUCCESS"))) {
			bindingResult.rejectValue("passwordStrength", "error.passwordStrength", passwordStrength);
		}

		if (!bindingResult.hasErrors()) {
			String bCryptHashedPassword = bCryptPasswordEncoder.encode(changePassword.getNewPassword());
			user.setPassword(bCryptHashedPassword);
			// user.setPasswordChanged(true);
			userService.save(user);

			res.setMessage("Password Changed Successfully");
			res.setResult(true);
			return ResponseEntity.ok(res);
		} else {
			throw new BindException(bindingResult);
		}

	}
}