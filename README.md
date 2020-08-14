# Khalk
> Khalk (pronounced like chalk) is a minimal Kotlin DSL for coloring console output

## Installation
[![Release](https://jitpack.io/v/de.moritzruth/khalk.svg)]

Replace `VERSION` with the version displayed above.

### Gradle Kotlin DSL
```kotlin
// Add repository
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("de.moritzruth:khalk:VERSION")
}
```

### Gradle Groovy DSL
```groovy
// Add repository
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'de.moritzruth:khalk:VERSION'
}
```

## Usage
This example should be pretty self-explanatory:

```kotlin
val khalk = Khalk()
println(khalk { green.underline.bold { "Hello world!" }})
```

### Styles
#### Modifiers
- `bold`
- `dim`
- `italic` - not supported very well
- `underline`
- `inverse` - Swaps the background color with the foreground color
- `strikethrough` - not supported very well

#### Foreground colors
- `black`
- `red`
- `green`
- `yellow`
- `blue`
- `magenta`
- `cyan`
- `white`
- `gray`
- `redBright`
- `greenBright`
- `yellowBright`
- `blueBright`
- `magentaBright`
- `cyanBright`
- `whiteBright`

#### Background colors
- `bgBlack`
- `bgRed`
- `bgGreen`
- `bgYellow`
- `bgBlue`
- `bgMagenta`
- `bgCyan`
- `bgWhite`
- `bgGray`
- `bgRedBright`
- `bgGreenBright`
- `bgYellowBright`
- `bgBlueBright`
- `bgMagentaBright`
- `bgCyanBright`
- `bgWhiteBright`

## License
Khalk is licensed under the [MIT License](/LICENSE).

Inspired by [chalk](https://github.com/chalk/chalk)
