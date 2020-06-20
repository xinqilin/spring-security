# spring-security


### start

1. 載入starter
2. config class =><br>(1).@EnableWebSecurity <br> (2) extends WebSecurityConfigurerAdapter  



### 自訂權限規則

```java

@Override
	protected void configure(HttpSecurity http) throws Exception {

http.authorizeRequests().antMatchers("/").permitAll()
								.antMatchers("/level1/**").hasRole("VIP1")
								.antMatchers("/level2/**").hasRole("VIP2")
								.antMatchers("/level3/**").hasRole("VIP3");
								
	}
```


```java
		//開啟auto config的登入功能
		http.formLogin();
		//進登入頁
		///login?error 表示失敗
		
		//開啟auto config的登出功能
		http.logout();
```


### 自訂義驗證規則

```java

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		auth.inMemoryAuthentication()
			.withUser("bill").password("billisgood").roles("VIP1","VIP2")
			.and()
			.withUser("paul").password("pauljay").roles("VIP1");
	}
	
	//設置好登入時，噴錯There is no PasswordEncoder mapped for the id "null"
	//因為Spring security 5.0中新增了多種加密方式，也改變了密碼的格式。
	//現如今Spring Security中密碼的儲存格式是“{id}…………”。前面的id是加密方式，id可以是bcrypt、sha256等，後面跟著的是加密後的密碼。也就是說，程式拿到傳過來的密碼的時候，會首先查詢被“{”和“}”包括起來的id，來確定後面的密碼是被怎麼樣加密的，如果找不到就認為id是null。這也就是為什麼我們的程式會報錯：There is no PasswordEncoder mapped for the id “null”。官方文件舉的例子中是各種加密方式針對同一密碼加密後的儲存形式，原始密碼都是“password”。
	
	
	sol:
	帳號前 .passwordEncoder(new BCryptPasswordEncoder())
	密碼  .password(new BCryptPasswordEncoder().encode("密碼"))
	
	
	----------------
	若帳號從DB拿
	//1. 注入userDetailsService的實現類
    //2. auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	
```


### thymeleaf 顯示springSecurity資訊

```html
<div sec:authorize="isAuthenticated()">
	<h2><span sec:authentication="name"></span>，您好,您的角色有：
		<span sec:authentication="principal.authorities"></span></h2>
</div>
```

1. 搜尋依賴注入thymeleaf-extras-springsecurity4(5) maven repository 

```java
載入
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity4(有可能是5)</artifactId>
    <version>3.0.2.RELEASE</version>
</dependency>


```

html加上
版本問題要注意一下 thymeleaf-extras-springsecurity4、thymeleaf-extras-springsecurity5
``xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5"``
