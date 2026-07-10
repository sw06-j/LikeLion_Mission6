# 멋쟁이 사자처럼 PBL - 멤버 & 과제 관리 시스템

> 작성자: 정시우

Spring Boot와 JPA를 사용하여 구현한 멤버 및 과제 CRUD REST API 프로젝트입니다.  
전역 예외 처리(@RestControllerAdvice)를 도입하여 일관된 에러 응답을 제공하고, 브라우저 기반 프론트엔드로 API를 직접 조작할 수 있습니다.

---

## 🛠 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| ORM | Spring Data JPA / Hibernate |
| Database | MySQL 8.x |
| Build | Gradle |
| Frontend | HTML, CSS, Vanilla JS |

---

## ⚙ 실행 방법

### 1. MySQL 데이터베이스 생성

```sql
CREATE DATABASE likelion_pbl;
```

### 2. `application.properties` 비밀번호 수정

```
src/main/resources/application.properties
```

```properties
spring.datasource.password=본인_비밀번호
```

### 3. 프로젝트 실행

```bash
./gradlew bootRun
```

### 4. 브라우저 접속

```
http://localhost:8080
```

---

## 📡 API 목록

### Member API

| HTTP 메서드 | URI | 설명 |
|-------------|-----|------|
| POST | /members/lions | 아기사자(Lion) 등록 |
| POST | /members/staffs | 운영진(Staff) 등록 |
| GET | /members | 전체 멤버 조회 |
| GET | /members?part=백엔드 | 파트별 멤버 필터링 |
| GET | /members/{id} | ID로 단일 멤버 조회 |
| PUT | /members/lions/{id} | Lion 정보 수정 |
| PUT | /members/staffs/{id} | Staff 정보 수정 |
| DELETE | /members/{id} | 멤버 삭제 |

### Assignment API

| HTTP 메서드 | URI | 설명 |
|-------------|-----|------|
| POST | /members/{memberId}/assignments | 과제 등록 |
| GET | /assignments | 전체 과제 조회 |
| GET | /members/{memberId}/assignments | 멤버별 과제 조회 |
| GET | /assignments/{id} | 단건 과제 조회 |
| GET | /assignments/search?keyword= | 과제 제목 검색 |
| PUT | /assignments/{id} | 과제 수정 |
| DELETE | /assignments/{id} | 과제 삭제 |

### 에러 응답 형식

모든 에러는 아래 형식으로 통일하여 반환됩니다.

```json
{
  "status": 404,
  "message": "해당 멤버를 찾을 수 없습니다. id: 999"
}
```

| 상황 | HTTP 상태 코드 |
|------|---------------|
| 멤버/과제 조회 실패 | 404 Not Found |
| 중복 이름 등록 | 409 Conflict |

---

## 📂 프로젝트 구조

```
src/main/java/com/example/pbl/
├── PblApplication.java                     # Spring Boot 진입점
│
├── member/                                 # 멤버 도메인
│   ├── controller/MemberController.java    # REST API 컨트롤러
│   ├── service/MemberService.java          # 비즈니스 로직 (예외 기반)
│   ├── repository/MemberRepository.java    # JpaRepository
│   ├── domain/
│   │   ├── Member.java                     # JPA 엔티티
│   │   ├── RoleType.java                   # 역할 Enum (LION/STAFF)
│   │   ├── Part.java                       # 파트 Enum
│   │   ├── Major.java                      # 전공 Enum
│   │   └── Position.java                   # 직책 Enum
│   └── dto/
│       ├── LionCreateRequest.java
│       ├── StaffCreateRequest.java
│       ├── LionUpdateRequest.java
│       ├── StaffUpdateRequest.java
│       └── MemberResponse.java
│
├── assignment/                             # 과제 도메인
│   ├── controller/AssignmentController.java
│   ├── service/AssignmentService.java
│   ├── repository/AssignmentRepository.java
│   ├── domain/Assignment.java              # JPA 엔티티
│   └── dto/
│       ├── AssignmentCreateRequest.java
│       ├── AssignmentUpdateRequest.java
│       └── AssignmentResponse.java
│
└── global/                                 # 전역 설정
    ├── exception/
    │   ├── GlobalExceptionHandler.java     # @RestControllerAdvice
    │   ├── MemberNotFoundException.java
    │   ├── AssignmentNotFoundException.java
    │   └── DuplicateMemberException.java
    └── dto/
        └── ErrorResponse.java

src/main/resources/
├── application.properties
└── static/                                 # 프론트엔드
    ├── index.html
    ├── css/style.css
    └── js/
        ├── member.js
        └── assignment.js
```
