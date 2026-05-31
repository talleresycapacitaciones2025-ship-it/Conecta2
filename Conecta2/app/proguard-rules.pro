# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Keep Compose classes
-keep class androidx.compose.** { *; }

# Keep data classes
-keep class com.conecta2.model.** { *; }

-dontwarn org.jetbrains.annotations.**
