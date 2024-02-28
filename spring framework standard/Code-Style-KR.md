## Introduction

이 문서는 Spring Framework의 소스 파일에 대한 코딩 표준을 정의합니다. 주로 Spring Framework 팀을 위한 것이지만, 기여자들이 참조용으로 사용할 수 있습니다.

이 문서의 구조는 [Google Java Style](https://google.github.io/styleguide/javaguide.html) 참조를 기반으로 하며, _작업 중인_ 문서입니다.

## Source File Basics

### 파일 인코딩: UTF-8

소스 파일은 `UTF-8`을 사용하여 인코딩되어야 합니다.

### 들여쓰기

- 들여쓰기는 _탭_ 을 사용합니다(공백 아님).
- Unix (LF), DOS (CRLF) 줄 끝이 아님
- 모든 후행 공백을 제거
  - Linux, Mac 등에서: `find . -type f -name "*.java" -exec perl -p -i -e "s/[ \t]$//g" {} \;`

## Source file structure

소스 파일은 다음으로 구성됩니다. 이 정확한 순서대로:

- 라이센스
- 패키지 선언
- 임포트 문
- 정확히 하나의 최상위 클래스

위의 각 섹션 사이에는 정확히 하나의 빈 줄이 있습니다.

### License

각 소스 파일은 파일 최상단에 다음 라이센스를 명시해야 합니다:

```java
/*
 * Copyright 2002-2023 원 저자 또는 저자.
 *
 * Apache License 버전 2.0("라이센스")에 따라 라이센스가 부여되었습니다.
 * 라이센스에 따라 이 파일을 사용할 수 없습니다.
 * 라이센스는 다음에서 얻을 수 있습니다.
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * 법률에 필요하거나 서면으로 합의되지 않는 한, 소프트웨어
 * 라이센스에 따라 배포되는 것은 "있는 그대로"의 기준으로 제공되며,
 * 명시적이든 묵시적이든 종류의 보증이나 조건 없이 제공됩니다.
 * 라이센스가 권한을 부여하고 제한하는 사항을 관리하는 라이센스를 참조하십시오.
 */
```

라이센스 헤더의 날짜 범위를 항상 확인하십시오. 예를 들어, 헤더가 여전히 다음과 같이 표시되어 있는 2022년에 파일을 수정한 경우:

```java
* Copyright 2002-2018 원 저자 또는 저자.
```

그런 다음 2023년에 맞게 다음과 같이 업데이트하십시오:

```java
* Copyright 2002-2023 the original author or authors.
```

### Import statements

임포트 문은 다음과 같이 구성됩니다:

- `java.*` import
- 빈 줄
- `javax.*` import
- `jakarta.*` import
- 빈 줄
- 다른 모든 import
- 빈 줄
- `org.springframework.*` import
- 빈 줄
- 다른 모든 정적 import

정적 import는 생산 코드에서 사용해서는 안 되지만, 테스트 코드에서는 특히 `import static org.assertj.core.api.Assertions.assertThat;`와 같은 것들에 대해 사용해야 합니다.

생산 코드에서는 일반적으로 정적 import가 금지되어 있지만, 다음과 같은 경우 정적 import가 허용됩니다.

- 상수(열거형 상수 포함): `java.nio.charset.StandardCharsets` 또는 `org.springframework.core.annotation.MergedAnnotations.SearchStrategy`의 상수와 같은
- 제3자 DSL의 정적 팩토리 메서드: JUnit 플랫폼 `Launcher` API와 함께 사용될 때 `org.junit.platform.engine.discovery.DiscoverySelectors`의 메서드와 같은

와일드카드 임포트(예: `import java.util.*` 또는 `import static org.assertj.core.api.Assertions.*`)는 테스트 코드에서도 금지됩니다.

### Java source file organization

소스 파일의 요소가 조직되는 방식은 다음과 같습니다:

1. 정적 필드
2. 일반 필드
3. 생성자
4. 생성자에서 호출하는 (비공개) 메서드
5. 정적 팩토리 메서드
6. JavaBean 속성(즉, getter 및 setter)
7. 인터페이스에서 온 메서드 구현
8. 인터페이스에서 온 메서드 구현에서 호출하는 비공개 또는 보호된 템플릿
9. 기타 메서드
10. `equals`, `hashCode`, `toString`

인터페이스에서 온 메서드 구현에서 호출하는 비공개 또는 보호된 메서드는 사용되는 메서드 바로 아래에 배치되어야 합니다. 즉, 3개의 인터페이스 메서드 구현에 각각 사용되는 3개의 비공개 메서드가 있다면, 메서드의 순서는 3개의 인터페이스 메서드 다음에 3개의 비공개 메서드가 아닌, 1개의 인터페이스와 1개의 비공개 메서드가 순서대로 포함되어야 합니다.

무엇보다도, 코드의 구성은 _자연스러워야_ 합니다.

## Formatting

### Braces

#### Block-like constructs: K&R style

중괄호는 대부분 _Kernighan과 Ritchie 스타일_ (a.k.a., "Egyptian brackets")을 따릅니다. 비어 있지 않은 블록과 블록과 같은 구조에 대해:

- 여는 중괄호 전에 줄 바꿈이 없지만 하나의 공백으로 시작합니다
- 여는 중괄호 후에 줄 바꿈
- 닫는 중괄호 전에 줄 바꿈
- 해당 중괄호가 문장을 종료하거나 메서드, 생성자, 또는 명명된 클래스의 본문인 경우 닫는 중괄호 후에 줄 바꿈
- else, catch 및 finally 문 전에 줄 바꿈

예시:

```java
return new MyClass() {
	@Override
	public void method() {
		if (condition()) {
			something();
		}
		else {
			try {
				alternative();
			}
			catch (ProblemException ex) {
				recover();
			}
		}
	}
};
```

### Line wrapping

90자는 우리가 목표로 하는 _선호하는_ 줄 길이입니다. 일부 경우에는 코드를 약간 리팩토링함으로써 선호하는 길이를 달성할 수 있습니다. 다른 경우에는 그렇지 않을 수도 있습니다.

90은 엄격한 제한이 아닙니다. 90-105 사이의 줄은 가독성을 돕고 줄 바꿈이 가독성을 떨어뜨리는 반대 효과가 있는 많은 경우에 완전히 수용 가능합니다. 이것은 판단의 문제이며 일관성을 추구하는 것도 중요합니다. 특정 상황이 코드의 다른 부분에서 어떻게 처리되는지 보면서 많은 것을 배울 수 있습니다.

105-120 사이의 줄은 허용되지만 권장되지 않으며 적어야 합니다.

어떤 줄도 120자를 초과해서는 안 됩니다.

위의 줄 바꿈 규칙에 대한 하나의 큰 예외는 Javadoc이며, 우리는 모든 종류의 맥락에서 -- 예를 들어, Github, 휴대폰 등에서 읽을 때 -- 최대 가독성을 위해 80자 정도에서 줄을 바꾸는 것을 목표로 합니다.

길이가 긴 표현을 줄 바꿈할 때, 90자는 우리가 줄 바꿈을 목표로 하는 길이입니다. 구분 기호를 다음 줄이 아닌 줄의 끝에 두십시오(쉼표로 구분된 인수 등). 예를 들어:

```java
if (thisLengthyMethodCall(param1, param2) && anotherCheck() &&
        yetAnotherCheck()) {

    // ....
}
```

### Blank Lines

다음 요소들 전에 두 개의 빈 줄을 추가합니다:

- `static {}` 블록
- 필드
- 생성자
- 내부 클래스

메서드 시그니처가 여러 줄인 경우, 즉, 후에 한 줄의 빈 줄을 추가합니다.

```java
@Override
protected Object invoke(FooBarOperationContext context,
        AnotherSuperLongName name) {

    // code here
}
```

내부 클래스의 경우, 내부 클래스가 이미 2줄로 분리되어 있기 때문에 일반적으로 필드와 생성자 주위에 추가 빈 줄이 추가되지 않습니다. 그러나 내부 클래스가 더 실질적인 경우에는 2개의 추가 라인이 가독성에 도움이 될 수 있습니다.

## Class declaration

클래스 선언의 `implements`, `extends` 부분을 가능한 한 클래스 자체와 같은 줄에 두려고 노력합니다.

클래스를 주문할 때 가장 중요한 클래스가 먼저 오도록 합니다.

## Naming

### Constant names

상수 이름은 `CONSTANT_CASE`를 사용합니다: 모든 대문자와 단어는 밑줄로 구분됩니다.

모든 상수는 `static final` 필드이지만, 모든 `static final` 필드가 상수인 것은 아닙니다. 따라서 필드가 **정말로** 상수인 경우에만 상수 케이스를 선택해야 합니다.

예시:

```java
// Constants
private static final Object NULL_HOLDER = new NullHolder();
public static final int DEFAULT_PORT = -1;

// Not constants
private static final ThreadLocal<Executor> executorHolder = new ThreadLocal<Executor>();
private static final Set<String> internalAnnotationAttributes = new HashSet<String>();
```

### Variable names

변수 이름으로 단일 문자를 사용하지 마십시오. 예를 들어, `Method m`보다는 `Method method`를 선호합니다.

## Programming Practices

### File history

- 파일은 하나의 저자가 작성한 것처럼 보여야 하며, 변경 사항의 역사처럼 보이지 않아야 합니다.
- 함께 속해 있는 것들을 인위적으로 펼치지 마십시오.

### Organization of setter methods

새로운 setter 메소드를 추가할 위치를 현명하게 선택하십시오; 단순히 리스트의 끝에 추가되어서는 안 됩니다. 어쩌면 setter는 다른 setter와 관련이 있거나 그룹과 관련이 있을 수 있습니다. 그 경우 관련 메소드 근처에 배치되어야 합니다.

- Setter 순서는 역사적 순서가 아니라 중요도의 순서를 반영해야 합니다.
- _필드_ 와 _setter_ 의 순서는 **일관성** 있어야 합니다.

### Ternary Operator

삼항 연산자를 괄호 안에 묶으십시오 -- 예를 들어, `return (foo != null ? foo : "default");`.

또한 _not null_ 조건이 먼저 오는지 확인하십시오.

### Null Checks

메서드 인수가 `null`이 아닌지 확인하기 위해 `org.springframework.util.Assert.notNull` 정적 메소드를 사용하십시오. 예외 메시지를 포맷할 때는 매개변수의 이름이 먼저 오고 첫 글자는 대문자로 시작하며, "_must not be null_"로 뒤따라야 합니다. 예를 들어

```java
public void handle(Event event) {
    Assert.notNull(event, "Event must not be null");
    //...
}
```

### Use of @Override

메서드가 상위 유형에서 선언된 메서드를 재정의하거나 구현하는 경우 항상 `@Override`를 추가하십시오.

### Use of @since

- 모든 새로운 클래스에는 해당 클래스가 도입된 프레임워크의 버전과 함께 `@since`를 추가해야 합니다.
- 기존 클래스의 _새로운_ **public** 및 **protected** 메서드에는 `@since`를 추가해야 합니다.

### Utility classes

정적 유틸리티 메서드의 집합만 있는 클래스는 `Utils` 접미사로 명명되어야 하며, `private` 기본 생성자를 가져야 하며, `abstract`이어야 합니다. 클래스를 `abstract`로 만들고 `private` _default_ 생성자를 제공하면 누구도 그것을 인스턴스화하는 것을 방지할 수 있습니다. 예를 들어:

```java
public abstract MyUtils {

    private MyUtils() {
        /* prevent instantiation */
    }

    // static utility methods
}
```

### Field and method references

클래스의 필드는 _항상_ `this`를 사용하여 참조해야 합니다. 그러나, 클래스의 메서드는 `this`를 사용하여 참조해서는 안 됩니다.

### Local variable type inference

변수 선언에 대한 `var`의 사용( _지역 변수 타입 추론_ )은 허용되지 않습니다. 대신, 구체적인 타입이나 인터페이스를 사용하여 변수를 선언하십시오(해당하는 경우).

## Javadoc

### Javadoc formatting

다음 템플릿은 메소드에 대한 일반적인 Javadoc 사용을 요약합니다.

```java
/**
 * Parse the specified {@link Element} and register the resulting
 * {@link BeanDefinition BeanDefinition(s)}.
 * <p>Implementations must return the primary {@link BeanDefinition} that results
 * from the parsing if they will ever be used in a nested fashion (for example as
 * an inner tag in a {@code <property/>} tag). Implementations may return
 * {@code null} if they will <strong>not</strong> be used in a nested fashion.
 * @param element the element that is to be parsed into one or more {@link BeanDefinition BeanDefinitions}
 * @param parserContext the object encapsulating the current state of the parsing process;
 * provides access to a {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}
 * @return the primary {@link BeanDefinition}
 */
BeanDefinition parse(Element element, ParserContext parserContext);
```

특히 다음 사항에 유의하십시오:

- 첫 문장에는 명령형 스타일을 사용하십시오(즉, _Returns_ 가 아닌 _Return_).
- 설명과 매개변수 설명 사이에 빈 줄이 없어야 합니다.
- 설명이 여러 단락으로 정의된 경우, 각각을 `<p>`로 시작하십시오.
- 매개변수 설명이 줄 바꿈이 필요한 경우, 후속 줄을 들여쓰기하지 마십시오(`parserContext` 참조).

클래스의 Javadoc에는 아래 예시에 나타난 몇 가지 추가 규칙이 있습니다:

```java
/*
 * Interface used by the {@link DefaultBeanDefinitionDocumentReader} to handle custom,
 * top-level (directly under {@code <beans/>}) tags.
 *
 * <p>Implementations are free to turn the metadata in the custom tag into as many
 * {@link BeanDefinition BeanDefinitions} as required.
 *
 * <p>The parser locates a {@link BeanDefinitionParser} from the associated
 * {@link NamespaceHandler} for the namespace in which the custom tag resides.
 *
 * @author Rob Harrop
 * @since 2.0
 * @see NamespaceHandler
 * @see AbstractBeanDefinitionParser
 */
```

- 각 클래스는 클래스가 도입된 버전을 나타내는 `@since` 태그를 가져야 합니다.
- 클래스 수준 Javadoc의 태그 순서는 다음과 같습니다: `@author`, `@since`, `@param`, `@see`, `@deprecated`.
- 메서드 수준 Javadoc의 태그 순서는 다음과 같습니다: `@param`, `@return`, `@throws`, `@since`, `@see`, `@deprecated`.
- 메서드 수준 Javadoc과 달리, 클래스 설명의 단락은 빈 줄로 분리됩니다 _are_.

Javadoc 작성 시 적용해야 할 추가 일반 규칙은 다음과 같습니다:

- `{@code}`를 사용하여 `null`과 같은 코드 문장이나 값들을 래핑합니다.
- 타입이 `{@link}` 요소로만 참조되는 경우 불필요한 `import` 선언을 피하기 위해 전체 자격 이름을 사용합니다.

## Tests

### Testing Framework

테스트는 JUnit Jupiter(일명, JUnit 5)를 사용하여 작성해야 합니다.

위 규칙의 유일한 예외는 JUnit 4와 TestNG와의 Spring의 통합을 구체적으로 테스트하는 `spring-test` 모듈의 테스트 클래스입니다.

### Naming

각 테스트 클래스 이름은 `Tests` 접미사로 끝나야 합니다.

### Assertions

AssertJ를 사용하여 주장합니다.

### Mocking

BDD Mockito 지원을 사용합니다.
