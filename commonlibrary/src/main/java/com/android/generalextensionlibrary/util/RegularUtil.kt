package com.android.generalextensionlibrary.util

import java.util.*
import java.util.regex.Pattern

/**
 * 验证手机号
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isMobile(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_MOBILE_EXACT, input)
}

/**
 * 验证电话号码
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isTelephone(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_TEL, input)
}


/**
 * 验证身份证号码1
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isIDCard(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_ID, input.toString().toUpperCase())
}


/**
 * 验证邮箱
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isEmail(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_EMAIL, input)
}

/**
 * 验证URL
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isURL(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_URL, input)
}

/**
 * 验证汉字
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isZh(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_ZH, input)
}

/**
 * 验证用户名
 *
 * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isUsername(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_USERNAME, input)
}

/**
 * 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isDate(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_DATE, input)
}

/**
 * 验证IP地址
 *
 * @param input 待验证文本
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isIP(input: CharSequence?): Boolean {
    return isMatch(RegularExpression.REGEX_IP, input)
}

/**
 * 判断是否匹配正则
 *
 * @param regex 正则表达式
 * @param input 要匹配的字符串
 * @return `true`: 匹配<br></br>`false`: 不匹配
 */
fun isMatch(regex: String?, input: CharSequence?): Boolean {
    return input != null && input.length > 0 && Pattern.matches(regex, input)
}

/**
 * 获取正则匹配的部分
 *
 * @param regex 正则表达式
 * @param input 要匹配的字符串
 * @return 正则匹配的部分
 */
fun getMatches(
    regex: String?,
    input: CharSequence?
): List<String>? {
    if (input == null) return null
    val matches: MutableList<String> =
        ArrayList()
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(input)
    while (matcher.find()) {
        matches.add(matcher.group())
    }
    return matches
}

/**
 * 获取正则匹配分组
 *
 * @param input 要分组的字符串
 * @param regex 正则表达式
 * @return 正则匹配分组
 */
fun getSplits(input: String?, regex: String?): Array<String?>? {
    return input?.split(regex!!)?.toTypedArray()
}

/**
 * 替换正则匹配的第一部分
 *
 * @param input       要替换的字符串
 * @param regex       正则表达式
 * @param replacement 代替者
 * @return 替换正则匹配的第一部分
 */
fun getReplaceFirst(
    input: String?,
    regex: String?,
    replacement: String?
): String? {
    return if (input == null) null else Pattern.compile(regex).matcher(input).replaceFirst(
        replacement
    )
}

/**
 * 替换所有正则匹配的部分
 *
 * @param input       要替换的字符串
 * @param regex       正则表达式
 * @param replacement 代替者
 * @return 替换所有正则匹配的部分
 */
fun getReplaceAll(
    input: String?,
    regex: String?,
    replacement: String?
): String? {
    return if (input == null) null else Pattern.compile(regex).matcher(input).replaceAll(replacement)
}