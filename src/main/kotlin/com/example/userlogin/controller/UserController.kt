package com.example.userlogin.controller

import com.example.userlogin.UserValidator
import com.example.userlogin.model.User
import com.example.userlogin.service.SecurityService
import com.example.userlogin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(private val userService: UserService,
                     private val securityService: SecurityService,
                     private val userValidator: UserValidator) {


    @GetMapping("/registration")
    fun registration(model: Model): String {
        model.addAttribute("userForm", User(null, null, null, null, null))
        return "registration"
    }

    @PostMapping("/registration")
    fun registration(@ModelAttribute("userForm") userForm: User, bindingResult: BindingResult): String {
        userValidator.validate(userForm, bindingResult)

        if (bindingResult.hasErrors()) {
            return "registration"
        }
        userService.saveUser(userForm)
        securityService.autoLogin(userForm.username!!, userForm.passwordConfirm!!)
        return "redirect:/welcome"
    }

    @GetMapping("/login")
    fun login(model: Model, error:String?, logout:String?):String{
        if(error != null){
            model.addAttribute("error","Your username and password is invalid!")
        }
        if(logout!= null){
            model.addAttribute("message","You have been logged out successfully.")
        }

        return "login"
    }

    @GetMapping(value = ["/","/welcome"])
    fun welcome(model: Model):String{
        return "welcome"
    }
}