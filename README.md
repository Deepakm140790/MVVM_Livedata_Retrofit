# MVVM_Livedata_Retrofit
This repository explains how to use MVVM, Livedata and Retrofit.

## Library details
// Mockito for unit tests
def mockito_version = "2.19.0"
testImplementation "org.mockito:mockito-core:$mockito_version"
// required if you want to use Mockito for Android tests
androidTestImplementation "org.mockito:mockito-android:$mockito_version"
testImplementation "android.arch.core:core-testing:1.1.1"

//Glide - To download image
def glide_version = "4.9.0"
implementation "com.github.bumptech.glide:glide:$glide_version"
annotationProcessor "com.github.bumptech.glide:compiler:$glide_version"

//Recycler view
implementation "com.android.support:recyclerview-v7:$support_version"

//ViewModel and LiveData
def lifecycle_version = "2.0.0"
implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
//alternatively - just ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
//alternatively - just LiveData
implementation "androidx.lifecycle:lifecycle-livedata:$lifecycle_version"

//Retrofit
def retrofit_version = "2.6.0"
implementation "com.squareup.retrofit2:retrofit:retrofit_version"
implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"

//Okhttp3 logging interceptor
def okhttp_version = "3.14.2"
implementation "com.squareup.okhttp3:logging-interceptor:$okhttp_version"

// Butter knife
def butterknife_version = "10.1.0"
implementation "com.jakewharton:butterknife:$butterknife_version"
annotationProcessor "com.jakewharton:butterknife-compiler:$butterknife_version"
