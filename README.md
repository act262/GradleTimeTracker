# Gradle task execute cost time

## USAGE
### 1. apply this plugin
config dependencies inside `build.gradle`
```
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath "io.micro.gradle:tracker-plugin:1.0.2-SNAPSHOT"
    }
}
```
then apply plugin
```
apply plugin: 'io.micro.gradle'
```

### 2. execute task and display each task execute time
```shell
gradle assemble
```
out result:
```
   6342ms All Task
     16ms  task ':compileJava'
   3338ms  task ':compileGroovy'
     23ms  task ':processResources'
      0ms  task ':classes'
     20ms  task ':jar'
   2438ms  task ':groovydoc'
     24ms  task ':publishPluginGroovyDocsJar'
     22ms  task ':publishPluginJar'
      4ms  task ':javadoc'
     17ms  task ':publishPluginJavaDocsJar'
      0ms  task ':assemble'
    418ms  task ':sample:compileJava'
      2ms  task ':sample:processResources'
      0ms  task ':sample:classes'
     20ms  task ':sample:jar'
      0ms  task ':sample:assemble'
```

custom configuration
```groovy
timeTracker {
    // Add time filter
    threshold = 1
    // Add task filter
    includeTask = ['compileGroovy']
}
```

## SAMPLE
@see sample


## DEVELOP
https://plugins.gradle.org/docs/submit

## TODO
- [ ] tracker configuration
- [x] time filter
- [x] task filter
