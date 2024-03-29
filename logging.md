# SLF4J, Logback, Log4J2 소개 및 Log4J2의 장점과 Spring Boot에서의 사용 및 log4j 보안 이슈

## SLF4J (Simple Logging Facade for Java)

SLF4J는 Java의 로깅을 위한 추상 레이어(facade)를 제공하는 라이브러리입니다. 다양한 로깅 프레임워크(예: java.util.logging, logback, log4j)와 호환될 수 있도록 설계되었습니다. SLF4J를 사용하면, 애플리케이션 내에서 사용하는 구체적인 로깅 구현체를 변경해야 할 때 코드 변경 없이 로깅 구현체만 교체하는 것으로 대응할 수 있습니다.

## Logback

Logback은 SLF4J의 창시자인 Ceki Gülcü가 개발한 로깅 프레임워크입니다. Log4J의 후속작으로, 더 나은 성능, 메모리 효율성 및 구성 옵션을 제공합니다. Logback은 자동 리로딩, 조건부 로깅 등의 기능을 지원하며, SLF4J와 함께 사용될 때 가장 효과적입니다.

## Log4J2

Log4J2는 Apache Software Foundation에서 개발한 로깅 프레임워크로, Log4J의 개선된 버전입니다. Log4J2는 멀티스레드 환경에서의 성능 향상, 로깅 레벨별로 로그를 분리하여 관리할 수 있는 기능, 비동기 로깅 지원 등 다양한 기능을 제공합니다.

### Log4J2의 장점:

1. **고성능 비동기 로깅**: Log4J2는 비동기 로깅을 지원하여 애플리케이션의 성능 저하를 최소화합니다.
2. **세밀한 로깅 설정**: 로깅 레벨, 패턴, 출력 형식 등을 다양하게 설정할 수 있습니다.
3. **다양한 로깅 대상 지원**: 파일, 콘솔, 데이터베이스 등 다양한 출력 대상을 지원합니다.
4. **멀티스레드 환경 최적화**: 멀티스레드 환경에서도 로깅 성능이 우수합니다.

![alt text](logback_vs_log4j2.png)

### Spring Boot에서 Log4J2 사용하기:

Spring Boot에서 Log4J2를 사용하려면, 기본 로깅 프레임워크를 Log4J2로 교체해야 합니다.

1. **의존성 추가**: `pom.xml` 혹은 `build.gradle` 파일에 Log4J2 의존성을 추가합니다.

   **Maven 예시:**

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-log4j2</artifactId>
   </dependency>
   ```

   **Gradle 예시:**

   ```groovy
   implementation 'org.springframework.boot:spring-boot-starter-log4j2'
   ```

2. **기존 로깅 의존성 제거**: `spring-boot-starter-logging` 의존성을 제거합니다. => log4j2 라이브러리 추가 시 spring boot가 자동으로 log4j2 사용

3. **Log4J2 설정 파일 추가**: `log4j2.xml`, `log4j2.json`, `log4j2.yaml` 중 하나의 설정 파일을 src/main/resources 디렉토리에 추가합니다.

4. **애플리케이션 재시작**: 변경사항을 적용하려면 애플리케이션을 재시작합니다.

Spring Boot에서 Log4J2를 사용하면, Log4J2의 고성능 및 세밀한 로깅 설정 기능을 활용하여 애플리케이션의 로깅 성능을 최적화하고 관리할 수 있습니다.

## Log4j 보안 이슈

Log4j 보안 이슈는 주로 "Log4Shell"이라고 불리는 취약점을 지칭합니다. 이 취약점은 2021년 12월에 발견된 Apache Log4j 라이브러리의 심각한 보안 취약점으로, CVE-2021-44228로 등록되었습니다.

### Log4Shell 취약점 개요:

- **영향을 받는 버전**: Apache Log4j 2.x 버전 중 2.0-beta9부터 2.14.1까지
- **취약점 유형**: 원격 코드 실행(Remote Code Execution, RCE)
- **위험도**: 매우 높음 (CVSS 점수 10.0)

### 취약점의 원인:

Log4Shell 취약점은 Log4j의 로깅 메시지에서 JNDI(Java Naming and Directory Interface) 조회를 허용함으로써 발생합니다. 공격자는 조작된 로깅 메시지를 이용하여 원격 서버로부터 악성 코드를 로드하고 실행할 수 있습니다. 특히, Log4j는 애플리케이션의 다양한 부분에서 로깅을 수행하기 때문에, 이 취약점을 통해 시스템에 대한 광범위한 접근이 가능해집니다.

### 취약점의 영향:

이 취약점은 전 세계 수백만 개의 시스템에 영향을 미쳤으며, 웹 서버, 클라우드 서비스, 엔터프라이즈 소프트웨어 등 다양한 환경에서 널리 사용되는 Log4j 라이브러리 때문에 영향 범위가 매우 넓었습니다. 공격자는 이 취약점을 이용해 기밀 정보를 탈취하거나, 랜섬웨어를 배포하고, 시스템을 완전히 제어할 수 있습니다.

### 대응 조치:

- **취약한 버전 업데이트**: Log4j 라이브러리를 2.15.0 이상으로 업데이트하여 취약점을 해결합니다. 추가적으로 발견된 취약점에 대해서는 더 높은 버전으로 업그레이드할 필요가 있을 수 있습니다.
- **설정 변경**: Log4j 설정에서 JNDI 기능을 비활성화하거나, 특정 패턴을 필터링하여 공격 벡터를 차단할 수 있습니다.
- **시스템 모니터링 및 로그 검사**: 시스템과 네트워크를 지속적으로 모니터링하고, 의심스러운 활동이나 로그를 검사하여 추가적인 침입이나 악성 행위를 탐지해야 합니다.

Log4Shell 취약점은 그 심각성과 영향력 때문에 많은 기업과 조직에서 긴급 대응을 요구했으며, 정보 보안 커뮤니티 내에서 중요한 주제가 되었습니다.
