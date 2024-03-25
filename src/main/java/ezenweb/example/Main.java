package ezenweb.example;

public class Main {
    public static void main(String[] args) {


// ================================= 단방향 ================================= //
        // 1. 회원가입
        Member member1 = Member.builder()
                .mno(1)
                .id("qwe")
                .name("유재석")
                .build();

        // 2. '유재석' 게시물 작성
        Board board1 = Board.builder()
                .bno(1)
                .title("title")
                .content("content")
                .writer(member1)
                .build();

        // 3. 게시물 작성한 회원정보 호출 [게시물을 통해 작성자 알 수 있다.]
        System.out.println(board1.getWriter());

// ================================= 양방향 ================================= //
        member1.getBoardList().add(board1);
        System.out.println("member1.getBoard(); = " + member1.getBoardList());

        Board board2 = Board.builder()
                .bno(2)
                .title("title2")
                .content("content2")
                .writer(member1)
                .build();
        member1.getBoardList().add(board2);
        System.out.println(member1.getBoardList());

    }
}



/*
    관계형 데이터베이스 SQL
        자바 관계형


*/