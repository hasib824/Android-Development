
# 🌀 Kotlin Flow `map` with Suspend Functions – Examples & Pitfalls

This repository demonstrates various valid and invalid ways to pass `suspend` functions to Kotlin Flow operators like `map`.

---

📂 **Code Reference**: [Anonymous functions.kt](https://github.com/hasib824/Android-Development/blob/main/Anonymous%20functions.kt)

---

## ✅ Valid Techniques

### 1. Inline suspend lambda (most common):
```kotlin
flowOf(1, 2, 3)
    .map { it * 2 }
    .collect { println(it) }
```
The lambda is automatically inferred as `suspend` because `map()` expects a `suspend` function.

---

### 2. Named suspend function reference:
```kotlin
suspend fun nyFunction(x: Int): Int = x * 2

flowOf(1, 2, 3)
    .map(::nyFunction)
    .collect { println(it) }
```

---

### 3. Assigning suspend lambda to a variable:
```kotlin
val suspendingFunction: suspend (Int) -> Int = { it * 2 }

flowOf(1, 2, 3)
    .map(suspendingFunction)
    .collect { println(it) }
```

---

### 4. Anonymous object implementing suspend functional type:
```kotlin
val transformer = object : suspend (Int) -> Int {
    override suspend fun invoke(x: Int): Int = x * 2
}

flowOf(1, 2, 3)
    .map(transformer)
    .collect { println(it) }
```

---

## ❌ Invalid Techniques

### Not valid Kotlin syntax:
```kotlin
flowOf(1, 2, 3)
    .map(suspend { x -> x * 2 }) // ❌ Compilation error
```

---

### Also invalid:
```kotlin
.map(suspend fun(x: Int): Int { return x * 2 }) // ❌ Not allowed
```

---

## 📚 Explanation

Kotlin doesn’t allow `suspend` as a modifier before anonymous lambdas like `suspend { ... }`. You must either:

- Let the compiler infer the `suspend` nature through context (`map { ... }`)
- Use a variable or function with an explicit `suspend` type

This repo is a handy reference for Kotlin developers working with `Flow` and suspend functions.



Passing Extension Functions as Parameters
-----------------------------------------

### Basic Function Parameter

To pass an extension function as a parameter, you need to define the parameter type correctly:

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   kotlin// Extension function  fun String.sayHello() {      println("Hello $this")  }  // Function that accepts extension function as parameter  fun executeStringFunction(stringFunc: String.() -> Unit) {      val name = "John"      name.stringFunc() // Call the extension function  }  // Usage  executeStringFunction(String::sayHello)  // or  executeStringFunction(::sayHello)   `

### Extension Function with Parameters

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   kotlin// Extension function with parameter  fun String.greetWith(greeting: String) {      println("$greeting $this")  }  // Function that accepts extension function with parameters  fun executeGreeting(greetFunc: String.(String) -> Unit) {      "Alice".greetFunc("Hi")      "Bob".greetFunc("Hello")  }  // Usage  executeGreeting(String::greetWith)   `

### Extension Function with Return Value

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   kotlin// Extension function that returns a value  fun String.addPrefix(prefix: String): String {      return "$prefix$this"  }  // Function that uses extension function with return value  fun processStrings(transformer: String.(String) -> String) {      val result1 = "World".transformer("Hello ")      val result2 = "Kotlin".transformer("Learn ")      println(result1) // Hello World      println(result2) // Learn Kotlin  }  // Usage  processStrings(String::addPrefix)   `

Function References with Extension Functions
--------------------------------------------

### Different Ways to Pass Extension Functions

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   kotlinfun String.myFunction() {      println("Processing: $this")  }  fun acceptExtensionFunction(func: String.() -> Unit) {      "Test".func()  }  // Method 1: Using class reference  acceptExtensionFunction(String::myFunction)  // Method 2: Using direct reference (if in same scope)  acceptExtensionFunction(::myFunction)  // Method 3: Using lambda  acceptExtensionFunction { myFunction() }  // Method 4: Using lambda with explicit call  acceptExtensionFunction { this.myFunction() }   `

Common Pitfalls and Solutions
-----------------------------

### Pitfall 1: Local Extension Functions and References

**Problem**: Extension functions defined inside other functions can't always be referenced with ::.

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   kotlin// ❌ This doesn't work  @Composable  fun MyComposable() {      @Composable      fun RowScope.localContent() {          // content here      }      // NavigationBar(content = ::localContent) // Error!  }   `

**Solution**: Move the function outside or use lambda:

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   kotlin// ✅ Solution 1: Move function outside  @Composable  fun RowScope.globalContent() {      // content here  }  @Composable  fun MyComposable() {      NavigationBar(content = ::globalContent) // Works!  }  // ✅ Solution 2: Use lambda  @Composable  fun MyComposable() {      @Composable      fun RowScope.localContent() {          // content here      }      NavigationBar { localContent() } // Works!  }   `

