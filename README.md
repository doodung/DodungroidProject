# SOPT_ANDROID_PROJECT

<br/>


## 📍 First_Seminar

<br/>

<h5>[ 필수과제 ]</h5>

<br/>

- SignupActivity 만들기

<img src="https://github.com/doodung/DodungroidProject/blob/master/slide1.PNG?raw=true" width="1000px" />

<br/>

<img src="https://github.com/doodung/DodungroidProject/blob/master/slide2.PNG?raw=true" width="1000px" />

<br/>

서버 연결 시 이름,아이디,비밀번호가 모두 전달되어야 하므로 한 칸이라도 비었을때 response가 fail되기 때문에 그 부분에 error code를 넣음.

```kotlin
...
if (response.isSuccessful) {
    Toast.makeText(this@SignupActivity, "회원가입 완료!", Toast.LENGTH_SHORT).show()
    if (response.body()!!.success) {
        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
else
{
    showError(response.errorBody())
}
...
```

<br/>

```kotlin
private fun showError(error : ResponseBody?)
{
    val e = error ?: return
    val ob = JSONObject(e.string())
    Toast.makeText(this, ob.getString ("message"),Toast. LENGTH_SHORT).show()
}
```

<br/>

<h5>[ 성장과제 2 ]</h5>

<br/>

- 자동로그인

<br/>

<img src="https://github.com/doodung/DodungroidProject/blob/master/slide3.PNG?raw=true" width="1000px" />

<br/>

sharedPreference 사용하여 아이디와 비밀번호를 저장함

```kotlin
fun autoLogin(){
    val preference = this.getSharedPreferences("temp", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = preference!!.edit()
    if(preference.getString("id", "").isNullOrBlank()||preference.getString("pw", "").isNullOrBlank())
    {
        init()
    }
    else
    {
        Toast.makeText(this, "자동로그인 되었습니다", Toast.LENGTH_SHORT).show()
        val gotologinintent = Intent(this, MainActivity2::class.java)
        startActivity(gotologinintent)
        finish()
    }
}
```

<br/>

체크박스에 체크가 되어있을 때만 자동로그인 되어야 함

```kotlin
private fun checkboxLogin() {
    val preference = this.getSharedPreferences("temp", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = preference!!.edit()
    if (cb_autologin.isChecked) {
        editor.putString("id", et_id.text.toString())
        editor.putString("pw", et_pw.text.toString())
        editor.clear()
        editor.apply()
        Toast.makeText(this, "다음부터 자동으로 로그인됩니다.", Toast.LENGTH_LONG).show()
    }
    else
    {
        editor.remove("id")
        editor.remove("pw")
        editor.clear()
        editor.apply()
    }
}
```



<br/>


## 📍 Second_Seminar

<br/>

<h5>[ 필수과제 ]</h5>

- RecyclerView

<br/>

<img src="https://github.com/doodung/DodungroidProject/blob/master/slide4.png?raw=true" width="1000px" />

<br/>

recyclerview를 만들기 위해 adapter와 viewholder 만듦

adapter는 아이템마다 뷰홀더를 생성하고 데이터를 전달.

click 인터페이스 구현

```kotlin
class ProfileAdapter(var context : Context): RecyclerView.Adapter<ProfileViewHolder>() {
    var data= mutableListOf<SampleDATA>()

    interface ItemClick
    {
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.profile_item,parent,false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
        if(itemClick != null)
        {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    override fun getItemCount(): Int =data.size
}
```

<br/>

fragment 코드에 아이템 클릭 리스너 구현 

내가 클릭한 position에 있는 title과 subtitle을 전달해줌

<br/>

```kotlin
profileAdapter.itemClick=object :ProfileAdapter.ItemClick{
    override fun onClick(view: View, position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("title", profileAdapter.data[position].title)
        intent.putExtra("subtitle", profileAdapter.data[position].subTitle)
        startActivity(intent)
    }
```

<br/>


## 📍 Third_Seminar

<br/>

<h5>[ 필수과제 ]</h5>

- bottomnavigation
- tablayout

<br/>

<img src="https://github.com/doodung/DodungroidProject/blob/master/slide5.png?raw=true" width="1000px" />

<br/>

<p align="center">
<img src="https://github.com/doodung/DodungroidProject/blob/master/screenshot2.gif?raw=true" height="600px"/>

<br/>

viewpageradapter 생성

```kotlin
class SampleViewPagerAdapter (fm:FragmentManager)
    :FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getCount(): Int = fragments.size
}
```

tablayout 구현, tablayout에  viewpager 구현

첫번째 탭 InfoFragment()

두번째 탭 OtherFragment()

```kotlin
class FirstFragment : Fragment() {
    private lateinit var viewpagerAdapter:SampleViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        viewpagerAdapter= SampleViewPagerAdapter(childFragmentManager)
        viewpagerAdapter.fragments= listOf(
            InfoFragment(),
            OtherFragment()
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sample_tab_viewpager.adapter=viewpagerAdapter
        sample_tab.setupWithViewPager(sample_tab_viewpager)
        sample_tab.apply{
            getTabAt(0)?.text="INFO"
            getTabAt(1)?.text="OTHER"
        }
    }
}
```

<br/>

bottom navigation과 viewpager 구현

```kotlin
viewPagerAdapter= SampleViewPagerAdapter(supportFragmentManager)
viewPagerAdapter.fragments= listOf(
    FirstFragment(),
    RecyclerFragment(),
    ThirdFragment()
)
sample_viewpager.adapter=viewPagerAdapter

sample_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {}
    override fun onPageSelected(position: Int) {
        bottomNavigationView.menu.getItem(position).isChecked = true
    }
})

bottomNavigationView.setOnNavigationItemSelectedListener{
    var index by Delegates.notNull<Int>()
    when(it.itemId){
        R.id.menu_camera ->index=2
        R.id.menu_brush ->index=1
        else -> index=0
    }
    sample_viewpager.currentItem=index
    true
}
```

<br/>

## 📍 Sixth_Seminar

<br/>

<h5>[ 필수과제 ]</h5>

- signin
- signup

<br/>

<img src="https://github.com/doodung/DodungroidProject/blob/master/silde6.PNG?raw=true" width="1000px" />

<img src="https://github.com/doodung/DodungroidProject/blob/master/silde7.PNG?raw=true" width="1000px" />

<br/>



<p align="center">
<img src="https://github.com/doodung/DodungroidProject/blob/master/screenshot.gif?raw=true" height="600px"/>

<br/>

인터페이스

```kotlin
interface MyService {

    //회원가입
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun postSignup(
        @Body body: RequestSignupData
    ):Call<ResponseSignupData>

    //로그인
    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    fun postSignin(
        @Body body: RequestSigninData
    ):Call<ResponseSigninData>
}

object MyServiceImpl {
    private const val BASE_URL = "http://15.164.83.210:3000/"
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : MyService = retrofit.create(MyService::class.java)
}
```



signin

```kotlin
data class ResponseSigninData(
    val data: Data,
    val message : String,
    val status : Int,
    val success : Boolean
){
    data class Data(
        val email : String,
        val password : String,
        val userName : String
    )
}
```

```kotlin
data class RequestSigninData (
        val email : String,
        val password : String,
    )
```



signup

```kotlin
data class ResponseSignupData(
    val data: Data,
    val message : String,
    val status : Int,
    val success : Boolean
){
    data class Data(
        val email : String,
        val password : String,
        val userName : String
    )
}
```

```kotlin
data class RequestSignupData (
        val email : String,
        val password : String,
        val userName : String
    )
```

