package ezenweb.controller;

import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // @RequestBody 객체로 받겠다
    @PostMapping("/signup/post.do")
    public boolean doSignUp(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doSignUp(memberDto);
    }
}
