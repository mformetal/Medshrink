# detekt

## Metrics

* 7 number of properties

* 4 number of functions

* 3 number of classes

* 3 number of packages

* 6 number of kt files

## Complexity Report

* 172 lines of code (loc)

* 147 source lines of code (sloc)

* 63 logical lines of code (lloc)

* 1 comment lines of code (cloc)

* 7 cyclomatic complexity (mcc)

* 5 cognitive complexity

* 2 number of total code smells

* 0% comment source ratio

* 111 mcc per 1,000 lloc

* 31 code smells per 1,000 lloc

## Findings (2)

### empty-blocks, EmptyFunctionBlock (1)

Empty block of code detected. As they serve no purpose they should be removed.

[Documentation](https://detekt.dev/docs/rules/empty-blocks#emptyfunctionblock)

* /Users/milespeele/IdeaProjects/Diary/backend/src/main/kotlin/com/diary/Application.kt:23:70
```
This empty block of code can be removed.
```
```kotlin
20     }
21 }
22 
23 fun Application.main(httpClient: HttpClient = applicationHttpClient) {
!!                                                                      ^ error
24     
25 }
26 

```

### style, UnusedParameter (1)

Function parameter is unused and should be removed.

[Documentation](https://detekt.dev/docs/rules/style#unusedparameter)

* /Users/milespeele/IdeaProjects/Diary/backend/src/main/kotlin/com/diary/Application.kt:23:22
```
Function parameter `httpClient` is unused.
```
```kotlin
20     }
21 }
22 
23 fun Application.main(httpClient: HttpClient = applicationHttpClient) {
!!                      ^ error
24     
25 }
26 

```

generated with [detekt version 1.23.1](https://detekt.dev/) on 2023-11-05 02:06:55 UTC
