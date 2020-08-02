package de.moritzruth.khalk

import kotlin.reflect.KProperty

internal enum class KhalkStyleGroup {
    MODIFIER,
    FG_COLOR,
    BG_COLOR
}

internal enum class KhalkStyle(private val openCode: Int, private val closeCode: Int, val group: KhalkStyleGroup) {
    BOLD(1, 22, KhalkStyleGroup.MODIFIER),
    DIM(2, 22, KhalkStyleGroup.MODIFIER),
    ITALIC(3, 23, KhalkStyleGroup.MODIFIER),
    UNDERLINE(4, 24, KhalkStyleGroup.MODIFIER),
    INVERSE(7, 27, KhalkStyleGroup.MODIFIER),
    STRIKETHROUGH(9, 29, KhalkStyleGroup.MODIFIER),

    BLACK(30, 39, KhalkStyleGroup.FG_COLOR),
    RED(31, 39, KhalkStyleGroup.FG_COLOR),
    GREEN(32, 39, KhalkStyleGroup.FG_COLOR),
    YELLOW(33, 39, KhalkStyleGroup.FG_COLOR),
    BLUE(34, 39, KhalkStyleGroup.FG_COLOR),
    MAGENTA(35, 39, KhalkStyleGroup.FG_COLOR),
    CYAN(36, 39, KhalkStyleGroup.FG_COLOR),
    WHITE(37, 39, KhalkStyleGroup.FG_COLOR),

    GRAY(90, 39, KhalkStyleGroup.FG_COLOR),
    RED_BRIGHT(91, 39, KhalkStyleGroup.FG_COLOR),
    GREEN_BRIGHT(92, 39, KhalkStyleGroup.FG_COLOR),
    YELLOW_BRIGHT(93, 39, KhalkStyleGroup.FG_COLOR),
    BLUE_BRIGHT(94, 39, KhalkStyleGroup.FG_COLOR),
    MAGENTA_BRIGHT(95, 39, KhalkStyleGroup.FG_COLOR),
    CYAN_BRIGHT(96, 39, KhalkStyleGroup.FG_COLOR),
    WHITE_BRIGHT(97, 39, KhalkStyleGroup.FG_COLOR),

    BLACK_BG(40, 49, KhalkStyleGroup.BG_COLOR),
    RED_BG(41, 49, KhalkStyleGroup.BG_COLOR),
    GREEN_BG(42, 49, KhalkStyleGroup.BG_COLOR),
    YELLOW_BG(43, 49, KhalkStyleGroup.BG_COLOR),
    BLUE_BG(44, 49, KhalkStyleGroup.BG_COLOR),
    MAGENTA_BG(45, 49, KhalkStyleGroup.BG_COLOR),
    CYAN_BG(46, 49, KhalkStyleGroup.BG_COLOR),
    WHITE_BG(47, 49, KhalkStyleGroup.BG_COLOR),

    GRAY_BG(100, 49, KhalkStyleGroup.BG_COLOR),
    RED_BRIGHT_BG(101, 49, KhalkStyleGroup.BG_COLOR),
    GREEN_BRIGHT_BG(102, 49, KhalkStyleGroup.BG_COLOR),
    YELLOW_BRIGHT_BG(103, 49, KhalkStyleGroup.BG_COLOR),
    BLUE_BRIGHT_BG(104, 49, KhalkStyleGroup.BG_COLOR),
    MAGENTA_BRIGHT_BG(105, 49, KhalkStyleGroup.BG_COLOR),
    CYAN_BRIGHT_BG(106, 49, KhalkStyleGroup.BG_COLOR),
    WHITE_BRIGHT_BG(107, 49, KhalkStyleGroup.BG_COLOR);

    val openEscapeSequence by lazy { createEscapeSequence(openCode) }
    val closeEscapeSequence by lazy { createEscapeSequence(closeCode) }

    companion object {
        private fun createEscapeSequence(code: Int) = "\u001B[${code}m"
    }
}

private class KhalkContextAppenderDelegate(private val style: KhalkStyle) {
    private var value: KhalkContext? = null

    operator fun getValue(thisRef: KhalkContext, property: KProperty<*>): KhalkContext {
        if (value == null) value = KhalkContext(thisRef.parentStyles, thisRef.ownStyles.filter { it.group != style.group }.toSet() + style)
        return value!!
    }
}

