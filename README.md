&emsp;<font color=#0000FF
size=5>使用这个commonlibrary、减少您必须编写的代码量，从而节省代码的读写时间，这样您就可以将更多精力放在要为用户构建的内容上。</font>

&emsp;<font face="黑体" size=6>**一行代码即可引入到您的工程使用：**</font>


&emsp;&emsp;&emsp;&emsp;<font color=#FF0000 size=5>implementation
'com.spb:commonlibrary:1.0.0'  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;或者  
&emsp;&emsp;&emsp;&emsp;api 'com.spb:commonlibrary:1.0.0'</font>

**常用方法介绍：**</font>


**1. toast 用法**

    toast("hello")


**2. click 用法**

    button.click{
           // todo
        }


**3. 启动activity**

    start+要启动的activity （比如 DemoActivity）



**4. SharePreferenceUtil 用法**

     1). var spValue by SharePreferenceUtil ("key", "DefaultValue")

     2). set data spValue = "value"

     3). get data spValue



**5. NetworkUtil 用法**

    1). isNetworkAvailable(context)

    2). isConnected(context)

    3). getNetworkType(context)


**6.  获取屏幕宽高以及Dp/Px转化用法**

    1). dp2px(context) or px2dp

    2). screenWidth or screenHeight



**7. RegularUtil 用法**

    1). 身份证是否合法 isIDCard("no")

    2). 手机号是否合法 isMobile("no")

    3). isEmail是否合法 isEmail("xx.mail.com")

    4). 用户名校验 isUsername()

    5). 格式日期校验 isDate()

**8. EncodeUtil 用法**

    1). 普通字符串编码 encode(input,"")

    2). 普通字符串解码 decode(input,"")

    3). base64Encode(input: String)，base64Decode(input: String?)

    4). binaryEncode(input: String)，binaryDecode(input: String)

    5). htmlEncode(input: CharSequence?)，htmlDecode(input: String?)

**9. DateUtil 用法**

    1). 当前时间格式化成指定格式的String类型   currentTimeMills
           
    2). 当前时间的Date类型   currentTimeString()
     			   
    3). Date类型格式化成指定格式的String类型   currentDate

    4).Long类型格式化成指定格式的String类型的日期 format2String(params) , format2DateString() 
  
    5). 解析String类型的日期为Long类型 parseDateString2Mills(params)
  
    6). 解析String类型的日期为Date类型 parseString2Date
  
    7). 获取两个日期的时间差 getTimeSpan(params)
  
    8). 将时间戳转换成 xx小时前 的样式  formatAgoStyleForWeibo(), formatAgoStyleForWeChat()
  
    9). 判断日期是否在同一年  isSameYear(params)
    
    10). 日期是否在两个日期之间  betweenDates(params)

    11). 将日期时间设置为0点   ofTimeZero()
  
    12).获取星期的下标  dayOfWeek()/dayOfWeek