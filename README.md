# k8sTest

Spring Boot + Kotlin 기반의 간단한 게시판 서비스 예제 프로젝트입니다.  
Docker 및 Kubernetes 환경에서의 배포를 염두에 두고 설계되었습니다.

## 주요 특징
- **Spring Boot 3 + Kotlin** 기반 REST API 예제
- JPA(Hibernate) 기반 데이터 접근
- MySQL 연동
- Dockerfile 제공 (컨테이너 빌드 및 배포 용이)
- Kubernetes 배포 디렉토리(`k8s/`) 포함

## 프로젝트 구조

```
k8sTest/
├── board-service/         # (추가 서비스 디렉토리, 실제 구현 소스 위치 가능)
├── k8s/                   # Kubernetes 배포 관련 yaml 파일 위치
├── src/                   # 메인 소스 코드 (Spring Boot)
├── build.gradle           # Gradle 빌드 스크립트
├── Dockerfile             # Docker 컨테이너 빌드 파일
├── .gitignore
└── settings.gradle
```

## 빌드 및 실행

### 1. 로컬 빌드

```bash
./gradlew build
```

### 2. Docker 이미지 빌드

```bash
docker build -t board-service:latest .
```

### 3. Docker 컨테이너 실행

```bash
docker run -p 8080:8080 board-service:latest
```

### 4. Kubernetes 배포

`k8s/` 디렉토리 내의 yaml 파일을 참고하여 배포할 수 있습니다.

```bash
kubectl apply -f k8s/
```

## 주요 파일 설명

- `build.gradle` : 프로젝트 의존성 및 빌드 설정 (Kotlin, Spring Boot, JPA, MySQL 등)
- `Dockerfile` : 컨테이너 이미지 빌드용 설정 (8080 포트 노출, jar 실행)
- `k8s/` : Kubernetes 배포 관련 매니페스트 파일 위치
- `src/` : 실제 서비스 코드 (Controller, Service, Entity 등)

## 개발 환경

- Java 17 (Eclipse Temurin)
- Kotlin 1.9.x
- Spring Boot 3.x
- Gradle

## 참고

- 이 저장소는 Kubernetes 및 Docker 실습, CI/CD 파이프라인 테스트, 마이크로서비스 구조 연습 등에 활용할 수 있습니다.
