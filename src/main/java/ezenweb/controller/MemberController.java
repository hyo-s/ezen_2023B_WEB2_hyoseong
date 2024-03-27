package ezenweb.controller;

import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
// @CrossOrigin("http://localhost:3000") // * 요청 근원지를 교차 허용
public class MemberController {

    @Autowired
    private MemberService memberService;

// ======================== 회원가입 ======================== //
    // @RequestBody 객체로 받겠다
    @PostMapping("/signup/post.do")
    public boolean doSignUpPost(@RequestBody MemberDto memberDto){
        return memberService.doSignUpPost(memberDto);
    }
// ======================== 로그인 ======================== //
    @PostMapping("/login/post.do")
    public boolean doLoginPost(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doLoginPost(memberDto);
    }
// ======================== 로그아웃 ======================== //
    @GetMapping("/login/get.do")
    public boolean doLogOutGet(){
        return memberService.doLogOutGet();
    }
// ======================== 현재 로그인 회원정보 호출 (세션호출) ======================== //
    @GetMapping("/login/info/get.do")
    public MemberDto doLoginInfo(){
        return memberService.doLoginInfo();
    }
}
