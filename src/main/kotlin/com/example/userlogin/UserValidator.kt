package com.example.userlogin

import com.example.userlogin.model.User
import com.example.userlogin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component
class UserValidator:Validator {
    @Autowired
    private lateinit var userService: UserService

    override fun validate(target: Any, errors: Errors) {
        val user = target as User
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","Not Empty")
        if(user.username!!.length < 6 || user.username.length > 32){
            errors.rejectValue("username", "Size.userForm.username")
        }
        if (userService.findByUserName(user.username) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.password!!.length < 8 || user.password!!.length > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (user.passwordConfirm != user.password) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

    }

    override fun supports(clazz: Class<*>): Boolean {
        return User::class.java == clazz
    }
}