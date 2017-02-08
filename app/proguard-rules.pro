# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\studio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses
# 避免混淆泛型
-keepattributes Signature
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
# 保留R下面的资源
-keep class **.R$* {*;}


##########实体类不参与混淆##########
-keep class com.ln.entity.**{*;}
##########butterKnife不参与混淆##########
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;

}
##########okhttp不参与混淆##########
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}

-dontwarn okio.**


-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

############greenDAO 3#####################
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-dontwarn org.greenrobot.greendao.database.**
-dontwarn rx.**

-keepattributes LineNumberTable,SourceFile

 # ProGuard configurations for Bugtags
  -keep class com.bugtags.library.** {*;}
  -dontwarn com.bugtags.library.**
  -keep class io.bugtags.** {*;}
  -dontwarn io.bugtags.**
  -dontwarn org.apache.http.**
  -dontwarn android.net.http.AndroidHttpClient
    # End Bugtags