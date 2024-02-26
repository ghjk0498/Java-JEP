## 메모리 설정 관련 옵션
- `-Xmx<size>`: 최대 힙 메모리 크기를 설정합니다. 예: -Xmx512m은 최대 힙 메모리를 512MB로 설정합니다.
- `-Xms<size>`: 시작 힙 메모리 크기를 설정합니다. 예: -Xms256m은 시작 시 힙 메모리를 256MB로 설정합니다.
- `-Xss<size>`: 스레드 스택의 크기를 설정합니다. 예: -Xss1m은 스레드당 스택 크기를 1MB로 설정합니다.

## 성능 최적화 및 모니터링 관련 옵션
- `-server`: 서버 JVM을 사용합니다. 서버 사이드 애플리케이션에 최적화된 성능을 제공합니다.
- `-verbose:gc`: 가비지 컬렉션(GC) 발생 시 정보를 출력합니다. 메모리 관리를 모니터링하는 데 유용합니다.
- `-XX:+UseG1GC`: G1 가비지 컬렉터를 사용합니다. 대규모 힙에 적합한 최신 GC 알고리즘입니다.
- `-XX:+HeapDumpOnOutOfMemoryError: OutOfMemoryError` 발생 시 힙 덤프를 생성합니다. 메모리 누수 분석에 유용합니다.
- `-XX:HeapDumpPath=<경로>`: 힙 덤프 파일 저장 위치를 지정합니다.

## 디버깅 및 테스트 관련 옵션
- `-Xdebug`: 디버그 모드를 활성화합니다. JDK 5 이전에 사용되던 옵션입니다.
- `-Xrunjdwp`:<옵션>: JDWP(Java Debug Wire Protocol)를 사용해 디버거 연결을 설정합니다. 예: -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=1044는 디버거가 1044 포트에 연결할 수 있도록 합니다.
-ea 또는 -enableassertions: 애플리케이션과 시스템 클래스에서 어설션을 활성화합니다.
-da 또는 -disableassertions: 애플리케이션에서 어설션을 비활성화합니다.

## 기타 유용한 옵션
- `-D<이름>=<값>`: 시스템 프로퍼티를 설정합니다. 예: -Djava.library.path=/usr/lib은 네이티브 라이브러리 경로를 설정합니다.
- `-version`: 설치된 Java 버전 정보를 출력합니다.
- `-jar <jar파일명>`: JAR 파일을 실행합니다.
- `-cp <클래스 경로> 또는 -classpath <클래스 경로>`: Java 클래스와 라이브러리의 검색 경로를 설정합니다.