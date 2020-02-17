These libraries are intended to reduce the amount of code you have to write, freeing code reading and writing
time, so you can focus more on what you want to build for your users (even if you're the only one), or
have more time to have `fun`.

1. toast 用法
     toast("hello")
     
2. onClick 用法
 
    button.onClick {
           // todo
        }
        

3. 启动activity

   start+要启动的activity （比如 DemoActivity）
    
   >

4. SharePreferenceUtil 用法

     1). var spValue by SharePreferenceUtil ("key", "DefaultValue")

     2). set data spValue = "value"

     3). get data spValue



5. NetworkUtil 用法

    1). isNetworkAvailable(context)

    2). isConnected(context)

    3). getNetworkType(context)


6.  获取屏幕宽高以及Dp/Px转化用法

    1). dp2px(context) or px2dp

    2). screenWidth or screenHeight



7. RegularUtil 用法

    1). 身份证是否合法 isIDCard("no")

    2). 手机号是否合法 isMobile("no")

    3). isEmail是否合法 isEmail("xx.mail.com")

    4). 用户名校验 isUsername()

    5). 格式日期校验 isDate()

8. EncodeUtil 用法

    1). 普通字符串编码 encode(input,"")

    2). 普通字符串解码 decode(input,"")

    3). base64Encode(input: String)，base64Decode(input: String?)

    4). binaryEncode(input: String)，binaryDecode(input: String)

    5). htmlEncode(input: CharSequence?)，htmlDecode(input: String?)

