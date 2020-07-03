package com.bohniman.vmsmaintenance.payload;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {

	@NotBlank(message = "* Please provide your old password.")
	public String oldPassword;

	@NotBlank(message = "* Please provide your new password.")
	public String newPassword;

	@NotBlank(message = "* Please re-type your new password.")
	public String confirmPassword;

	public String passwordStrength;


	
}