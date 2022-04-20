package ma.fst.tkhzn.sdsi.business;

import org.springframework.beans.factory.annotation.Autowired;

public class ForgotPasswordService {
//    @Autowired
//    UserService userService;
//    @Autowired
//    private JavaMailSender mailSender;
//
//
//    @RequestMapping(value="/forgotpass" ,method=RequestMethod.GET)
//    public String openEmailForm(Model model)
//    {
//        model.addAttribute("pageTitle", "Forgot Password");
//        return "forgotpass";
//    }
//
//
//    @PostMapping("/forgotpass")
//    public String forgotPasswordForm(HttpServletRequest request,Model model)
//    {
//        String email = request.getParameter("email");
//        String token = RandomString .make(45);
//
//        try {
//            userService.updateResetPassword(token, email);
//            String ressetPasswordLink =Utility.getSiteURL(request) + "/reset_password?token="+token;
//
//            sendEmail(email,ressetPasswordLink);
//            model.addAttribute("message", "Nous avons envoyé un lien pour récupérer votre mot de passe à votre boite mail . Veuillez le consulter !");
//
//        } catch (UserNotFoundException e) {
//            model.addAttribute("error", e.getMessage());
//        } catch (UnsupportedEncodingException | MessagingException exp) {
//            model.addAttribute("error","Error while sending email");
//        }
//        model.addAttribute("pageTitle", "Forgot Password");
//
//        return "forgotpass";
//    }
//
//    private void sendEmail(String email, String ressetPasswordLink) throws UnsupportedEncodingException, MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("contact@CHU.com","CHU Support");
//        helper.setTo(email);
//        String subject ="Récuperation du mot de passe";
//
//        String content="<p>Bonjour, </p>"
//                +"<p>Vous avez demandé de récuperer votre mot de passe  </p>"
//                +"<p>Voici le lien ci-dessous pour modifier votre mot de passe;</p>"
//                +"<p><a href=\""+ressetPasswordLink + "\">Modifier mot de passe</a></p>";
//        helper.setSubject(subject);
//        helper.setText(content,true);
//
//        mailSender.send(message);
//
//    }
//
//    @RequestMapping(value = "/reset_password",method = RequestMethod.GET)
//    public String showRessetPasswordForm(@Param(value="token")String token,
//                                         Model model) {
//        User user= userService.get(token);
//        if(user == null) {
//
//            model.addAttribute("title", "Reset your password !!");
//            model.addAttribute("message", "Invalid Token !!");
//            System.out.println("******if");
//            return "message";
//        }
//        model.addAttribute("token", token);
//        model.addAttribute("pageTitle", "Reset your password !!");
//        System.out.println("******else");
//        return "resset_passwd_form";
//    }
//
//    @PostMapping("/reset_password")
//    public String processResetPassword(HttpServletRequest request, Model model) {
//        String token = request.getParameter("token");
//        String password = request.getParameter("password");
//        System.out.println(token);
//        System.out.println(password);
//        User user = userService.get(token);
//        System.out.println(user.getName());
//
//
//        model.addAttribute("title", "Reset your password");
//
//        if (user == null)
//            model.addAttribute("message", "Invalid Token");
//
//        else {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            String encodedPassword = encoder.encode(password);
//            userService.updatePassword(user, encodedPassword);
//            System.out.println("You have successfully changed your password.");
//            model.addAttribute("message", "You have successfully changed your password.");
//
//        }
//        return "redirect:login4";
//
//
//    }
}
