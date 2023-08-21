# REST API 게시판 시스템

## 설명

게시판, 게시글 및 댓글 관련 기능을 제공하는 REST API 서버입니다.

## 개발 환경

**개발 환경:**
- Spring Boot 3.1.2
- Java 17
- H2 데이터베이스
- IntelliJ IDEA
- Gradle

## 사전 요구 사항

### application.properties

```java
spring.datasource.url=jdbc:h2:tcp://localhost/~/mpboard
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.type=trace

```
## build.gradle 의존성 설정
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```
# 실행 방법

1. `DemoApplication.java` 파일을 실행합니다.
2. `src/main/java/com/example/demo` 폴더 아래에 `controller`, `entity`, `repository`, `service` 패키지를 생성합니다.
3. 컨트롤러, 엔티티, 리포지토리, 서비스 클래스를 해당 패키지에 생성합니다.

# 파일 구조

### 엔티티

- `@Entity`와 `@Data` 어노테이션을 사용하여 엔티티를 정의합니다.
- `board`, `post`, `comment` 클래스를 생성합니다.
- `@Id`와 `@GeneratedValue(strategy=GenerationType.IDENTITY)`를 사용하여 PK를 설정합니다.
- `@OneToMany`와 `@ManyToOne` 어노테이션으로 관계를 설정합니다.

### 컨트롤러

- `@RestController` 어노테이션을 사용하여 컨트롤러를 정의합니다.
- `@RequestMapping`으로 API 엔드포인트를 설정합니다.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` 어노테이션으로 CRUD 작업을 구현합니다.
- `@PathVariable`을 사용하여 경로 변수를 처리합니다.
- `@RequestBody`를 사용하여 요청 데이터를 처리합니다.

# 주요 기능 (CRUD)

## 게시판

- 게시판 생성/수정/삭제/상세 정보 확인이 가능합니다.

## 게시글

- 게시글 생성/수정/삭제/상세 정보 확인이 가능합니다.

## 댓글

- 댓글 생성/수정/삭제/게시글에 연관된 상세 정보 확인이 가능합니다.


