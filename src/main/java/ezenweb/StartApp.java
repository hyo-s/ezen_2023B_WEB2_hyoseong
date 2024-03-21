package ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class);
    }
}


/*
    // JPA
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    // MY SQL
        runtimeOnly 'com.mysql:mysql-connector-j'

    ================================ 필수 연동 ================================
        application.properties 파일에서 연동
        # JDBC 연동
        # 1. 해당 JDBC 클래스 호출
        spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
        # 2. 해당 JDBC 클래스 이용한 접속할 DB 서버 경로 설정
        spring.datasource.url=jdbc:mysql://localhost:3306/springweb2
        # 3. DB서버의 아이디와 비밀번호
        spring.datasource.username=root
        spring.datasource.password=1234

        # 4. JPA DDL ( create, drop, alter ) DML ( select, insert, delete, update )
            # 1. 서버가 켜질 때 DDL 생성 여부
        spring.jpa.hibernate.ddl-auto=create
    ==========================================================================

    JSX
        JS의 확장문법
        JS + HTML/XML 결합
            JSP : JAVA + HTML 결합
            MUSTACHE : JAVA(Spring model) + HTML 결합
    장점
        코드가 간결해진다
        가독성이 향상된다

    JPA : Java Persistence API
        Persistence (영속성 : 프로그램이 종료되어도 사라지지 않는 데이터의 특성)
        서버와 데이터베이스 사이
            Entity : 자바 객체를 DB가 이해할 수 있게 만든 것으로 이를 기반으로 테이블이 만들어진다.
            Repository : 엔티티가 DB 속 테이블에 저장 및 관리(조작) 할 수 있도록 하는 인터페이스입니다.
                ( 인터페이스 : 서로 다른 객체들 간의 동일한 기능을 수행하게 조작하는 하나의 기능 묶음 )
    JPA Entity 생성 방법
        1. 클래스 파일을 생성한다.
        2. @Entity 설정
        3. PK 필드는 필수 1개 이상 @Id
    Repository 생성 방법
        1. 인터페이스 파일을 생성한다.
        2. extends JpaRepository 상속받기
            JpaRepository<조작할엔티티, PK 자료형>
    리포지토리 객체 이용한 엔티티 조작
        1. [C] .save(저장할엔티티)
        2. [R] .findAll()
        3. [U] .findById(호출할PK).get();
        4. [D] .delete(삭제할엔티티) 또는 .deleteByID(
*/