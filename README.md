# mvvm-starter

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MVVM%20Starter-blue.svg?style=flat)]()

A starter project for Android MVVM Project with DataBinding Library

--
Libraries used :

* Retrofit `com.squareup.retrofit2:retrofit:2.1.0`
* EventBus `org.greenrobot:eventbus:3.0.0`
* Glide `com.github.bumptech.glide:glide:3.7.0`
* EasyPermission `pub.devrel:easypermissions:0.2.1`
* Hawk `com.orhanobut:hawk:2.0.1`
* Android Libraries ( `appcompat`, `design support`, `data binding`, etc )

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
* RecyclerView Sample
	* Sample recyclerview implementation using viewholder & databinding
	* Using `User` as dummy object representation
	* See `RecyclerViewActivity` for details
* ViewPager Sample
	* Sample viewpager implementation using tablayout
	* See `ViewPagerActivity` for details
* Android M permission handler using `EasyPermissions`
	* Handle permission for Android M and above
	* Sample implementation for CAMERA access permission
* Reusable Style
	* All colors are available inside `colors.xml`
	* Styles are available inside `styles.xml`
	* Custom Fonts are using `CustomTextView` on `utils` package and custom attribute on `attrs.xml`
	* Roboto fonts included
* Utilities classes
	* Camera Utils : Get image from camera / gallery
	* Calendar Utils : Parse & display Calendar object into various format
	
--
![screenshots](https://puu.sh/swReQ/d7b2f101f4.jpg "Screenshots")
--

### ToDo

- [ ] Documentation & Wiki
- [ ] Location detection
- [ ] Analytics setup
- [ ] Custom toolbar & menu
- [x] Android M permissions sample
- [x] Calendar utils
- [x] Camera utils
- [x] RecyclerView sample
- [x] ViewPager sample
- [ ] Any suggestion?
