https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html
## 핵심 속성
- `debug`: 디버그 모드를 활성화합니다(default: false). command line argument의 `--debug`와 동일합니다.

### 로깅 관련 속성
- `logging.*`: 속성을 사용하여 Spring Boot 애플리케이션의 로깅 설정을 구성할 수 있습니다. 이 설정들은 애플리케이션의 로깅 레벨, 로그 파일의 위치, 로그 패턴 등을 정의할 수 있게 해줍니다. Spring Boot는 기본적으로 Apache Log4j2, Logback, JUL (Java Util Logging) 등 여러 로깅 시스템을 지원합니다. application.properties 또는 application.yml 파일을 통해 이러한 로깅 설정을 쉽게 변경할 수 있습니다.
#### 로그 레벨 설정
```
logging:
  level:
    root: WARN
    org:
      springframework:
        web: DEBUG
    com:
      mycompany: INFO
```
이 설정은 애플리케이션의 루트 로거에 대한 로깅 레벨을 WARN으로, Spring Framework의 웹 관련 로거에 대한 레벨을 DEBUG로, 특정 패키지(com.mycompany)에 대한 레벨을 INFO로 설정합니다.
#### 로그 파일 설정
```
logging:
  file:
    name: app.log
    path: /logs
```
이 설정은 로그 파일의 이름을 app.log로 지정하고, 로그 파일을 저장할 경로를 /logs 디렉토리로 설정합니다.
#### 로그 패턴 설정
```
logging:
  pattern:
    console: '%d{yyyy-MM-dd HH:mm:ss} - %msg%n'
    file: '%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n'
```
이 설정은 콘솔 로그의 출력 포맷과 파일 로그의 출력 포맷을 각각 설정합니다. 날짜, 시간, 스레드 이름, 로깅 레벨, 로거 이름, 메시지 등 다양한 요소를 포맷에 포함시킬 수 있습니다.
#### 로그 문자셋 설정
```
logging:
  charset:
    console: UTF-8
    file: UTF-8
```
이 설정은 콘솔 출력과 로그 파일 모두에 대해 UTF-8 인코딩을 사용하도록 지정합니다. 로그 메시지에 다국어 문자가 포함되어 있을 경우, 이 설정을 통해 문자가 올바르게 인코딩되고 표시되도록 할 수 있습니다.
#### 외부 로깅 설정 파일 지정
```
logging:
  config: classpath:logback-spring.xml
```
이 예시에서는 classpath에 위치한 logback-spring.xml 파일을 로깅 설정 파일로 지정합니다. Spring Boot는 실행 시 이 파일을 로드하여 로깅 구성을 적용합니다.

### Spring Boot Actuator 관련 속성
- `info.*`: 스프링 부트 애플리케이션의 정보를 구성하는 데 사용됩니다. 이 정보는 애플리케이션의 버전, 설명, 커스텀 정보 등을 포함할 수 있으며, 주로 애플리케이션의 메타데이터를 제공하는 데 사용됩니다. 스프링 부트 Actuator의 /info 엔드포인트를 통해 이 정보에 접근할 수 있습니다.
```
info:
  app:
    name: MyApplication
    description: This is an example Spring Boot application
    version: 1.0.0
  team:
    name: MyTeam
    contact: contact@example.com
```

