<p align="center">
  <a href="https://newjibs.store">
    <img src="./assets/title.png" width="400" height="95"  alt="logo">
  </a>
</p>

<br>

<p align="center">
  <a href="https://github.com/takealook97">
    <img src="https://github.com/takealook97.png/" width="150" alt="takealook97">
  </a>
  <a href="https://github.com/smarfy99">
    <img src="https://github.com/smarfy99.png/" width="150" alt="smarfy99">
  </a>
  <br>
  backend: 김건우 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; frontend: 정지원
</p>

<br>

## 🕊️ Deploy URL

- ✅ front-server : https://newjibs.store
- ✅ back-server : https://newjibs.shop
- ✅ back-swagger : https://newjibs.shop/swagger-ui/

<br>

## 🏠 Introduction

### main-service

- **부동산 모의 투자 서비스**
- 예산 100억을 기반으로 부동산에 투자하여 2년 뒤 최대 차익을 내는 것을 목표로 한다.
- 투자 이후에는 차익과 비례한 만큼의 포인트가 적립된다.

### sub-service

- 회원 서비스
- 랭킹 시스템
- 공지사항
- 최신 부동산 뉴스 기사 조회 서비스

<br>

## 🛠️ Skills

### language

- back : Java 8
- front : Html, CSS, JavaScript

### framework

- back : SpringBoot 2.7.16, JPA
- front : Vue.js, Vuetify

### database

- MariaDB 2.7.4
- Redis
- S3

### server

- AWS EC2
- Docker
- Cloudtype
- Netlify

<br>

## 🌐 Deploy Structure

<img src="./assets/server.png" width="700" alt="배포 구조 이미지">

<br>

## 🏗️ UML

<img src="./assets/uml.png" width="700" alt="uml">

<br>

## 💾 ERD

<img src="./assets/erd.png" width="700" alt="erd">

<br>

## ⚙️ function

### 🎯 회원

💡 회원 관리는 기본적으로 Spring Security와 JWT를 활용
- [x] 회원 가입
- [x] 로그인 / 로그아웃
- [x] 마이페이지
- [x] 프로필 사진 업로드 / 삭제
- [x] 회원 탈퇴
- [x] 랭킹 조회

### 🎯 부동산 (모의 투자)

💡 카카오 맵 API를 활용하여 부동산 좌표 및 정보 출력
- [x] 전체 좌표 정보 조회 (위경도, aptCode, 최소/최대 가격)
- [x] 특정 좌표 조회 (거래 번호를 비롯한 해당 부동산의 상세 정보)
- [x] 투자 결과 조회 (투자 후 수익 총량 출력 및 점수 반영)

### 🎯 공지사항

💡 조회를 제외한 기능들은 관리자 권한이 필요
- [x] 전체 공지사항 조회
- [x] 공지사항 작성
- [x] 특정 공지사항 조회
- [x] 특정 공지사항 수정
- [x] 특정 공지사항 삭제

### 🎯 뉴스

💡최신 네이버 부동산 뉴스 정보 제공
- [x] 뉴스 크롤링 (1일, 지난 6일 -> 1주일 사전 적재)
- [x] 뉴스 크롤링 스케쥴러 (랜덤한 매 5:30 ~ 6:30 마다 크롤링)
- [x] 전체 뉴스 기사 조회
- [x] 특정 뉴스 기사 조회