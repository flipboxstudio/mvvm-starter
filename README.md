# mvvm-starter
--

A starter project for Android MVVM Project with DataBinding Library

--
Libraries used :

* Retrofit `com.squareup.retrofit2:retrofit:2.1.0`
* EventBus `org.greenrobot:eventbus:3.0.0`
* Glide `com.github.bumptech.glide:glide:3.7.0`
* Hawk `com.orhanobut:hawk:2.0.1`
* Android Libraries ( appcompat, design support, data binding, etc )

--

Setup included :

* Data Binding
	* Using Android Data Binding Library
	* ViewModel is inside `viewmodels` package
* Authentication Flow
	* Using Retrofit & ResponseInterceptor for request & response handling
	* Dummy API using `https://jsonplaceholder.typicode.com`
	* `AuthActivity` as Fragments Container
	* `LoginFragment` as Login UI
	* `ForgotPasswordFragment` as Forgot Password UI
* Reusable Style
	* All colors are available inside `colors.xml`
	* Styles are available inside `styles.xml`
	* Custom Fonts are using `CustomTextView` on `utils` package and custom attribute on `attrs.xml`
	* Roboto fonts included

### ToDo

- [ ] Documentation
- [ ] Location detection
- [ ] Analytics setup
- [ ] Moar utils
- [ ] Any suggestion?