open class KhalkContext internal constructor(internal val parentStyles: Set<KhalkStyle>, internal val ownStyles: Set<KhalkStyle>) {
    val bold by KhalkContextAppenderDelegate(KhalkStyle.BOLD)
    val dim by KhalkContextAppenderDelegate(KhalkStyle.DIM)
    val italic by KhalkContextAppenderDelegate(KhalkStyle.ITALIC)
    val underline by KhalkContextAppenderDelegate(KhalkStyle.UNDERLINE)
    val inverse by KhalkContextAppenderDelegate(KhalkStyle.INVERSE)
    val strikethrough by KhalkContextAppenderDelegate(KhalkStyle.STRIKETHROUGH)

    val black by KhalkContextAppenderDelegate(KhalkStyle.BLACK)
    val red by KhalkContextAppenderDelegate(KhalkStyle.RED)
    val green by KhalkContextAppenderDelegate(KhalkStyle.GREEN)
    val yellow by KhalkContextAppenderDelegate(KhalkStyle.YELLOW)
    val blue by KhalkContextAppenderDelegate(KhalkStyle.BLUE)
    val magenta by KhalkContextAppenderDelegate(KhalkStyle.MAGENTA)
    val cyan by KhalkContextAppenderDelegate(KhalkStyle.CYAN)
    val white by KhalkContextAppenderDelegate(KhalkStyle.WHITE)

    val gray by KhalkContextAppenderDelegate(KhalkStyle.GRAY)
    val redBright by KhalkContextAppenderDelegate(KhalkStyle.RED_BRIGHT)
    val greenBright by KhalkContextAppenderDelegate(KhalkStyle.GREEN_BRIGHT)
    val yellowBright by KhalkContextAppenderDelegate(KhalkStyle.YELLOW_BRIGHT)
    val blueBright by KhalkContextAppenderDelegate(KhalkStyle.BLUE_BRIGHT)
    val magentaBright by KhalkContextAppenderDelegate(KhalkStyle.MAGENTA_BRIGHT)
    val cyanBright by KhalkContextAppenderDelegate(KhalkStyle.CYAN_BRIGHT)
    val whiteBright by KhalkContextAppenderDelegate(KhalkStyle.WHITE_BRIGHT)

    val bgBlack by KhalkContextAppenderDelegate(KhalkStyle.BLACK_BG)
    val bgRed by KhalkContextAppenderDelegate(KhalkStyle.RED_BG)
    val bgGreen by KhalkContextAppenderDelegate(KhalkStyle.GREEN_BG)
    val bgYellow by KhalkContextAppenderDelegate(KhalkStyle.YELLOW_BG)
    val bgBlue by KhalkContextAppenderDelegate(KhalkStyle.BLUE_BG)
    val bgMagenta by KhalkContextAppenderDelegate(KhalkStyle.MAGENTA_BG)
    val bgCyan by KhalkContextAppenderDelegate(KhalkStyle.CYAN_BG)
    val bgWhite by KhalkContextAppenderDelegate(KhalkStyle.WHITE_BG)

    val bgGray by KhalkContextAppenderDelegate(KhalkStyle.GRAY_BG)
    val bgRedBright by KhalkContextAppenderDelegate(KhalkStyle.RED_BRIGHT_BG)
    val bgGreenBright by KhalkContextAppenderDelegate(KhalkStyle.GREEN_BRIGHT_BG)
    val bgYellowBright by KhalkContextAppenderDelegate(KhalkStyle.YELLOW_BRIGHT_BG)
    val bgBlueBright by KhalkContextAppenderDelegate(KhalkStyle.BLUE_BRIGHT_BG)
    val bgMagentaBright by KhalkContextAppenderDelegate(KhalkStyle.MAGENTA_BRIGHT_BG)
    val bgCyanBright by KhalkContextAppenderDelegate(KhalkStyle.CYAN_BRIGHT_BG)
    val bgWhiteBright by KhalkContextAppenderDelegate(KhalkStyle.WHITE_BRIGHT_BG)

    operator fun invoke(fn: KhalkContext.() -> String): String {
        val styles = ownStyles.subtract(parentStyles)
        val builder = StringBuilder()
        for (style in styles) builder.append(style.openEscapeSequence)

        builder.append(KhalkContext(ownStyles, emptySet()).fn())

        for (style in styles) builder.append(style.closeEscapeSequence)

        return builder.toString()
    }
}

class Khalk: KhalkContext(emptySet(), emptySet())
